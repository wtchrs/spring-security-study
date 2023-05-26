package com.example.ssia.config;

import com.example.ssia.service.InMemoryUserDetailsService;
import com.example.ssia.user.CustomUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class UserManagementConfig {

    // @Bean
    public UserDetailsService dummyUserDetailsService() {
        UserDetails user1 = new CustomUser("john", "1234", "read");
        UserDetails user2 = User.withUsername("user")
                .password("1234")
                .authorities("read", "write")
                .accountExpired(false)
                .disabled(true)
                .build();
        return new InMemoryUserDetailsService(List.of(user1, user2));
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
