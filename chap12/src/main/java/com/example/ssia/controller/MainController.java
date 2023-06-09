package com.example.ssia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String mainPage(OAuth2AuthenticationToken token) {
        log.info("token.getPrincipal() = {}", token.getPrincipal());
        return "main.html";
    }
}
