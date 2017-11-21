package com.example.kamioka;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class TokenAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("TokenAuthenticatedProcessingFilter.getPreAuthenticatedCredentials()");
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization = " + authorization);
        System.out.println("-------------------------------------------------------------------");
        return authorization == null ? "" : authorization;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("TokenAuthenticatedProcessingFilter.getPreAuthenticatedPrincipal()");
        System.out.println("-----------------------------------------------------------------");
        return "";
    }
}
