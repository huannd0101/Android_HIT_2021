package com.example.btvn_buoi4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView rcHangHoa;
    List<HangHoa> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();

        list.add(new HangHoa(1, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));
        list.add(new HangHoa(2, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));
        list.add(new HangHoa(3, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));
        list.add(new HangHoa(4, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));
        list.add(new HangHoa(5, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));
        list.add(new HangHoa(6, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));
        list.add(new HangHoa(7, R.drawable.bananas, "Chuối", "Chuối chín ngon", 100, 0));

        HangHoaAdapter adapter = new HangHoaAdapter(list, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcHangHoa.setLayoutManager(layoutManager);
        rcHangHoa.setAdapter(adapter);
    }

    private void AnhXa(){
        rcHangHoa = findViewById(R.id.rcHangHoa);
    }


}