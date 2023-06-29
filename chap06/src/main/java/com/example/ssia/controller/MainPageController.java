package com.example.ssia.controller;

import com.example.ssia.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService productService;

    @GetMapping("/main")
    public String mainPage(Authentication auth, Model model) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("products", productService.findAll());
        return "main";
    }
}
