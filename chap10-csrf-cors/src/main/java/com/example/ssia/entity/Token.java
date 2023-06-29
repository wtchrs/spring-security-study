package com.example.ssia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Token {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @Setter
    private String token;

    public Token(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }
}
