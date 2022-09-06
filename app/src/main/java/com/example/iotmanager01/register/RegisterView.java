package com.example.iotmanager01.register;

public interface RegisterView {
    String getLogin();
    String getEmail();
    String getPassword();
    String getRePassword();
    String getTerms();
    void errorView(String message);
    void openMainPageView();
}
