package com.example.alertdialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> arr;
    ArrayAdapter adapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lv = findViewById(R.id.lv);
        arr = new ArrayList<>();
        arr.add("Nguyễn Đình Huân 1");
        arr.add("Nguyễn Đình Huân 2");
        arr.add("Nguyễn Đình Huân 3");
        arr.add("Nguyễn Đình Huân 4");
        arr.add("Nguyễn Đình Huân 5");
        arr.add("Nguyễn Đình Huân 6");
        arr.add("Nguyễn Đình Huân 7");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XacNhanXoa(position);
            }
        });
    }

    private void XacNhanXoa(int i){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xóa item này hay không???");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arr.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Bạn đã chọn không xóa", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }












}