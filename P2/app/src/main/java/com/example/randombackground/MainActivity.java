package com.example.randombackground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnChange;
    RelativeLayout rltLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        List<Integer> a = new ArrayList<>();
        a.add(R.drawable.img_one);
        a.add(R.drawable.img_two);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                rltLayout.setBackgroundResource(a.get(random.nextInt(a.size())));
            }
        });
    }

    private void AnhXa() {
        btnChange = (Button) findViewById(R.id.btnChange);
        rltLayout = (RelativeLayout) findViewById(R.id.manHinh);
    }
}