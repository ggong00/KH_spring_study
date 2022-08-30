package com.example.demo2.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String username;
    private String pass;
    private String email;

    public User() {
    }

    public User(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }
}
