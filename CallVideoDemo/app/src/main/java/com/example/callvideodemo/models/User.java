package com.example.callvideodemo.models;

import java.io.Serializable;

public class User implements Serializable {
    private String username, email, token;

    public User(String username, String email, String token) {
        this.username = username;
        this.email = email;
        this.token = token;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
