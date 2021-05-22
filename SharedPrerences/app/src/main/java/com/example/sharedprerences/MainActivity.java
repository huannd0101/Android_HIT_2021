package com.example.sharedprerences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    CheckBox cbRemember;
    EditText edtUser, edtPass;
    //khai báo sharedPreferences
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        //lấy sharedPreferences: lưu vào bộ nhớ ứng dụng
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //lấy giá trị từ bộ nhớ ứng dụng
        edtUser.setText(sharedPreferences.getString("taikhoan", ""));
        edtPass.setText(sharedPreferences.getString("matkhau", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("cbChecked", false));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if(user.compareTo("admin")==0 && pass.compareTo("123")==0){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    //nếu ô checkbox đc check thì ta sẽ lưu dữ liệu
                    if(cbRemember.isChecked()){
                        //bắt đầu khai báo editor
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", user);//2 đối gồm 1 name và 1 giá trị
                        editor.putString("matkhau", pass);
                        editor.putBoolean("cbChecked", true);//mặc định có check thì là true rồi :v
                        editor.commit(); //lưu chỉnh sửa
                    }else {
                        //khi ô checkbox k đc check thì ta khóa hết dữ liệu
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("cbChecked");
                        editor.commit();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa(){
        btn = findViewById(R.id.btn);
        cbRemember = findViewById(R.id.cbRemember);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
    }
}