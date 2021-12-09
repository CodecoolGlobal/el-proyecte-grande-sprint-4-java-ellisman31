package com.codecool.forcedepartment.security.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codecool.forcedepartment.security.JWT.JWTConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;
    private final JWTConfig jwtConfig;
    private final static int PLUS_DAY_AFTER_ACCESS_TOKEN = 14;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, SecretKey secretKey, JWTConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            response.setHeader("errorMessage", "The given user is not valid or exist!");
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException{

        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(String.valueOf(secretKey).getBytes());

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",
                        user.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(java.sql.Date.valueOf(
                        LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()+PLUS_DAY_AFTER_ACCESS_TOKEN)))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);


        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }


}
