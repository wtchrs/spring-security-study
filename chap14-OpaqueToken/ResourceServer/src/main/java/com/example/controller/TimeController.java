package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@Slf4j
@RestController
public class TimeController {

    @GetMapping("/time/now")
    public String test(BearerTokenAuthentication auth) {
        log.info("auth.getToken().getTokenValue() = {}", auth.getToken().getTokenValue());
        return ZonedDateTime.now().toString();
    }

}
