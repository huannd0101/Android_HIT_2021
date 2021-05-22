package com.example.texttospeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv = findViewById(R.id.tv);

        String content =  getIntent().getStringExtra("content");
        if(content != null){
            tv.setText(content);
        }
    }
}