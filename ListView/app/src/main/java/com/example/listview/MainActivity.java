package com.example.listview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvLover;
    EditText edt1;
    Button btn1, btn2;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edt1 = findViewById(R.id.edt1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        lvLover = findViewById(R.id.lvLover);

        List<String> a = new ArrayList<>();
        a.add("Nguyễn Đình Huân 1");
        a.add("Nguyễn Đình Huân 2");
        a.add("Nguyễn Đình Huân 3");
        a.add("Nguyễn Đình Huân 4");
        a.add("Nguyễn Đình Huân 4");
        a.add("Nguyễn Đình Huân 1");
        a.add("Nguyễn Đình Huân 2");


        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, a);

        lvLover.setAdapter(arrayAdapter);

        lvLover.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, a.get(position), Toast.LENGTH_SHORT).show();
                edt1.setText(a.get(position));
                pos = position;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText() != null){
                    a.set(pos, edt1.getText().toString());
                    edt1.setText(null);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString() != null){
                    a.add(edt1.getText().toString());
                    edt1.setText(null);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        lvLover.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                a.remove(position);
                edt1.setText(null);
                //cập nhập lại dữ liệu
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}