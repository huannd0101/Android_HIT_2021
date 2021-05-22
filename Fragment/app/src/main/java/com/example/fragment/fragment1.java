package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fragment.databinding.LayoutFragment1Binding;

import java.util.ArrayList;
import java.util.List;

public class fragment1 extends Fragment {
    LayoutFragment1Binding binding;
    public static fragment1 newInstance() {

        Bundle args = new Bundle();

        fragment1 fragment = new fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.layout_fragment1, container, false);
//        return view;


        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment1, container, false);

        binding.btnMoveFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student("Huân đẹp trai", 20);
                List<Student> list = new ArrayList<>();
                list.add(new Student("Huân 1", 20));
                list.add(new Student("Huân 2", 21));
                list.add(new Student("Huân 3", 22));
                list.add(new Student("Huân 4", 23));
                list.add(new Student("Huân 5", 24));

                Fragment fragment = fragment2.newInstance("Nguyễn Đình Huân", student, list); //fragment đích cần tới
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_fragment, fragment).addToBackStack(null).commit();
            }
        });








        return binding.getRoot(); //trả về view
    }
}
