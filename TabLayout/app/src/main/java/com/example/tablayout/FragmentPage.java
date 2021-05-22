package com.example.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tablayout.databinding.FragmentHomeBinding;
import com.example.tablayout.databinding.FragmentPageBinding;

public class FragmentPage extends Fragment {
    FragmentPageBinding binding;
    public static FragmentPage newInstance() {

        Bundle args = new Bundle();

        FragmentPage fragment = new FragmentPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_page,container,false);

        return binding.getRoot();
    }
}
