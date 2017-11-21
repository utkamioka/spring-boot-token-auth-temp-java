package com.example.kamioka.views;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
public class Welcome {
    @RequestMapping(path = "/welcome")
    public Map get() {
        return ImmutableMap.builder().put("createAt", Objects.toString(new Date())).put("message", "welcome").build();
    }
}
