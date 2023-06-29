package com.example.ssia.second.auth.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OtpAuthCommand {

    private String username;
    private String code;

    public OtpAuthCommand(String username, String code) {
        this.username = username;
        this.code = code;
    }
}
