package com.example.btvn_buoi5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    Pattern pattern;
    String regUserName = "^[a-zA-Z0-9]{5,}$";
    String regPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();



    }

    private void AnhXa() {

    }
}