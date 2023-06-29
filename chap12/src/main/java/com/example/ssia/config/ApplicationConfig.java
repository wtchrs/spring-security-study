package com.example.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

/**
 * By configuring the two options below, Spring Boot will automatically register {@link ClientRegistration} and
 * {@link ClientRegistrationRepository} as beans. Alternatively, you can manually register them.
 * <ul>
 * <li>spring.security.oauth2.client.registration.github.client-id</li>
 * <li>spring.security.oauth2.client.registration.github.client-secret</li>
 * </ul>
 * @see ManualApplicationConfig
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth -> {});
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return http.build();
    }
}
