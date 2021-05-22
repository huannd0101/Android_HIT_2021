package com.example.menupopup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    Button btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });
    }

    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        //bắt sự kiện
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menuThem:
                        btnMenu.setText("Thêm");
                        break;
                    case R.id.menuSua:
                        btnMenu.setText("Sửa");
                        break;
                    case R.id.menuXoa:
                        btnMenu.setText("Xóa");
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}