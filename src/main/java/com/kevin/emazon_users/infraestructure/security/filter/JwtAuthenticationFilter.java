package com.kevin.emazon_users.infraestructure.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.emazon_users.domain.model.UserModel;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kevin.emazon_users.infraestructure.security.util.UtilSecurityClass.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserModel userModel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userModel.getEmail(), userModel.getPassword()
            );
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new AuthenticationCredentialsNotFoundException("No se pudo crear el token de autenticación");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        UserDetails user = (UserDetails) authResult.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        String token = Jwts.builder()
                .issuedAt(now)
                .expiration(new Date(now.getTime() + MILLIS_TIME_EXPIRATION))
                .signWith(SECRET_KEY)
                .claims(buildClaims(user))
                .compact();
        fillInformation(response, token, user);
    }

    private static void fillInformation(HttpServletResponse response, String token, UserDetails user) throws IOException {
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        Map<String, String> body = new HashMap<>();
        body.put("username", user.getUsername());
        body.put("token", token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType(CONTENT_TYPE);
    }

    private Map<String, ?> buildClaims(UserDetails user) {
        Map<String, Object> claimBody = new HashMap<>();
        claimBody.put("username", user.getUsername());
        // Extraer el rol en formato ROLE_
        claimBody.put("role", user.getAuthorities().iterator().next().getAuthority());
        return claimBody;
    }



    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "error al ingresar los datos de inicios de sesión");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }
}
