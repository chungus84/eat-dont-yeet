package com.eatdontyeet.recipebackend.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("PUT", "DELETE", "POST", "PUT", "GET", "HEAD")
                .allowedHeaders("CustomAuth", "Authorization", "header3", "Origin", "Access-Control-Allow-Origin", "Content-Type",
                        "Accept", "Origin, Accept", "X-Requested-With",
                        "Access-Control-Request-Method", "Access-Control-Request-Headers", "username", "userid")
                .exposedHeaders("CustomAuth", "Origin", "Content-Type", "Accept", "Authorization", "userid", "username",
                        "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true);
    }
}
