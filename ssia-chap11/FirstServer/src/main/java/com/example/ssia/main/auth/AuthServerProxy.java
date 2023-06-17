package com.example.ssia.main.auth;

import com.example.ssia.main.auth.command.OtpAuthCommand;
import com.example.ssia.main.auth.command.PasswordAuthCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthServerProxy {

    private final RestTemplate restTemplate;

    @Value("${auth.server.base-url}")
    private String baseUrl;

    public boolean sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        HttpEntity<PasswordAuthCommand> request = new HttpEntity<>(new PasswordAuthCommand(username, password));
        return restTemplate.postForEntity(url, request, Void.class)
                .getStatusCode()
                .equals(HttpStatus.OK);
    }

    public boolean sendOtp(String username, String code) {
        String url = baseUrl + "/otp/check";
        HttpEntity<OtpAuthCommand> request = new HttpEntity<>(new OtpAuthCommand(username, code));
        return restTemplate.postForEntity(url, request, Void.class)
                .getStatusCode()
                .equals(HttpStatus.OK);
    }
}
