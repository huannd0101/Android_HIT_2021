package com.example.demo_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.demo_sqlite.DAO.UserDatabase;
import com.example.demo_sqlite.databinding.ActivityUpdateBinding;
import com.example.demo_sqlite.enteties.User;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        user = (User) getIntent().getExtras().get("obj_user");

        if(user != null){
            binding.edtUsername.setText(user.getName());
            binding.edtAddress.setText(user.getAddress());
        }

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String username = binding.edtUsername.getText().toString().trim();
        String address = binding.edtAddress.getText().toString().trim();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(address))
            return;

        //update user
        user.setName(username);
        user.setAddress(address);
        //không đc tạo user mới trong này để update
        UserDatabase.getInstance(this).userDAO().updateUser(user);
        Toast.makeText(this, "Update user successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}