package com.example.swiperefreshlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rclView;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rclView = findViewById(R.id.rclView);
        swipeRefreshLayout = findViewById(R.id.sfl);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rclView.setLayoutManager(linearLayoutManager);

        adapter = new ItemAdapter();
        adapter.setData(getListItem());

        rclView.setAdapter(adapter);

        //tạo viền
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rclView.addItemDecoration(decoration);

        //tạo phần refresh
        swipeRefreshLayout.setOnRefreshListener(this);

        //custom màu sắc
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.design_default_color_primary));
    }

    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        for(int i=0; i<20; i++)
            list.add(new Item("Item + " + String.valueOf(i)));
        return list;
    }

    @Override
    public void onRefresh() {
        //tạo thử 3s
        List<Item> list = getListItem();
        list.add(0, new Item("Huân"));
        adapter.setData(list);
//        adapter.notifyDataSetChanged(); //trong setData có rồi nên k cần thiết
        //trong setData đã có notifyDataSetChange
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                //Dừng lại sau khi xong
            }
        }, 3000);

    }
}