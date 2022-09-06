package com.example.iotmanager01.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.iotmanager01.R;
import com.example.iotmanager01.graph.GraphActivity;
import com.example.iotmanager01.main_page.MainPageActivity;
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
    public void errorView(String message) {
        Log.d(TAG, "loginUser: error");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMainPageView() {
        Log.d(TAG, "loginUser: opengraphview");
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);

    }
}