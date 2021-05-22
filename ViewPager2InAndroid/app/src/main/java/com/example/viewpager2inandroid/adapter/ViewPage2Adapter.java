package com.example.viewpager2inandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.viewpager2inandroid.fragment.HomeFragment;
import com.example.viewpager2inandroid.fragment.NotifyFragment;
import com.example.viewpager2inandroid.fragment.PageFragment;

public class ViewPage2Adapter extends FragmentStateAdapter {
    public ViewPage2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new PageFragment();
            case 2:
                return new NotifyFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
