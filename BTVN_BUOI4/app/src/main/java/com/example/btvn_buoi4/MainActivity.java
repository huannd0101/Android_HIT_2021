package com.example.btvn_buoi4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    TextView tvForget, tvAccount;
    Button btnDangNhap, btnHuyBo;
    CheckBox cbRemember;
    Spinner spnDay, spnMonth, spnYear;

    Pattern pattern;
    String regUserName = "^[a-zA-Z0-9]{5,}$";
    String regPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AnhXa();



        //create new account
        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAccount();
            }
        });



        //forget password
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForget();
            }
        });

        //btn đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                boolean checkUser = CheckUserName(userName);
                boolean checkPass = CheckPassword(password);
                if(userName.compareTo("") == 0 && password.compareTo("") == 0){
                    Toast.makeText(MainActivity.this, "Xin mời nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                }else if(userName.compareTo("") == 0){
                    Toast.makeText(MainActivity.this, "Xin nhập tài khoản", Toast.LENGTH_SHORT).show();
                }else if(password.compareTo("") == 0){
                    Toast.makeText(MainActivity.this, "Xin mời nhập password", Toast.LENGTH_SHORT).show();
                }else if(!checkUser && !checkPass){
                    Toast.makeText(MainActivity.this, "Tài khoản mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }else if(!checkUser){
                    Toast.makeText(MainActivity.this, "Tài khoản không đúng", Toast.LENGTH_SHORT).show();
                }else if(!checkPass){
                    Toast.makeText(MainActivity.this, "Password không đúng", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                    startActivity(intent);
                }
            }
        });
        //btn hủy bỏ
        btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUserName.setText(null);
                edtPassword.setText(null);
                cbRemember.setChecked(false);

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                startActivity(intent);
            }
        });
    }

    private void DialogAccount(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_account);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }

    private void DialogForget(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.forget_password);
        dialog.setCanceledOnTouchOutside(true);
        //Ánh xạ
//        EditText edtForget0 = findViewById(R.id.edtForget0);
//        EditText edtForget1 = findViewById(R.id.edtForget1);
//        EditText edtForget2 = findViewById(R.id.edtForget2);
//        Button btnXacNhan = findViewById(R.id.btnXacNhan);

//        btnXacNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String edt0 = edtForget0.getText().toString().trim();
//                String edt1 = edtForget1.getText().toString().trim();
//                String edt2 = edtForget2.getText().toString().trim();
//                boolean check0 = CheckUserName(edt0);
//                boolean check1 = CheckPassword(edt1);
//                boolean check2 = CheckPassword(edt2);
//                if(!check0 && !check1 && !check2){
//                    Toast.makeText(MainActivity.this, "Yêu cầu nhập lại :v", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        dialog.show();
    }

    private boolean CheckUserName(String userName){
        pattern = Pattern.compile(regUserName);
        return pattern.matcher(userName).find();
    }
    private boolean CheckPassword(String password){
        pattern = Pattern.compile(regPassword);
        return pattern.matcher(password).find();
    }
    private void AnhXa(){
        edtPassword = findViewById(R.id.edtPassWord);
        edtUserName = findViewById(R.id.edtUserName);
        tvForget = findViewById(R.id.tvForget);
        tvAccount = findViewById(R.id.tvAccount);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnHuyBo = findViewById(R.id.btnHuyBo);
        cbRemember = findViewById(R.id.cbRemember);
    }
}