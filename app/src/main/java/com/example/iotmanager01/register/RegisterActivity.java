package com.example.iotmanager01.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.iotmanager01.R;
import com.example.iotmanager01.databinding.ActivityRegisterBinding;
import com.example.iotmanager01.login.LoginActivity;
import com.example.iotmanager01.main_page.MainPageActivity;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements RegisterView{
    private ActivityRegisterBinding binding;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerPresenter = new RegisterPresenterImpl(this, this);


        binding.registerButton.setOnClickListener(view -> {
            registerPresenter.registerUser();
        });

        binding.checkBoxTerms.setError("error");
    }

    @Override
    public String getLogin() {
        return binding.loginInputEditText.getText().toString();
    }

    @Override
    public String getEmail() {
        return binding.emailInputEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.passInputEditText.getText().toString();
    }

    @Override
    public String getRePassword() {
        return binding.rePassInputEditText.getText().toString();
    }

    @Override
    public boolean getTerms() {

        return binding.checkBoxTerms.isChecked();
    }

    @Override
    public void errorView(String message) {

    }

    @Override
    public void openLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}