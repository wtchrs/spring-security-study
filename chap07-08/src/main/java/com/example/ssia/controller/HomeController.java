package com.example.ssia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!\n";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "Ciao!\n";
    }

    @GetMapping("/hola")
    public String hola() {
        return "Hola!\n";
    }
}
