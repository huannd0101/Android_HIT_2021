package com.example.ipa_demo_buoi6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ipa_demo_buoi6.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    String url = "http://demo-b5.herokuapp.com/api/accounts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        //get API
        binding.btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String name = jsonObject.getString("username");
                            binding.textView.setText(jsonArray.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        binding.textView.setText("Lỗi rồi :v");
                    }
                });

                //phải add thì mới chạy đc
                requestQueue.add(stringRequest);
            }
        });


        //thêm thành phần API: post
        binding.btnPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", "Xin lỗi vì sự bất tiện này nhưng em là Huân đẹpt trai");
                    jsonObject.put("password", "Chắc chắn là Huân đẹpt trai r :v");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String requestBody = jsonObject.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        binding.textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        binding.textView.setText("Lỗi rồi");
                    }
                }) {
                    //2 phương thức để post
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if(requestBody==null) //nếu string vào rỗng thì return null, ngược lại thì post lên
                            return null;
                        else {
                            try {
                                return requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }
                };
                //add vào requestQueue
                requestQueue.add(stringRequest);
            }
        });


        //put/path: sửa thành phần api
        binding.btnPUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("password", "testLan2");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String stringBody = jsonObject.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.PATCH, url + "/18", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        binding.textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        binding.textView.setText("Lỗi rồi");
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if(stringBody != null){
                            try {
                                return stringBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        return null;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        //delete: xóa thành phần của API

        binding.btnDELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url + "/51", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        binding.textView.setText("Thành công");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        binding.textView.setText("Lỗi");
                    }
                });
                //add
                requestQueue.add(stringRequest);
            }
        });

    }
}