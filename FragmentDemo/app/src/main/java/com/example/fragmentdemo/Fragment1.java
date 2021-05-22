package com.example.fragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fragmentdemo.databinding.LayoutFragment1Binding;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    LayoutFragment1Binding binding;
    public static Fragment1 newInstance() {

        Bundle args = new Bundle();

        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.layout_fragment1,container,false);

        binding.btnMoveToFr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Person> list = new ArrayList<>();
                list.add(new Person("Điệp",21));
                list.add(new Person("Doan",21));
                list.add(new Person("Trung",21));
                list.add(new Person("Linh",21));
                Fragment fragment = Fragment2.newInstance("",new Person("Điệp",21),list);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_fragment,fragment).addToBackStack(null).commit();
            }
        });
        return binding.getRoot();
    }
}
