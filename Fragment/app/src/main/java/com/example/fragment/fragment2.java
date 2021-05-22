package com.example.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fragment.databinding.LayoutFragment2Binding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fragment2 extends Fragment {
    LayoutFragment2Binding binding;
    public static fragment2 newInstance(String name, Student student, List<Student> list) {

        Bundle args = new Bundle();
        //truyền dữ liệu giữa 2 fragment
        args.putString("name", name);
        args.putParcelable("obj", student);
        args.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);

        fragment2 fragment = new fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.layout_fragment2, container, false);
//        return view;
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment2, container, false);


        binding.btnMoveFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fragment1.newInstance(); //fragment đích đến
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_fragment, fragment).addToBackStack(null).commit();
            }
        });
        //nhận dữ liệu từ fragment1 chuyển sang
        String name = getArguments().getString("name");
        Student student = getArguments().getParcelable("obj");
        List<Student> list = getArguments().getParcelableArrayList("list");
        Toast.makeText(getContext(), Arrays.toString(new List[]{list}), Toast.LENGTH_SHORT).show();

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(getContext());
    }


}
