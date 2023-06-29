package com.example.ssia.config;

import com.example.ssia.filter.CsrfTokenLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public UserDetailsService uds() {
        var uds = new InMemoryUserDetailsManager();
        uds.createUser(User
                .withUsername("user")
                .password("1234")
                .authorities("READ")
                .build());
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class);

        CsrfTokenRequestAttributeHandler csrfHandler = new CsrfTokenRequestAttributeHandler();
        csrfHandler.setCsrfRequestAttributeName(null);

        http.csrf(csrf -> csrf
                .csrfTokenRequestHandler(csrfHandler)  // To disable default csrf token generation.
                .csrfTokenRepository(customCsrfTokenRepository)
                .ignoringRequestMatchers("/rest/ciao")
                .ignoringRequestMatchers("/cors/**"));

        // Centralized CORS Policy Configuration
        http.cors(cors -> cors.configurationSource(corsConfigurationSource));

        http.formLogin(form -> form.defaultSuccessUrl("/main", true));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/rest/**").permitAll()
                .requestMatchers("/cors/**").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}
