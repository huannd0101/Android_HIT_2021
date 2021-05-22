package com.example.medialocalsound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.khong_phai_em_dung_khong);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mediaPlayer.start(); //phát
//                mediaPlayer.stop(); //dừng
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mediaPlayer.stop();
            }
        });

    }
}