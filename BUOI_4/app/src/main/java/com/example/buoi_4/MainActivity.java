package com.example.buoi_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btn1, btn2;
    String name;
    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt1.getText().toString();
                age = Integer.parseInt(edt2.getText().toString());

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtra("name", name);
                intent.putExtra("age", age);

                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(intent);
            }
        });
    }

    private void AnhXa(){
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btn1 = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
    }
}