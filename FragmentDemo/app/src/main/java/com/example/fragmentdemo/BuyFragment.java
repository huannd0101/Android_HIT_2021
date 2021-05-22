package com.example.fragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.fragmentdemo.databinding.BuyfragmentLayoutBinding;

public class BuyFragment extends Fragment {
    BuyfragmentLayoutBinding binding;
    public static BuyFragment newInstance() {

        Bundle args = new Bundle();

        BuyFragment fragment = new BuyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.buyfragment_layout,container,false);
        return binding.getRoot();
    }
}
