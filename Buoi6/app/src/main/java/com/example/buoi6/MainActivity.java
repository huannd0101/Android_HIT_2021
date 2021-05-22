package com.example.buoi6;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    static final String SHARE_PRE_NAME = "account";
    Button btnSubmit;
    CheckBox cb;
    EditText edtUser, edtPass;
    SQLHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();
        //SQLite
        sqlHelper = new SQLHelper(this);
        sqlHelper.insertAccount(new Account("acc1", "pass1"));
//        sqlHelper.updateAccount(1, new Account("editAcc1", "pass1"));
//        sqlHelper.deleteAccount(1);



















        //shared reference
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PRE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(cb.isChecked()){
                    editor.putString("username", edtUser.getText().toString().trim());
                    editor.putString("password", edtPass.getText().toString().trim());
                    editor.putBoolean("check", true);
                    editor.commit();
                }else {
                    editor.putBoolean("check", false);
                }
            }
        });

    }

    //khi khởi tạo nó sẽ tự động điền vào
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PRE_NAME, MODE_PRIVATE);
        String user = sharedPreferences.getString("username", "null");
        String pass = sharedPreferences.getString("password", "null");
        edtUser.setText(user);
        edtPass.setText(pass);
    }

    private void AnhXa() {
        btnSubmit = findViewById(R.id.button);
        cb = findViewById(R.id.checkBox);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPassword);
    }
}