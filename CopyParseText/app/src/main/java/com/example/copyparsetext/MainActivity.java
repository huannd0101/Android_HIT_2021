package com.example.copyparsetext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;

import com.example.copyparsetext.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        // Sao chép

        binding.btnCopy.setOnClickListener(v -> {
            String copyText= binding.editText.getText().toString().trim();
            myClip = ClipData.newPlainText("text", copyText);
            myClipboard.setPrimaryClip(myClip);
        });

        // dán
        binding.btnParse.setOnClickListener(v -> {
            ClipData clipData = myClipboard.getPrimaryClip();
            ClipData.Item item = clipData .getItemAt(0);
            String parseText = item.getText().toString();

            binding.tv.setText(parseText);
        });

    }
}