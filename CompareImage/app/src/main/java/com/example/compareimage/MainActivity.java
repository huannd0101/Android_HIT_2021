package com.example.compareimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.img_bbbb);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.img_b);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(v -> {
            findDifference(bitmap1, bitmap2);

        });

    }

    private void findDifference(Bitmap firstImage, Bitmap secondImage) {
        Bitmap bmp = secondImage.copy(secondImage.getConfig(), true);

        if (firstImage.getWidth() != secondImage.getWidth()
                || firstImage.getHeight() != secondImage.getHeight()) {
            Toast.makeText(getApplicationContext(), "Nooo", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < firstImage.getWidth(); i++) {
            for (int j = 0; j < firstImage.getHeight(); j++) {
                if (firstImage.getPixel(i, j) != secondImage.getPixel(i, j)) {
                    bmp.setPixel(i, j, Color.RED);
                }
            }
        }

        imageView.setImageBitmap(bmp);
    }
}