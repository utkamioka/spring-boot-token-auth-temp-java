package com.example.kamioka.views.api;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(path = "/api/welcome")
public class Welcome {
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map get(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails = " + userDetails);
        Map<String,String> response = new LinkedHashMap<>();
        response.put("createAt", Objects.toString(new Date()));
        response.put("message", "welcome");
        response.put("username", isNull(userDetails) ? null : userDetails.getUsername());
        return response;
    }
}
