package com.example.dathang;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView rcHangHoaOrder;
    List<HangHoa> list = new ArrayList<>();
    Button btnOrder1, btnHuyBoTotal1;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnBack = findViewById(R.id.btnBack);
        btnHuyBoTotal1 = findViewById(R.id.btnHuyBoTotal1);
        btnOrder1 = findViewById(R.id.btnOrder1);
        rcHangHoaOrder = findViewById(R.id.rcHangHoaOrder);

        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("listHangHoa");

        HangHoaAdapter adapter = new HangHoaAdapter(list, MainActivity3.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity3.this, RecyclerView.VERTICAL, false);


        rcHangHoaOrder.setLayoutManager(layoutManager);
        rcHangHoaOrder.setAdapter(adapter);

        btnOrder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3.this, "Ra cổng lấy hàng đi :v", Toast.LENGTH_SHORT).show();
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnHuyBoTotal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3.this, "Nhịn đói rồi :)", Toast.LENGTH_SHORT).show();
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity3.this, MainActivity2.class);
                intent1.putParcelableArrayListExtra("listOrder", (ArrayList<? extends Parcelable>) list);

                startActivity(intent1);
            }
        });
    }
}