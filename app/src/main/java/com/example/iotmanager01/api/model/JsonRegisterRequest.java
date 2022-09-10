package com.example.iotmanager01.api.model;

public class JsonRegisterRequest {
    final String login;
    final String email;
    final String password;
    final String repassword;

    public JsonRegisterRequest(String login, String email, String password, String repassword) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }
}
