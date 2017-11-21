package com.example.kamioka;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

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
                .mvcMatchers("/api/welcome").permitAll()
                .mvcMatchers("/pages/hello").permitAll()
//                .mvcMatchers("/api", "/api/**").permitAll()
                .anyRequest().authenticated();

        // HTTPヘッダのアクセストークンから認証情報を抽出するフィルター
        AbstractPreAuthenticatedProcessingFilter preAuthFilter = new TokenAuthenticatedProcessingFilter();
        preAuthFilter.setAuthenticationManager(authenticationManager());
        http.addFilter(preAuthFilter);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("---------------------------------------------------------");
        System.out.println("SecurityConfigure.configure(AuthenticationManagerBuilder)");
        System.out.println("---------------------------------------------------------");

        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new TokenAuthenticationUserDetailsService());
        provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());

        auth.authenticationProvider(provider);
    }
}
