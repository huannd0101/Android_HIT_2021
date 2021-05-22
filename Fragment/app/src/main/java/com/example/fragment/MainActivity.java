package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getFragment(fragment1.newInstance());

//        binding.fragment1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragment(fragment1.newInstance());
//            }
//        });
//
//        binding.fragment2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragment(fragment2.newInstance());
//            }
//        });
    }

    public void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment, fragment).commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        finish();
    }
}