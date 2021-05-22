package com.example.tablayout_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.os.CountDownTimer;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    String url = "https://apincov.herokuapp.com/countries";
    ArrayAdapter<String> arrayAdapterCountry;
    List<String> listCountry;
    View view;
    ImageButton btnSearch;
    AutoCompleteTextView autoCompleteTextView;
    RecyclerView rcView;
    ProgressBar progressBar;
    int tempCount;
    List<Country> countryList = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    public BlankFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2, String param3) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }

    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_blank, container, false);
        AnhXa();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        listCountry = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                int temp = 0;
                int i = 0;
                JSONObject jsonObject = null;
                listCountry.clear();
                do {
                    jsonObject = jsonArray.getJSONObject(i);
                    String nameCountry = jsonObject.getString("Country_Region");
                    listCountry.add(nameCountry);

                    if(nameCountry.compareToIgnoreCase("VietNam") == 0)
                        temp = i;

                    countryList.add(new Country(jsonObject.getString("Country_Region"),
                            jsonObject.getString("Confirmed"),
                            jsonObject.getString("Deaths"),
                            jsonObject.getString("Recovered")));

                    i++;
                }while(i < 130);

                if(countryList.size() != 0){
                    progressBar.setVisibility(View.INVISIBLE);
                }

                Country tempCountry = countryList.get(0);
                countryList.set(0, countryList.get(temp));
                countryList.set(temp, tempCountry);

                //gán auto text
                arrayAdapterCountry = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
                autoCompleteTextView.setAdapter(arrayAdapterCountry);
                arrayAdapterCountry.addAll(listCountry);
                ////

                CountryAdapter countryAdapter = new CountryAdapter(countryList, getContext());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

                rcView.setLayoutManager(layoutManager);
                rcView.setAdapter(countryAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String autoText = autoCompleteTextView.getText().toString().trim();
                    List<Country> tempList = new ArrayList<>();
                    for(Country i : countryList)
                        if(i.getCountry_Region().trim().toLowerCase().contains(autoText.toLowerCase()))
                            tempList.add(i);

                    tempCount = tempList.size();

                    CountryAdapter countryAdapter1 = new CountryAdapter(tempList, getContext());
                    RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rcView.setLayoutManager(layoutManager1);
                    rcView.setAdapter(countryAdapter1);
                }
            });
        }, error -> Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show());

        requestQueue.add(stringRequest);


        //-----------------------


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoCompleteTextView.getText() != null){
                    Toast.makeText(getContext(), "Có " + tempCount + " kết quả", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Bạn phải nhập trước :v", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //------------------------




        return view;
    }

    private void AnhXa(){
        autoCompleteTextView = view.findViewById(R.id.edtSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        rcView = view.findViewById(R.id.rcView);
        progressBar = view.findViewById(R.id.progressBar);
    }
}