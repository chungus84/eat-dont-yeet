package com.eatdontyeet.recipebackend.security.config.filter;

import com.eatdontyeet.recipebackend.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.rmi.ServerException;

public class JwtFilter extends GenericFilterBean {

    private String secret = SecurityConstants.SECRET_KEY;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        final String authHeader = httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            if(authHeader == null || !authHeader.startsWith(SecurityConstants.BEARER)) {
                throw  new ServerException("An exception occured");
            }
        }
        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        httpServletRequest.setAttribute("claims", claims);
        httpServletRequest.setAttribute("profile", request.getParameter("userId"));
        chain.doFilter(httpServletRequest, httpServletResponse);


    }
}
