package com.example.iotmanager01.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.iotmanager01.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private LoginPresenterImpl presenter;
    private static final String TAG = "TAGOWE";
    TextInputEditText loginEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenterImpl(this, this);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
            presenter.loginUser();
        });



    }

    @Override
    public String getLogin() {
        return  loginEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void loggingInView() {

    }

    @Override
    public void loggedInView() {

    }

    @Override
    public void errorView() {

    }

    @Override
    public void openGraphView() {

    }
}