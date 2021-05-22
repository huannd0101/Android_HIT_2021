package com.example.bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.tv);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("duLieu");

        String temp1 = bundle.getString("dataString");
        int temp2 = bundle.getInt("dataInt");
        Student temp3 = (Student) bundle.getSerializable("obj");
        String[] temp4 = bundle.getStringArray("dataArr");

        tv.setText(temp4[2]);
    }
}