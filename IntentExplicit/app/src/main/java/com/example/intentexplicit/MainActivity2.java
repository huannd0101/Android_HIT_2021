package com.example.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv1;
    EditText edt1;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.tv1);
        edt1 = findViewById(R.id.edt1);
        btn2 = findViewById(R.id.btn2);

        Intent intent = getIntent();

        String datas = intent.getStringExtra("datastring");

        tv1.setText("Nội dung là: " + datas);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);

                intent1.putExtra("redata", edt1.getText().toString());

                startActivity(intent1);
            }
        });
    }
}