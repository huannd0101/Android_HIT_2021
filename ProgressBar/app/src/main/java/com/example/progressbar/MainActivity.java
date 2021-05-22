package com.example.progressbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbXuLy;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AnhXa();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = pbXuLy.getProgress();
                        pbXuLy.setProgress(current + 10);
                        if(current >= pbXuLy.getMax())
                                pbXuLy.setProgress(0);
                    }

                    @Override
                    public void onFinish() {
                        this.start();
                    }
                }.start();
            }
        });

    }


    public void AnhXa(){
        pbXuLy = findViewById(R.id.pbXuLy);
        btn1 = findViewById(R.id.btn1);
    }
}