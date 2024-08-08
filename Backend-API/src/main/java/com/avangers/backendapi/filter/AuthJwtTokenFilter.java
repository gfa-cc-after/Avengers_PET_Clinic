package com.avangers.backendapi.filter;

import com.avangers.backendapi.services.UserServiceImpl;
import com.avangers.backendapi.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
public class AuthJwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private UserServiceImpl userService;

    public AuthJwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserServiceImpl userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractJwtToken(request);
            if (token != null && jwtTokenUtil.validateJwtToken(token)) {
                String email = jwtTokenUtil.getEmailFromJwtToken(token);
                UserDetails userDetails = userService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken upat =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        } catch (Exception e) {
            log.error("User authentication cannot be performed: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest httpServletRequest){
        //Bearer egfheghgkehgwhgkgwhkghwergl
        String header = httpServletRequest.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")){
            return header.substring(7); //Bearer+space=7, then token
        }
        return null;
    }
}
