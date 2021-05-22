package com.example.pressbackagain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    long backPressTime;
    Toast mToast; //Khai báo toàn cục để có thể tắt toast khi exit app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //override lại hàm

    @Override
    public void onBackPressed() {

        if(backPressTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
            super.onBackPressed();
            return;
        }else {
            mToast = Toast.makeText(this, "Press back again to exit the application", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
}