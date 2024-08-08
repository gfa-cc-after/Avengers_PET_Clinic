package com.avangers.backendapi.filter;

import com.avangers.backendapi.models.User;
import com.avangers.backendapi.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
public class AuthJwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private User user;

    public AuthJwtTokenFilter(JwtTokenUtil jwtTokenUtil, User )
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
