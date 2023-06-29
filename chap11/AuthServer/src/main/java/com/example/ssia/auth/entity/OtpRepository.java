package com.example.ssia.auth.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {

    Optional<Otp> findByUsername(String username);
}
