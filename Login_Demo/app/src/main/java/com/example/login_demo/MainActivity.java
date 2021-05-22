package com.example.login_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    TextView forgetPass;
    Button btnLogin;
    FloatingActionButton fb, twitter, google;
    float v = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        userName = findViewById(R.id.edtUserName);
        password = findViewById(R.id.edtPass);
        forgetPass = findViewById(R.id.tvForgetPass);
        btnLogin = findViewById(R.id.btnLogin);

        userName.setTranslationY(800);
        password.setTranslationY(800);
        forgetPass.setTranslationY(800);
        btnLogin.setTranslationY(800);

        userName.setAlpha(v);
        password.setAlpha(v);
        forgetPass.setAlpha(v);
        btnLogin.setAlpha(v);

        userName.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1000).start();
        password.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1200).start();
        forgetPass.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1500).start();
        btnLogin.animate().translationY(0).alpha(1).setDuration(1800).setStartDelay(1700).start();

        fb = findViewById(R.id.fab_fb);
        twitter = findViewById(R.id.fab_twitter);
        google = findViewById(R.id.fab_google);


        fb.setTranslationX(800);
        google.setTranslationX(800);
        twitter.setTranslationX(800);


        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);


        fb.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(1400).start();
        google.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(1600).start();
        twitter.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(1800).start();

    }
}