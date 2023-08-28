package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager manager) {
        var oauth2Client = new ServletOAuth2AuthorizedClientExchangeFilterFunction(manager);
        return WebClient.builder().apply(oauth2Client.oauth2Configuration()).build();
    }

}
