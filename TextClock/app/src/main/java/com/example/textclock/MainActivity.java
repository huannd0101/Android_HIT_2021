package com.example.textclock;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {
    TextClock textclock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textclock = findViewById(R.id.textclock);
        textclock.setAllCaps(false);

    }
}