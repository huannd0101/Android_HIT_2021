package com.example.demo_sqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_sqlite.DAO.UserDatabase;
import com.example.demo_sqlite.adapters.UserAdapter;
import com.example.demo_sqlite.databinding.ActivityMainBinding;
import com.example.demo_sqlite.enteties.User;
import com.example.demo_sqlite.event.IOnClickUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_INTENT = 10;
    ActivityMainBinding binding;

    UserAdapter adapter;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        list = new ArrayList<>();

        adapter = new UserAdapter(new IOnClickUser() {
            @Override
            public void updateUser(User user) {
                clickToUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                clickToDeleteUser(user);
            }
        });

        adapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rclView.setLayoutManager(linearLayoutManager);

        binding.rclView.setAdapter(adapter);

        //bắt sự kiện add user
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        //Bắt sự kiện xóa tất cả
        binding.tvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToDeleteAllUser();
            }
        });

        //bắt sự kiện search
        binding.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //Xử lý logic search
                    searchUser();
                }
                return false;
            }
        });

        loadData();

    }

    private void searchUser() {
        String keyword = binding.edtSearch.getText().toString().trim();
        list.clear();
        list = UserDatabase.getInstance(MainActivity.this).userDAO().searchByUsername(keyword);
        //sét lại data cho thằng adapter
        adapter.setData(list);

        //search xong thì bỏ bàn phím
        hideSoftKeyboard();
    }

    private void clickToDeleteAllUser() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Confirm delete all user")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDatabase.getInstance(MainActivity.this).userDAO().deleteAllUser();
                        Toast.makeText(MainActivity.this, "Delete all user successfully", Toast.LENGTH_SHORT).show();

                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();;
    }

    private void clickToDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Xóa user from list
                        UserDatabase.getInstance(MainActivity.this).userDAO().deleteUser(user);
                        Toast.makeText(MainActivity.this, "Delete user successfully", Toast.LENGTH_SHORT).show();

                        loadData();
                    }
                })
                .setNegativeButton("No", null).show();
    }

    private void clickToUpdateUser(User user) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("obj_user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_INTENT);
    }

    private void addUser() {
        String username  = binding.edtUsername.getText().toString().trim();
        String address  = binding.edtAddress.getText().toString().trim();
        String year  = binding.edtYear.getText().toString().trim();

        //check 1 trong 2 trống thì k add
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(address) || TextUtils.isEmpty(year)){
            return;
        }

        User user = new User(username, address, year);

        if(checkUserExists(user)){
            Toast.makeText(this, "User exists", Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabase.getInstance(this).userDAO().insertUser(user);

        Toast.makeText(this, "Add user successfully", Toast.LENGTH_SHORT).show();

        binding.edtUsername.setText("");
        binding.edtAddress.setText("");
        binding.edtYear.setText("");

        //ẩn bàn phím sau khi add xong
        hideSoftKeyboard();

        loadData();
    }

    private void loadData() {
        list = UserDatabase.getInstance(this).userDAO().getListUser();
        adapter.setData(list);
    }

    //ẩn bàn phím sau khi add xong
    public void hideSoftKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException exception){
            exception.printStackTrace();
        }
    }


    //check user đã tồn tại hay chưa
    public boolean checkUserExists( User user){
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getName());
        return list != null && !list.isEmpty();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_INTENT && resultCode == RESULT_OK && data != null){
            loadData();
        }
    }
}