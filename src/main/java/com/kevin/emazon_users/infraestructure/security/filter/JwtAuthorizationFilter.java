package com.kevin.emazon_users.infraestructure.security.filter;

import com.kevin.emazon_users.infraestructure.exception.AlreadyExpiredJwtException;
import com.kevin.emazon_users.infraestructure.exception.InvalidJwtTokenException;
import com.kevin.emazon_users.infraestructure.security.JpaUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

import static com.kevin.emazon_users.infraestructure.security.util.UtilSecurityClass.*;

@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JpaUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header != null && header.startsWith(PREFIX_TOKEN)) {
            String token = header.replace("Bearer ", "");

            // Aquí validas el token y obtienes el usuario
            String username = getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Aquí deberías cargar el usuario y sus autoridades
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
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
              .get("username", String.class);
    }

    private boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.get("username", String.class);
            // Obtener el rol único en formato ROLE_
            String roleFromToken = claims.get("role", String.class);

            // Verificar si el rol en el JWT coincide con las autoridades del usuario
            if (userDetails.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(roleFromToken))) {
                return false;
            }

            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                return false;
            }
            return username.equals(userDetails.getUsername());

        } catch (ExpiredJwtException e) {
            throw new AlreadyExpiredJwtException("El JWT ya expiró");
        } catch (JwtException e) {
            throw new InvalidJwtTokenException("El token es inválido");
        }
    }


}