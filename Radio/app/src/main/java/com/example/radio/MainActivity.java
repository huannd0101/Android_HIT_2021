package com.example.radio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup grRadio;
    LinearLayout manHinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RadioButton cx có phương thức isChecked() như CheckBox
        grRadio = (RadioGroup) findViewById(R.id.grRadio);
        manHinh = (LinearLayout) findViewById(R.id.manhinh);

        grRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case 1:
                        manHinh.setBackgroundResource(R.drawable.ic_launcher_background);
                        Toast.makeText(MainActivity.this, "Haizzzzzzz", Toast.LENGTH_LONG);
                        break;
                    case 2:
                        manHinh.setBackgroundResource(R.drawable.ic_launcher_foreground);
                        break;
                }
            }
        });
    }
}