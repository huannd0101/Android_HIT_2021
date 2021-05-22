package com.example.fragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.databinding.SellfragmentLayoutBinding;

public class SellFragment extends Fragment {
    SellfragmentLayoutBinding binding;
    public static SellFragment newInstance() {

        Bundle args = new Bundle();

        SellFragment fragment = new SellFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.sellfragment_layout,container,false);
        return binding.getRoot();
    }
}
