package com.example.intentexplicit3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String[] a = intent.getStringArrayExtra("arr");

        String temp = "";
        for(int i=0; i<a.length; i++)
            temp += a[i] + " - ";

        textView.setText(temp);
    }
}