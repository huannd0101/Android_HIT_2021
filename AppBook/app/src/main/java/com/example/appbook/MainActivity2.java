package com.example.appbook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {
    TextView tvTitle, tvAuthor, tvPrice, tvRateStar, tvContentDiscription, tvNumOfReview, tvCategoty, tvNumOfPage;
    ImageView imgView;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvPrice = findViewById(R.id.tvPrice);
        tvRateStar = findViewById(R.id.tvRateStar);
        tvContentDiscription = findViewById(R.id.tvContentDiscription);
        tvNumOfReview = findViewById(R.id.tvNumOfReview);
        tvCategoty = findViewById(R.id.tvCategoty);
        tvNumOfPage = findViewById(R.id.tvNumOfPage);
        imgView = findViewById(R.id.imgView);

        Intent intent = getIntent();

        Book book = intent.getParcelableExtra("obj");
        Glide.with(this)
                .load(book.getImageLink())
                .into(imgView);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());

//        tvPrice.setText(book.getPrice().intValue() + "đ");

        double temp = 0;
        try {
            temp = 1.0*book.getRateStar()/book.getNumOfReview();
        }catch (Exception e){
            temp = 0;
        }

        tvRateStar.setText(String.valueOf(temp));
        tvContentDiscription.setText(book.getDescription());
        tvNumOfReview.setText(String.valueOf(book.getNumOfReview()));
        tvCategoty.setText(book.getCategoty());
        tvNumOfPage.setText(String.valueOf(book.getNumOfPage()));


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}