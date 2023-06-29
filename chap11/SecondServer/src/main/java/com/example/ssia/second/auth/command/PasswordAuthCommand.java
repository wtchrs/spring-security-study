package com.example.ssia.second.auth.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordAuthCommand {

    private String username;
    private String password;

    public PasswordAuthCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
