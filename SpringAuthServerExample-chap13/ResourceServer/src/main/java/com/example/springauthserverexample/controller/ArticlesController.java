package com.example.springauthserverexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticlesController {

    @GetMapping
    public String[] getArticles() {
        log.info("ArticlesController.getArticles");
        return new String[]{"Article 1", "Article 2", "Article 3"};
    }
}
