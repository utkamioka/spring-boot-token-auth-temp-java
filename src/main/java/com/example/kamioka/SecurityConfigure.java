package com.example.kamioka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("-----------------------------------------");
        System.out.println("SecurityConfigure.configure(HttpSecurity)");
        System.out.println("-----------------------------------------");
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/welcome").permitAll()
//                .mvcMatchers("/api", "/api/**").permitAll()
                .anyRequest().authenticated();

        http.addFilter(tokenAuthenticatedProcessingFilter());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("---------------------------------------------------------");
        System.out.println("SecurityConfigure.configure(AuthenticationManagerBuilder)");
        System.out.println("---------------------------------------------------------");
        auth.authenticationProvider(tokenAuthenticationProvider());
    }

    //@Bean
    public AbstractPreAuthenticatedProcessingFilter tokenAuthenticatedProcessingFilter() throws Exception {
        System.out.println("------------------------------------------------------");
        System.out.println("SecurityConfigure.tokenAuthenticatedProcessingFilter()");
        System.out.println("------------------------------------------------------");
        TokenAuthenticatedProcessingFilter filter = new TokenAuthenticatedProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    //@Bean
    public PreAuthenticatedAuthenticationProvider tokenAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(tokenAuthenticationUserDetailsService());
        provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
        return provider;
    }

    //@Bean
    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> tokenAuthenticationUserDetailsService() {
        System.out.println("---------------------------------------------------------");
        System.out.println("SecurityConfigure.tokenAuthenticationUserDetailsService()");
        System.out.println("---------------------------------------------------------");
        return new TokenAuthenticationUserDetailsService();
    }

}
