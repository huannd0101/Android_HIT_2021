package com.example.seekbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1, cb2, cb3;
    ImageButton imgbtn1;
    SeekBar sk1, sk2, sk3;
    Switch swt1;
    RelativeLayout background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AnhXa();

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random rd = new Random();
                int rd1 = rd.nextInt(4);
                int rd2 = rd.nextInt(4);
                int rd3 = rd.nextInt(4);
                sk1.setProgress(sk1.getProgress() + rd1);
                sk2.setProgress(sk2.getProgress() + rd2);
                sk3.setProgress(sk3.getProgress() + rd3);
                //check win
                if(sk1.getMax() <= sk1.getProgress()){
                    Toast.makeText(MainActivity.this, "Player one WIN", Toast.LENGTH_SHORT).show();
                    imgbtn1.setVisibility(View.VISIBLE);
                    if(cb1.isChecked()){
                        Toast.makeText(MainActivity.this, "Bạn giỏi như Huân vậy :v", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn chơi dở thế", Toast.LENGTH_LONG).show();
                    }
                    this.cancel();

                    EnableCheckBox();
                }
                if(sk2.getMax() <= sk2.getProgress()){
                    Toast.makeText(MainActivity.this, "Player two WIN", Toast.LENGTH_SHORT).show();
                    imgbtn1.setVisibility(View.VISIBLE);
                    if(cb2.isChecked()){
                        Toast.makeText(MainActivity.this, "Bạn giỏi như Huân vậy :v", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn chơi dở thế", Toast.LENGTH_LONG).show();
                    }
                    this.cancel();

                    EnableCheckBox();
                }
                if(sk3.getMax() <= sk3.getProgress()){
                    Toast.makeText(MainActivity.this, "Player three WIN", Toast.LENGTH_SHORT).show();
                    imgbtn1.setVisibility(View.VISIBLE);
                    if(cb3.isChecked()){
                        Toast.makeText(MainActivity.this, "Bạn giỏi như Huân vậy :v", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn chơi dở thế", Toast.LENGTH_LONG).show();
                    }
                    this.cancel();

                    EnableCheckBox();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    imgbtn1.setVisibility(View.INVISIBLE);
                    sk1.setProgress(5);
                    sk2.setProgress(5);
                    sk3.setProgress(5);
                    DisEnableCheckBox(); //tắt chọn checkbox khác
                    DisEnableSeekBar(); //tắt tác động đến seekBar
                    countDownTimer.start();
                }else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn một đối tượng trước :v", Toast.LENGTH_LONG).show();
                }
            }
        });

        swt1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    background.setBackgroundResource(R.drawable.mountain);
                }else {
                    background.setBackgroundResource(R.drawable.mountains2);
                }
            }
        });

    }

    private void AnhXa() {
        imgbtn1 = findViewById(R.id.imgbtn1);
        sk1 = findViewById(R.id.sk1);
        sk2 = findViewById(R.id.sk2);
        sk3 = findViewById(R.id.sk3);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        swt1 = findViewById(R.id.swt1);
        background = findViewById(R.id.background);
    }

    private void EnableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    private void DisEnableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }
    private void EnableSeekBar(){
        sk1.setEnabled(true);
        sk2.setEnabled(true);
        sk3.setEnabled(true);
    }
    private void DisEnableSeekBar(){
        sk1.setEnabled(false);
        sk2.setEnabled(false);
        sk3.setEnabled(false);
    }
}