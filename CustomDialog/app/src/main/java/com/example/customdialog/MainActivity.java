package com.example.customdialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tv = findViewById(R.id.tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
    }

    private void DialogLogin(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //nếu hiển thị title mà muốn tắt thì dùng lệnh này sau khi khai báo - để dưới thì bị lỗi
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCanceledOnTouchOutside(false); //nếu chạm ra ngoài dialog thì có cho hủy hay k(mặc định là true)

        //ánh xạ
        EditText edtUserName = dialog.findViewById(R.id.edt1);
        EditText edtPassword = dialog.findViewById(R.id.edt2);
        Button btnDangNhap = dialog.findViewById(R.id.btnDangNhap);
        Button btnHuyBo = dialog.findViewById(R.id.btnHuyBo);

        //bắt sự kiện
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                if(user.compareTo("huannd0101")  == 0 && pass.compareTo("0108") == 0){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    edtUserName.setText(null);
                    edtPassword.setText(null);
                }
            }
        });

        btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.cancel();
                dialog.dismiss();
                //dùng 1 trong 2 để tắt hộp thoại
            }
        });

        dialog.show();
    }
}