package com.eatdontyeet.recipebackend.security.config;

import com.eatdontyeet.recipebackend.security.config.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean<>();
        filter.setFilter(new JwtFilter());

        filter.addUrlPatterns(("/profile/user/*"));
        return filter;
    }
}
