package com.example.springauthserverexample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client
        .ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticlesController {

    private final WebClient webClient;

    @GetMapping("/articles")
    public String[] getArticles(
            @RegisteredOAuth2AuthorizedClient("articles-client-authorization-code")
            OAuth2AuthorizedClient authorizedClient) {

        log.info("ArticlesController.getArticles");
        return webClient.get()
                .uri("http://localhost:8090/articles")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }
}
