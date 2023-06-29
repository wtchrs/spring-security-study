package com.example.ssia.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Otp {

    @Id
    private String username;

    @Setter
    private String code;

    public Otp(String username, String code) {
        this.username = username;
        this.code = code;
    }
}
