package com.example.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        tv1 = findViewById(R.id.tv1);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                //truyền dữ liệu với tên là data
                intent.putExtra("datastring", "Nguyễn Đình Huân");

                startActivity(intent);
            }
        });

        Intent intent1 = getIntent();
        String temp = intent1.getStringExtra("redata");
        if(temp != null)
            tv1.setText("Re-data: " + temp);
        else
            tv1.setText("");
    }
}