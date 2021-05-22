package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox cbtoan, cbvan;
    Button btnkq;
    TextView tvkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbtoan = (CheckBox) findViewById(R.id.cbtoan);
        cbvan = (CheckBox) findViewById(R.id.cbvan);
        btnkq = (Button) findViewById(R.id.btnkq);
        tvkq = (TextView) findViewById(R.id.tvkq);

//        if(cbtoan.isChecked())
//            tvkq.setText("huân học toán");
//        else
//            tvkq.setText("huân học văn");

        cbtoan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                tvkq.setText("huân học toán");
                if(cbtoan.isChecked())
                    tvkq.setText("huân học toán");
                else
                    tvkq.setText("huân học văn");
            }
        });
    }
}