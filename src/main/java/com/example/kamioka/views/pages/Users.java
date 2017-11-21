package com.example.kamioka.views.pages;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({"ROLE_admin", "ROLE_user"})
public class Users {
    @RequestMapping(path = "/pages/users")
    public String get(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("-----------");
        System.out.println("Users.get()");
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("userDetails.getPassword() = " + userDetails.getPassword());
        System.out.println("userDetails.getAuthorities() = " + userDetails.getAuthorities());
        System.out.println("-----------");
        return "users";
    }
}
