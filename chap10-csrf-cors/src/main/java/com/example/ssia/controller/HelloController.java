package com.example.ssia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "Get Hello!\n";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!\n";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        return "Post Ciao!\n";
    }
}
