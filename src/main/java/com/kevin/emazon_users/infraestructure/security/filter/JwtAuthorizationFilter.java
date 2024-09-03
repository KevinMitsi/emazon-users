package com.kevin.emazon_users.infraestructure.security.filter;

import com.kevin.emazon_users.infraestructure.security.JpaUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

import static com.kevin.emazon_users.infraestructure.security.util.ConstantsSecurityContext.ROLE_KEY;
import static com.kevin.emazon_users.infraestructure.security.util.UtilSecurityClass.*;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JpaUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header != null && header.startsWith(PREFIX_TOKEN)) {
            String token = header.replace(PREFIX_TOKEN, "");

            // Aquí validas el token y obtienes el usuario
            String username = getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Aquí deberías cargar el usuario y sus autoridades
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (validateToken(token, userDetails, response)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //Contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private  String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build()
                    .parseSignedClaims(token)
                    .getPayload()
              .get(SPRING_SECURITY_FORM_USERNAME_KEY, String.class);
    }

    private boolean validateToken(String token, UserDetails userDetails, HttpServletResponse response) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.get(SPRING_SECURITY_FORM_USERNAME_KEY, String.class);
            // Obtener el rol único en formato ROLE_
            String roleFromToken = claims.get(ROLE_KEY, String.class);

            // Verificar si el rol en el JWT coincide con las autoridades del usuario
            if (userDetails.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(roleFromToken))) {
                return false;
            }

            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false;
            }
            return username.equals(userDetails.getUsername());

        } catch (JwtException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
    }


}