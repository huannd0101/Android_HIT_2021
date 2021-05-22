package com.example.fragmentdemo;

import android.content.Intent;
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

import com.example.fragmentdemo.databinding.LayoutFragment2Binding;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    LayoutFragment2Binding binding;
    public static Fragment2 newInstance(String name, Person p, List<Person> list) {

        Bundle args = new Bundle();
        args.putString("name",name);
        args.putParcelable("object",p);
        args.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.layout_fragment2,container,false);
//        return view;
        binding = DataBindingUtil.inflate(inflater,R.layout.layout_fragment2,container,false);
        binding.btnMovetoFr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = Fragment1.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_fragment,fragment)
                        .addToBackStack(null).commit();
            }
        });

        String name = getArguments().getString("name");
//        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        Person p = getArguments().getParcelable("object");
//        Toast.makeText(getContext(), p.getName()+"\n"+ p.getAge(), Toast.LENGTH_SHORT).show();
        List<Person> list = getArguments().getParcelableArrayList("list");
        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        binding.btnStarActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity3.class);
                startActivity(intent);
//                EventBus.getDefault().post(new ECloseApp());
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
