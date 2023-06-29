package com.example.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails user1 = User.withUsername("john")
                .password("1234")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("jane")
                .password("1234")
                .roles("MANAGER")
                .authorities("PREMIUM")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(authReq -> authReq
                .requestMatchers("/hello").hasRole("ADMIN")
                .requestMatchers("/ciao").hasRole("MANAGER")

                .requestMatchers(HttpMethod.GET, "/a").authenticated()
                .requestMatchers(HttpMethod.POST, "/a").denyAll()
                .requestMatchers("/a/b/**").authenticated()

                .requestMatchers("/product/{code:^[0-9]*$}").permitAll()

                .requestMatchers(RegexRequestMatcher.regexMatcher("^/video/(us|uk|ca)/(en|fr)$")).authenticated()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/video/*/*")).hasAuthority("PREMIUM")

                .anyRequest().denyAll());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
