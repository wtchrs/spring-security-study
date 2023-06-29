package com.example.ssia.auth.controller;

import com.example.ssia.auth.service.command.ValidateOtpCommand;
import com.example.ssia.auth.service.UserService;
import com.example.ssia.auth.service.command.AuthUserCommand;
import com.example.ssia.auth.service.command.CreateUserCommand;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody CreateUserCommand command){
        userService.addUser(command);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody AuthUserCommand command) {
        userService.auth(command);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody ValidateOtpCommand otpCommand, HttpServletResponse response) {
        if (userService.check(otpCommand)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
