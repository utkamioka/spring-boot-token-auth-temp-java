package com.example.kamioka.views.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
    @RequestMapping(path = "/")
    public String get() {
        return "index";
    }
}
