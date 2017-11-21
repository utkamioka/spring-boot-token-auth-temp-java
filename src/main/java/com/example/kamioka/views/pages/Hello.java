package com.example.kamioka.views.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
    @RequestMapping(path = "/pages/hello")
    public String get() {
        return "hello";
    }
}
