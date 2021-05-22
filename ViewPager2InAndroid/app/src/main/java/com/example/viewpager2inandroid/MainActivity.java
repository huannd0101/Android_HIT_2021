package com.example.viewpager2inandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.viewpager2inandroid.adapter.ViewPage2Adapter;
import com.example.viewpager2inandroid.pageTransformer.DepthPageTransformer;
import com.example.viewpager2inandroid.pageTransformer.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPage2;
    RadioGroup radioGroup1, radioGroup2, radioGroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        init();

        radioGroup1.check(R.id.rdb_ltr);
        radioGroup2.check(R.id.rdb_horizontal);
        radioGroup3.check(R.id.rdb_normal);

        //sét adapter cho viewPage2
        ViewPage2Adapter viewPage2Adapter = new ViewPage2Adapter(this);
        viewPage2.setAdapter(viewPage2Adapter);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            //Đang lỗi phần này
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_ltr:
                        viewPage2.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                        break;
                    case R.id.rdb_rtl:
                        viewPage2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_horizontal:
                        viewPage2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        break;
                    case R.id.rdb_vertical:
                        viewPage2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                }
            }
        });

    radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_normal:
                        viewPage2.setPageTransformer(null);
                        break;
                    case R.id.rdb_depth:
                        viewPage2.setPageTransformer(new DepthPageTransformer());
                        break;
                    case R.id.rdb_zoomOut:
                        viewPage2.setPageTransformer(new ZoomOutPageTransformer());
                        break;
                }
            }
        });

    }

    public void init() {
        //phần code trong packet pageTransformer thì lên mạng cop: https://developer.android.com/training/animation/screen-slide-2#java
        viewPage2 = findViewById(R.id.viewPage2);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);


    }
}