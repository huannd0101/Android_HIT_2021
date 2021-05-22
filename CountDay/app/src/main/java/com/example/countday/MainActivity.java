package com.example.countday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    Button btn1;
    Calendar calendar, calendar1;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AnhXa();

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar1 = Calendar.getInstance();
                int result = (int) ((calendar1.getTimeInMillis() - calendar.getTimeInMillis()) / (1000*60*60*24));
                if(result > 0){
                    tv2.setText("Ngày xa em: " + result + " ngày");
                }else {
                    Toast.makeText(MainActivity.this, "Chọn lại ngày đi :v", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void ChonNgay(){
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONDAY);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        tv1.setText("Chọn ngày: " + simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }



    private void AnhXa(){
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        btn1 = findViewById(R.id.btn1);
    }
}