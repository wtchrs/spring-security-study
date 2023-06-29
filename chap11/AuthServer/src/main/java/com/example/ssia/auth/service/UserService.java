package com.example.ssia.auth.service;

import com.example.ssia.auth.entity.Otp;
import com.example.ssia.auth.entity.OtpRepository;
import com.example.ssia.auth.entity.User;
import com.example.ssia.auth.entity.UserRepository;
import com.example.ssia.auth.service.command.ValidateOtpCommand;
import com.example.ssia.auth.util.GenerateCodeUtil;
import com.example.ssia.auth.service.command.AuthUserCommand;
import com.example.ssia.auth.service.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final OtpRepository otpRepository;

    private final MailService mailService;

    @Value("${sendgrid.enable}")
    private boolean sendgridEnable;

    public void addUser(CreateUserCommand command) {
        userRepository.save(command.toUser(passwordEncoder));
    }

    public void auth(AuthUserCommand command) {
        Optional<User> find = userRepository.findByUsername(command.getUsername());
        if (find.isPresent()) {
            User user = find.get();
            if (passwordEncoder.matches(command.getPassword(), user.getPassword())) {
                String code = renewOtp(user);
                log.info("code = {}", code);
                if (sendgridEnable) {
                    mailService.sendTextMail(
                            user.getEmail(), "[SSIA] Complete authentication!",
                            "Your authentication code is here: " + code);
                }
            } else {
                throw new BadCredentialsException("Bad credentials.");
            }
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    public boolean check(ValidateOtpCommand command) {
        return otpRepository
                .findByUsername(command.getUsername())
                .map(otp -> otp.getCode().equals(command.getCode()))
                .orElse(false);
    }

    private String renewOtp(User user) {
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> find = otpRepository.findByUsername(user.getUsername());
        if (find.isPresent()) {
            Otp otp = find.get();
            otp.setCode(code);
        } else {
            otpRepository.save(new Otp(user.getUsername(), code));
        }
        return code;
    }
}
