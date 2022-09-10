package com.example.iotmanager01.api.model;

public class JsonLoginRequest {
    final String login;
    final String password;

    public JsonLoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}