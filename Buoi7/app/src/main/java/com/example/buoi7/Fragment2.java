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

import com.example.buoi7.databinding.LayoutFragment2Binding;

public class Fragment2 extends Fragment {
    LayoutFragment2Binding binding;

    public static Fragment2 newInstance(Person person, String name) {

        Bundle args = new Bundle();

        args.putParcelable("ll", person);
        args.putString("name", name);


        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment2, container, false);

        Person person = getArguments().getParcelable("ll");

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = Fragment1.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment1).addToBackStack(null).commit();

                String name = getArguments().getString("name");
                binding.tv2.setText("Name: " + person.getName() + "\nName of lover: " + person.getNameOfLover());
                Toast.makeText(getContext(), person.getNameOfLover(), Toast.LENGTH_SHORT).show();

            }
        });

        return binding.getRoot();
    }
}
