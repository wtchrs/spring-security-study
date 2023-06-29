package com.example.ssia.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByIdentifier(String identifier);
}
