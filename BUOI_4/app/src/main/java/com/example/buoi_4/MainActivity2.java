package com.example.buoi_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv1, tv2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AnhXa();

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);

        tv1.setText(name);
        tv2.setText(age+"");
    }

    private void AnhXa(){
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
    }
}