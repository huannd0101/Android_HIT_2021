package com.example.ncovid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ncovid.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String url = "https://apincov.herokuapp.com/countries";
    ArrayAdapter<String> arrayAdapterCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = null;
        List<String> listCountry = new ArrayList<>();



        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    JSONObject jsonObject = null;
                    int i = 0;
                    do {
                        jsonObject = jsonArray.getJSONObject(i);
                        i++;

                        listCountry.add(jsonObject.getString("Country_Region"));

                    }while(i < 150);
//                    1215

                    //gán auto text
                    arrayAdapterCountry = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);

                    binding.edtSearch.setAdapter(arrayAdapterCountry);

                    arrayAdapterCountry.addAll(listCountry);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, binding.edtSearch.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}