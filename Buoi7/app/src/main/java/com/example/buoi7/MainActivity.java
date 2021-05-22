package com.example.buoi7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.buoi7.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getFragment(Fragment1.newInstance());




    }

    public void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }
}