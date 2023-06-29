package com.example.ssia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        log.info("Adding product '{}'", name);
        return "redirect:/main";
    }
}
