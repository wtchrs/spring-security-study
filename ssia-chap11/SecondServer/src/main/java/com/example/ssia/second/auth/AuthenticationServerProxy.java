package com.example.ssia.second.auth;

import com.example.ssia.second.auth.command.OtpAuthCommand;
import com.example.ssia.second.auth.command.PasswordAuthCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationServerProxy {

    private final RestTemplate rest;

    @Value("${auth.server.base-url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";

        PasswordAuthCommand body = new PasswordAuthCommand(username, password);
        HttpEntity<PasswordAuthCommand> request = new HttpEntity<>(body);

        rest.postForEntity(url, request, Void.class);
    }

    public boolean sendOtp(String username, String code) {
        String url = baseUrl + "/otp/check";

        OtpAuthCommand body = new OtpAuthCommand(username, code);
        HttpEntity<OtpAuthCommand> request = new HttpEntity<>(body);

        return rest.postForEntity(url, request, Void.class)
                .getStatusCode()
                .equals(HttpStatus.OK);
    }
}
