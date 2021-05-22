package com.example.dropanddroprecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rclView;
    ItemAdapter itemAdapter;
    List<String> stringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataItem();

        rclView = findViewById(R.id.rclView);

        itemAdapter = new ItemAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rclView.setLayoutManager(layoutManager);

        itemAdapter.setData(stringList);

        rclView.setAdapter(itemAdapter);

        //tạo đường kẻ giữa các item
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rclView.addItemDecoration(divider);

        //drop and drag item in recyclerview
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                //đổi tên viewHolder tẹo cho dễ hình dung :v
                int posDrag = dragged.getAdapterPosition();
                int posTarget = target.getAdapterPosition();

                Collections.swap(stringList, posDrag, posTarget);

                itemAdapter.notifyItemMoved(posDrag, posTarget);

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(rclView);
    }

    private void getDataItem(){
        stringList = new ArrayList<>();
        for(int i=0; i<20; i++){
            stringList.add("item " + i);
        }
    }
}