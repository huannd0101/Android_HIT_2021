package com.example.minigame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity2 extends Activity {
    TableLayout table;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Collections.shuffle(MainActivity.listName);

        table = findViewById(R.id.table);

        int soDong = 6;
        int soCot = 3;
        //tạo dòng và tạo cột
        for(int i=1; i<=soDong; i++){
            TableRow tableRow = new TableRow(this);

            //kích thước (google)
            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());

            //tạo cột - ImageView
            for(int j=1; j<=soCot; j++){
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px, px);
                imageView.setLayoutParams(layoutParams); // lấy kích thước của nó đc khai bap

                //lấy vị trí của từng hình
                int pos = soCot*(i-1) + j -1;

                int idHinh = getResources().getIdentifier(MainActivity.listName.get(pos), "drawable", getPackageName());
                imageView.setImageResource(idHinh);
                //add ImageView vào tableRow
                tableRow.addView(imageView);

                //click
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //trả về màn hình trước kết quả được click
                        Intent intent = new Intent();
                        intent.putExtra("hinhDuocChon", MainActivity.listName.get(pos));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

            }
            //add tableRow vào table
            table.addView(tableRow);
        }

    }
}