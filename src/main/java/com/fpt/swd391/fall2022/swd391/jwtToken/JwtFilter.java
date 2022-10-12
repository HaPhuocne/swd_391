package com.fpt.swd391.fall2022.swd391.jwtToken;


import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
     final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = getAccessToken(request);
            if(!jwtUtil.ValidAccessToken(accessToken))  {
                filterChain.doFilter(request, response);
                return;
            }
        filterChain.doFilter(request, response);

        setAuthentication(accessToken,request);
    }
    private void setAuthentication(String accessToken, HttpServletRequest request){
        UserDetails userDetails = getUserDetails(accessToken);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,null);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    private UserDetails getUserDetails(String accessToken){
        Account account = new Account();
        String[] subjectArray = jwtUtil.getSubject(accessToken).split(",");
        account.setId(Long.parseLong(subjectArray[0]));
        account.setEmail(subjectArray[1]);
        return account;
    }
    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("Authorization header :" + header);

        return !ObjectUtils.isEmpty(header) && !header.startsWith("Bearer");

    }
    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split("")[1].trim();
        System.out.println("Access token: "+token);
        return token;
    }
}
