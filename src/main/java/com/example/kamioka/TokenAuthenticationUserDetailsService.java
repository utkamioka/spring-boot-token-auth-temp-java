package com.example.kamioka;

import jersey.repackaged.com.google.common.collect.ImmutableSet;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * @see TokenAuthenticatedProcessingFilter
 */
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("TokenAuthenticationUserDetailsService.loadUserDetails(PreAuthenticatedAuthenticationToken)");
        System.out.println("token = " + token);
        System.out.println("------------------------------------------------------------------------------------------");
        if (isNull(token.getCredentials())) {
            throw new UsernameNotFoundException("empty credentials");
        }
        // credentialsとprincipalの内容は、{@link TokenAuthenticatedProcessingFilter}を参照
        String username = token.getCredentials().toString();
        String password = token.getPrincipal().toString();  // principal is always empty string

        // XXX: Credentialsで指定されたユーザになる
        // XXX: 【注意】認証はしていない

        if (Objects.equals(username, "admin")) {
            return new User(username, password, ImmutableSet.of(
                    new SimpleGrantedAuthority("ROLE_admin")
            ));
        }
        if (Objects.equals(username, "user1")) {
            return new User(username, password, ImmutableSet.of(
                    new SimpleGrantedAuthority("ROLE_user")
            ));
        }
        if (Objects.equals(username, "user2")) {
            return new User(username, password, ImmutableSet.of(
                    new SimpleGrantedAuthority("ROLE_user")
            ));
        }
        if (Objects.equals(username, "guest")) {
            return new User(username, password, ImmutableSet.of(
                    new SimpleGrantedAuthority("ROLE_guest")
            ));
        }

        throw new UsernameNotFoundException(token.getPrincipal()+":"+token.getCredentials());
    }
}
