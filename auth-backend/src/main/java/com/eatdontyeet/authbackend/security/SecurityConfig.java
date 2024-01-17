package com.eatdontyeet.authbackend.security;

import com.eatdontyeet.authbackend.security.filter.AuthenticationFilter;
import com.eatdontyeet.authbackend.security.filter.ExceptionHandlerFilter;
import com.eatdontyeet.authbackend.security.filter.JWTAuthorizationFilter;
import com.eatdontyeet.authbackend.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    CustomAuthenticationManager customAuthenticationManager;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager, SECRET_KEY);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/users/register").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(SECRET_KEY), AuthenticationFilter.class);
        return http.build();

    }

}
