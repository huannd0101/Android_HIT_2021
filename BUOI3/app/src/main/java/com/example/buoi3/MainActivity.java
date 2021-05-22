package com.example.buoi3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Spinner spinner;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //tắt thanh tiêu đề lớn
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


//
//        tv = findViewById(R.id.tv);
//
//        tv.setBackgroundColor(getResources().getColor().);
        spinner = findViewById(R.id.spinner);

        String[] arr2 = {"Huân", "Huân 2"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, arr2);
        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), arr2[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    //////////////

        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDiaglogShow();

                //
//                DialogCustome dialogCustome = new DialogCustome((MainActivity.this));
//                dialogCustome.show();

                //

//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent);
            }
        });


    }

    public void onDiaglogShow(){
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Xác nhận")
                .setMessage("Yes or No")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Đồng ý", Toast.LENGTH_LONG).show();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Chịu :))", Toast.LENGTH_LONG).show();
                    }
                }).create();

        dialog.show();
    }
}