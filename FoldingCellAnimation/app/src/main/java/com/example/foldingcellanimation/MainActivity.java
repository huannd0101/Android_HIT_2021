package com.example.foldingcellanimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.admin.DeviceAdminReceiver;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    String url = "https://bookshopb.herokuapp.com/api/books";
    List<Book> bookList = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
                recyclerView = findViewById(R.id.rclView);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);

                BookAdapter adapter = new BookAdapter();
                adapter.setData(bookList);
                recyclerView.setAdapter(adapter);


                //xóa item
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int pos = viewHolder.getAdapterPosition();
                        bookList.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });

                itemTouchHelper.attachToRecyclerView(recyclerView);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(MainActivity.this, "lỗi", Toast.LENGTH_SHORT).show());

        requestQueue.add(stringRequest);
    }
}