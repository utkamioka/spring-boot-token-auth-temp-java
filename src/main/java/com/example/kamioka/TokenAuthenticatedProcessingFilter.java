package com.example.kamioka;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class TokenAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("TokenAuthenticatedProcessingFilter.getPreAuthenticatedCredentials()");
        for (String s : Collections.list(request.getHeaderNames())) {
            System.out.println("> " + s + " = " + request.getHeader(s));
        }
        String authorization = request.getHeader("Authorization");
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
