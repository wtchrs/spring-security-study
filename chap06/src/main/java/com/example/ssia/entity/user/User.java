package com.example.ssia.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 45)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addAuthority(String name) {
        authorities.add(new Authority(this, name));
    }
}
