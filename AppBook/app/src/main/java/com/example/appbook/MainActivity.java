package com.example.appbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbook.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "https://bookshopb.herokuapp.com/api/books";
    List<Book> bookList = new ArrayList<>();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    bookList.add(new Book(
                            jsonObject.getInt("id"),
                            jsonObject.getString("imageLink"),
                            jsonObject.getString("imagePublicId"),
                            jsonObject.getString("title"),
                            jsonObject.getString("author"),
                            jsonObject.getString("publisher"),
                            jsonObject.getInt("releaseYear"),
                            jsonObject.getInt("numOfPage"),
                            jsonObject.getString("description"),
                            jsonObject.getString("categoty"),
                            jsonObject.getInt("rateStar"),
                            jsonObject.getInt("price"),
                            jsonObject.getInt("numOfReview")
                    ));
                }

                //đáp ra rclView

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

                BookAdapter2 adapter2 = new BookAdapter2(bookList, this);

                binding.rclView.setLayoutManager(layoutManager);
                binding.rclView.setAdapter(adapter2);

                adapter2.setiOnClickItem(book -> {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("obj", book);
                    startActivity(intent);
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(MainActivity.this, "lỗi", Toast.LENGTH_SHORT).show());

        requestQueue.add(stringRequest);
    }
}