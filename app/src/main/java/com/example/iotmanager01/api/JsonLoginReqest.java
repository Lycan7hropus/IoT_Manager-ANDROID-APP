package com.example.iotmanager01.api;

public class JsonLoginReqest {
    final String login;
    final String password;

    public JsonLoginReqest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}