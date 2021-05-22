package com.example.integratepythonwithandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Wave;

public class FirstScreen extends AppCompatActivity {
    ProgressBar loading;
    int progressBarStatus = 0;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        getSupportActionBar().hide();
        loading = findViewById(R.id.loading);
        loading.setIndeterminateDrawable(new Wave());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus<100){
                    progressBarStatus++;
                    SystemClock.sleep(100);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loading.setProgress(progressBarStatus);
                        }
                    });
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(progressBarStatus == 100){
                                Intent intent = new Intent(FirstScreen.this, SecondScreen.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        }).start();

    }


}