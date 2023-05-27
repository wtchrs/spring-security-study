package com.example.ssia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/a")
    public String postEndpointA() {
        return "Works!\n";
    }

    @GetMapping("/a")
    public String getEndpointA() {
        return "Works!\n";
    }

    @GetMapping("/a/b")
    public String getEndpointB() {
        return "Works!\n";
    }

    @GetMapping("/a/b/c")
    public String getEndpointC() {
        return "Works!\n";
    }
}
