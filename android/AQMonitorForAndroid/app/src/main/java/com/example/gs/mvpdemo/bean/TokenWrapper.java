package com.example.gs.mvpdemo.bean;

public class TokenWrapper {

    // 密钥
    private String token;

    public TokenWrapper(String token) {
        this.token = token;
    }

    public String getPrincipal() {
        return token;
    }

    public String getCredentials() {
        return token;
    }
}
