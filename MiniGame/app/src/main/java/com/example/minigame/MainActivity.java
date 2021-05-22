package com.example.minigame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ImageView imgView1, imgView2;
    public static List<String> listName; //khai báo public static để màn hình khác cx có thể dùng
    int REQUEST_CODE = 123;
    String imgNameOfView1 = "";
    TextView tvTotal;
    int total = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        imgView1 = findViewById(R.id.imgView1);
        imgView2 = findViewById(R.id.imgView2);
        tvTotal = findViewById(R.id.tvTotal);


        String[] ArrName = getResources().getStringArray(R.array.list_name); //lấy list name từ values-string

        listName = new ArrayList<>(Arrays.asList(ArrName)); //khai báo mảng mang các giá trị của list name từ values-string

        Collections.shuffle(listName); // trộn mảng sau mỗi lần chạy

        imgNameOfView1 = listName.get(0);

        int idHinh = getResources().getIdentifier(imgNameOfView1, "drawable", getPackageName());//chuyển một cái tên ở trong drawable thành id

        imgView1.setImageResource(idHinh);

        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        tvTotal.setText(String.valueOf(100));
    }

    //sử lý kết quả của dialog truyền về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            String hinh = data.getStringExtra("hinhDuocChon");
            int idImage = getResources().getIdentifier(hinh, "drawable", getPackageName());
            imgView2.setImageResource(idImage);
            if(imgNameOfView1.compareTo(hinh) == 0){
                Toast.makeText(this, "Bạn giỏi quá <3\n+10đ", Toast.LENGTH_SHORT).show();

                //đúng thì cộng điểm
                total += 10;

                //nếu đúng thì sau 2s đổi hình khác
                CountDownTimer countDownTimer = new CountDownTimer(1500, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Collections.shuffle(listName);
                        imgNameOfView1 = listName.get(0);
                        int idHinh = getResources().getIdentifier(imgNameOfView1, "drawable", getPackageName());
                        imgView1.setImageResource(idHinh);
                    }
                };
                countDownTimer.start();
            }
            else{
                //sai thì trừ điểm
                Toast.makeText(this, "Bạn sai rồi :v\n-10đ", Toast.LENGTH_SHORT).show();
                total -= 10;
            }
            tvTotal.setText(String.valueOf(total));
        }

        //nếu k chọn gì bằng cách kiểm tra RESULT_CANCELED
        if(requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Bạn chưa chọn\n-20đ", Toast.LENGTH_SHORT).show();
            //trừ 20 điểm khi chưa chọn
            total -= 20;
            tvTotal.setText(String.valueOf(total));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //tạo menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //bắt sự kiện của menu item

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuReload){
            Collections.shuffle(listName);
            imgNameOfView1 = listName.get(0);
            int idHinh = getResources().getIdentifier(imgNameOfView1, "drawable", getPackageName());
            imgView1.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }
}