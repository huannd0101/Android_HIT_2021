package com.example.tablayout_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment3 extends Fragment {
    View view;
    TextView tvTest;
    String urlTinh = "https://thongtindoanhnghiep.co/api/city";
    String urlQuan = "https://thongtindoanhnghiep.co/api/district/";
    List<City> cityList = new ArrayList<>();
    Spinner spinnerCity, spinnerHuyen, spinnerQuan;
    String[] arrCity = new String[65];
    List<String> arrCityTest = new ArrayList<>();
    List<City> districtList = new ArrayList<>();
    List<City> wardList = new ArrayList<>();
    Button btnSend;
    CheckBox cb4;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    public BlankFragment3() {
        // Required empty public constructor
    }

    public static BlankFragment3 newInstance(String param1, String param2, String param3) {
        BlankFragment3 fragment = new BlankFragment3();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank3, container, false);
        AnhXa();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequestCity = new StringRequest(Request.Method.GET, urlTinh, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("LtsItem");
                int i = 0;
                while(true){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    cityList.add(new City(jsonObject1.getInt("Type"), jsonObject1.getString("SolrID"),
                            jsonObject1.getInt("ID"), jsonObject1.getString("Title")));
                    if(jsonObject1.getInt("ID") == 64)
                        break;
                    i++;
                }
                i=1;

                arrCity[0] = "Chọn Tỉnh/Thành";
                arrCityTest.add("Chọn Tỉnh/Thành");
                for(City j : cityList){
                    arrCityTest.add(j.getTitle());
                }

                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrCityTest);

                spinnerCity.setAdapter(cityAdapter);

                //Huyện

                spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position != 0){
                            StringRequest stringRequestHuyen = new StringRequest(Request.Method.GET, urlTinh+"/"+position+"/district", response1 -> {
                                try {
                                    JSONArray jsonArrayHuyen = new JSONArray(response1);
                                    String[] districtAdapter = new String[11];
                                    int j = 0;
                                    JSONObject jsonObjectHuyen = jsonArrayHuyen.getJSONObject(j);
                                    districtAdapter[0] = "Chọn Quận/Huyện";
                                    while(jsonObjectHuyen != null){
                                        jsonObjectHuyen = jsonArrayHuyen.getJSONObject(j);
                                        districtList.add(new City(jsonObjectHuyen.getInt("Type"),
                                                jsonObjectHuyen.getString("SolrID"),
                                                jsonObjectHuyen.getInt("ID"),
                                                jsonObjectHuyen.getString("Title")));
                                        districtAdapter[++j] = jsonObjectHuyen.getString("Title");
                                        if(j==10)
                                            break;
                                    }

                                    ArrayAdapter<String> huyenAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, districtAdapter);
                                    spinnerHuyen.setAdapter(huyenAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }, error -> Toast.makeText(getContext(), "Lỗi huyện", Toast.LENGTH_SHORT).show());
                            requestQueue.add(stringRequestHuyen);
                        }else {
                            String[] districtAdapter = new String[1];
                            districtAdapter[0] = "Chọn Quận/Huyện";
                            ArrayAdapter<String> huyenAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, districtAdapter);
                            spinnerHuyen.setAdapter(huyenAdapter);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //Quận
                spinnerHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position != 0){
                            StringRequest stringRequestQuan = new StringRequest(Request.Method.GET,
                                    urlQuan+districtList.get(position-1).getId()+"/ward", response1 -> {

                                try {
                                        JSONArray jsonArrayHuyen = new JSONArray(response1);
                                    String[] wardAdapter = new String[11];
                                    int j = 0;
                                    JSONObject jsonObjectQuan = jsonArrayHuyen.getJSONObject(j);
                                    wardAdapter[0] = "Chọn Phường/Xã";
                                    while(jsonObjectQuan != null){
                                        jsonObjectQuan = jsonArrayHuyen.getJSONObject(j);
                                        wardList.add(new City(jsonObjectQuan.getInt("Type"),
                                                jsonObjectQuan.getString("SolrID"),
                                                jsonObjectQuan.getInt("ID"),
                                                jsonObjectQuan.getString("Title")));
                                        wardAdapter[++j] = jsonObjectQuan.getString("Title");
                                        if(j==10)
                                            break;
                                    }

                                    ArrayAdapter<String> wardListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, wardAdapter);
                                    spinnerQuan.setAdapter(wardListAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }, error -> Toast.makeText(getContext(), "Lỗi Quận", Toast.LENGTH_SHORT).show());
                            requestQueue.add(stringRequestQuan);
                        }else {
                            String[] wardAdapter = new String[1];
                            wardAdapter[0] = "Chọn Phường/Xã";
                            ArrayAdapter<String> wardListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, wardAdapter);
                            spinnerQuan.setAdapter(wardListAdapter);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(stringRequestCity);


        //btn
        btnSend.setEnabled(false);
        cb4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(!isChecked)
                btnSend.setEnabled(false);
            else
                btnSend.setEnabled(true);
        });

        btnSend.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Tôi biết rồi :v\n Chỉ cần xem không cần khai báo đâu :)", Toast.LENGTH_SHORT).show();
        });



        return view;
    }

    private void AnhXa(){
        spinnerCity = view.findViewById(R.id.spinnerTinh);
        spinnerHuyen = view.findViewById(R.id.spinnerHuyen);
        spinnerQuan = view.findViewById(R.id.spinnerQuan);
        btnSend = view.findViewById(R.id.btnSend);
        cb4 = view.findViewById(R.id.cb4);
    }
}