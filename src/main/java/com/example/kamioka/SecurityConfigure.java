package com.example.kamioka;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .anyRequest().authenticated()

                .and()

                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("123").roles("admin")
                .and()
                .withUser("user1").password("123").roles("user")
                .and()
                .withUser("user2").password("123").roles("user")
                .and()
                .withUser("guest").password("123").roles("guest");
    }
}
