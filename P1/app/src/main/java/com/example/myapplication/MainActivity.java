package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    Button btnCheck;
    EditText edtNumber;
    ImageView ivView;
    RelativeLayout rltLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int a = random.nextInt(100);
//                tvDisplay.setText(edtNumber.getText().toString());
//                Toast.makeText(MainActivity.this, edtNumber.getText().toString(), Toast.LENGTH_LONG).show();
                tvDisplay.setText(a + "");
                ivView.setImageResource(R.drawable.ic_launcher_background);
//                rltLayout.setBackgroundResource(R.drawable.img);
                rltLayout.setBackgroundColor(Color.BLACK);
            }
        });




    }

    private void AnhXa() {
        btnCheck = (Button)findViewById(R.id.btnCheck);
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        tvDisplay = (TextView)findViewById(R.id.tvDisplay);
        ivView = findViewById(R.id.ivView);
        rltLayout = (RelativeLayout) findViewById(R.id.rltLayout);
    }
}