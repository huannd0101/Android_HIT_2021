package com.example.autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);

        autoCompleteTextView.setAdapter(adapter);

        adapter.addAll(getResources().getStringArray(R.array.arr));

    }
}