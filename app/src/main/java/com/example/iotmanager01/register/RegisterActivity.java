package com.example.iotmanager01.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.iotmanager01.R;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public String getLogin() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getRePassword() {
        return null;
    }

    @Override
    public String getTerms() {
        return null;
    }

    @Override
    public void errorView(String message) {

    }

    @Override
    public void openMainPageView() {

    }
}