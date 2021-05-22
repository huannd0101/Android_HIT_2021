package com.example.buoi7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.buoi7.databinding.LayoutFragment1Binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment1, container, false);

        Person person = new Person("Huân", "Lam Linh 1");

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Lam Linh", Toast.LENGTH_SHORT).show();
                binding.tv.setText("Lam Linh <3");
                //tạo person để sang fragment2


                //chuyển giữa 2 fragment
                Fragment fragment2 = Fragment2.newInstance(person, "Huân"); //tạo đối tượng fragment cần tới
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment2).addToBackStack(null).commit();

            }
        });



        return binding.getRoot();
    }
}
