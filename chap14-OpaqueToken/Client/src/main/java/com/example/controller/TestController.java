package com.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    private final WebClient webClient;

    @GetMapping("/")
    public String test(OAuth2AuthenticationToken token) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(token.getAuthorizedClientRegistrationId(), token.getName());
        String tokenValue = client
                .getAccessToken()
                .getTokenValue();
        log.info("tokenValue = {}", tokenValue);
        String nowTime = webClient.get()
                .uri("http://localhost:8090/time/now")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Hello, NOW TIME : " + nowTime;
    }

}
