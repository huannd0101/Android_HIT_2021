package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnMenu;
    ConstraintLayout cstLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        cstLayout = findViewById(R.id.cstLayout);
        btnMenu = findViewById(R.id.btnMenu);

        //đăng ký view cho context menu
        registerForContextMenu(btnMenu);
    }

    //khởi tạo
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //lấy menu truyền vào menu vừa khởi tạo, và menu của ContextMenu bên trên đối số
        getMenuInflater().inflate(R.menu.menu_context, menu);

        menu.setHeaderTitle("Chọn màu cho nền");
        menu.setHeaderIcon(R.mipmap.ic_launcher_round);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //bắt sự kiện

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.red:
                cstLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.black:
                cstLayout.setBackgroundColor(Color.BLACK);
                break;
            case R.id.blue:
                cstLayout.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }
}