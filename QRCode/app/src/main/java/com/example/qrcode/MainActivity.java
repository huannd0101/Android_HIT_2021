package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qrcode.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.QRCode.setOnClickListener(v -> {
            openScannerActivity();
        });




    }
    private void openScannerActivity() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Quét mã QR");// hướng dẫn
        integrator.setOrientationLocked(true );
        integrator.setTimeout(30000);//giới hạn thời gian quét
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // không quét đc mã QR hoặc không được cấp phép truy cập CAMERA
                Toast.makeText(this, "Hoàng ăn cứt", Toast.LENGTH_SHORT).show();
            }
            else {
                String content=result.getContents();// nội dung mã QR
                String format=result.getFormatName();//định dạng của mã QR
                binding.QRCode.setText(content);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}