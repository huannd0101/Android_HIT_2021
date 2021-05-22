package com.example.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOpenBottomSheetDialog();
            }
        });
    }

    private void clickOpenBottomSheetDialog() {
        //Nếu nội dung trong layout dài quá thì dùng NestedScrollView
        View view = getLayoutInflater().inflate(R.layout.buttom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        //sets k cho tắt bằng các cách thông thường
        bottomSheetDialog.setCancelable(false);
        //tắt bằng button bên trong
        Button btnCancel, btnPayment;
        btnCancel = view.findViewById(R.id.btnCancel);
        btnPayment = view.findViewById(R.id.btnPayment);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click btnPayment", Toast.LENGTH_SHORT).show();
            }
        });

        //Nổi che hết màn hình
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}