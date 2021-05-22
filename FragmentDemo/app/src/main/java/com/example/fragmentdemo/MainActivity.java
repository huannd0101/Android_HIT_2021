package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragmentdemo.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        getFragment(Fragment1.newInstance());
//        binding.btnFragment1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragment(Fragment1.newInstance());
//            }
//        });
//        binding.btnFragment2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragment(Fragment2.newInstance());
//            }
//        });

    }
    public void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment,fragment).commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCloseApp(ECloseApp eCloseApp){
        //do something here
        finish();
    }
}