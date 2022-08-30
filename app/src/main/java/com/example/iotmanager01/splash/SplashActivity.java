package com.example.iotmanager01.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.iotmanager01.R;
import com.example.iotmanager01.TokenRepository;
import com.example.iotmanager01.graph.GraphActivity;
import com.example.iotmanager01.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private SplashPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        presenter = new SplashPresenterImpl(this, this);
        presenter.navigateToDestination();


    }

    @Override
    public void openLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void openGraphView() {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
}