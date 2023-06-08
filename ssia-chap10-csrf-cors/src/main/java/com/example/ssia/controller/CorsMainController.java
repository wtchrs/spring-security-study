package com.example.ssia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cors")
@Slf4j
public class CorsMainController {

    @GetMapping("/")
    public String mainPage() {
        return "main-cors";
    }

    @PostMapping("/test")
    @ResponseBody
    @CrossOrigin("http://localhost:8080") // Per-Endpoint CORS Policy Configuration
    public String test() {
        log.info("CorsMainController.test");
        log.info("Test method called");
        return "HELLO";
    }
}
