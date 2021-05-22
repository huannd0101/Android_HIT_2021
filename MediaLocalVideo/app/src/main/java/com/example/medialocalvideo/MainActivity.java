package com.example.medialocalvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button button;
    VideoView vdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        vdView = findViewById(R.id.vdView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vdView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

                vdView.start();

                //tạo thanh chỉnh thời gian
                MediaController mediaController = new MediaController(MainActivity.this);
                mediaController.setMediaPlayer(vdView);

                vdView.setMediaController(mediaController);
            }
        });
    }
}