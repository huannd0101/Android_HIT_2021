package com.example.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tablayout.databinding.FragmentFavoriteBinding;
import com.example.tablayout.databinding.FragmentHomeBinding;

public class FragmentFavorite extends Fragment {
    FragmentFavoriteBinding binding;
    public static FragmentFavorite newInstance() {

        Bundle args = new Bundle();

        FragmentFavorite fragment = new FragmentFavorite();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false);

        return binding.getRoot();
    }
}
