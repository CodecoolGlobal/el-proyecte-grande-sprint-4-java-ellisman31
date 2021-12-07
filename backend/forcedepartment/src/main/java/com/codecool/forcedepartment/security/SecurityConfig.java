package com.codecool.forcedepartment.security;

import com.codecool.forcedepartment.security.Filters.CustomAuthenticationFilter;
import com.codecool.forcedepartment.security.Filters.CustomAuthorizationFilter;
import com.codecool.forcedepartment.security.JWT.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final SecretKey secretKey;
    private final JWTConfig jwtConfig;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
                          UserDetailsService userDetailsService, SecretKey secretKey, JWTConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), secretKey, jwtConfig);
        //customAuthenticationFilter.setFilterProcessesUrl("/new_login_path"); //custom login and pass this to the filter
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().antMatchers("/", "/register", "/login").permitAll()
                .and()
                .authorizeRequests().antMatchers(GET, "/api/**").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(POST, "/api/**").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(PUT, "/api/**").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(DELETE, "/api/**").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), secretKey, jwtConfig))
                .addFilterBefore(new CustomAuthorizationFilter(secretKey, jwtConfig), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
