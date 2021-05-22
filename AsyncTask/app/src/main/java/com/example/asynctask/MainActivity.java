package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView imgView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imgView);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongViec congViec = new CongViec();

                //để chạy thì đối tượng đó .execute();
                congViec.execute();
            }
        });

    }

    //khai báo một class
    private class CongViec extends AsyncTask<Void, String, String> {

        //bắt đầu công việc
        @Override
        protected void onPreExecute() {
            tv.setText("Bắt đầu\n");
            super.onPreExecute();
        }

        //Xử lý
        @Override
        protected String doInBackground(Void... voids) {
            for(int i=0; i<3; i++){

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress("Đang xử lý công việc thứ " + i + "\n");
                publishProgress("Cục cứt chó " + i + "\n");
            }
            for(int i=0; i<3; i++){

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress("for thứ 2 " + i + "\n");
            }
            return "Xong công việc";
        }

        //kết thúc
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.append(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv.append(values[0]);
        }
    }
}