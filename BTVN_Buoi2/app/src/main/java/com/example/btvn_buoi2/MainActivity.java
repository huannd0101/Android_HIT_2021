package com.example.btvn_buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1.setOnClickListener(this); 
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn....
                //
                break;

        }
    }
}