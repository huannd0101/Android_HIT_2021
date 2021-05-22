package com.example.aswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout manHinh;
    Switch swtWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swtWifi = (Switch) findViewById(R.id.swtWifi);
        manHinh = (LinearLayout) findViewById(R.id.manHinh);

        swtWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swtWifi.isChecked()){
                    manHinh.setBackgroundResource(R.drawable.ic_launcher_background);
                }else
                    manHinh.setBackgroundResource(R.drawable.ic_launcher_foreground);
            }
        });
    }
}