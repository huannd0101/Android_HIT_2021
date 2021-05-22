package com.example.bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle = new Bundle();

                String[] a = {"String 1", "String 2", "String 3"};
                Student student = new Student("Nguyen Dinh Huan", 20);

                bundle.putString("dataString", "Đây là data String");
                bundle.putInt("dataInt", 1234);
                bundle.putStringArray("dataArr", a);
                bundle.putSerializable("obj", student);

                intent.putExtra("duLieu", bundle);

                startActivity(intent);
            }
        });

    }
}