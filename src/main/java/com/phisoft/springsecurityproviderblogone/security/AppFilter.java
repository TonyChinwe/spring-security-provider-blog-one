package com.phisoft.springsecurityproviderblogone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AppFilter implements Filter {
    @Autowired
    private AuthenticationManager manager;
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String header=request.getHeader("Authorization");
        CustomAuthToken customAuthToken=new CustomAuthToken(header,null);
        try {
            Authentication result=manager.authenticate(customAuthToken);
            if (result.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(result);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }












}
