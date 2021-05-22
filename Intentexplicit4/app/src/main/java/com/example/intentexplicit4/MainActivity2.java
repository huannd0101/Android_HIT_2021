package com.example.intentexplicit4;

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
        Student student = (Student) intent.getSerializableExtra("obj");
        tv.setText(student.getName() + " - " + student.getAge());

    }
}