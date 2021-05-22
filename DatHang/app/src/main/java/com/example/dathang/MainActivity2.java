package com.example.dathang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView rcHangHoa;
    List<HangHoa> list = new ArrayList<>();
    TextView tvSoLuong, sum;
    ImageButton oder;
    Button btnHuyBoTotal, btnOrder;
    List<HangHoa> listOrder = new ArrayList<>();
    int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tvSoLuong = findViewById(R.id.tvSoLuong);
        rcHangHoa = findViewById(R.id.rcHangHoa);
        sum = findViewById(R.id.sum);
        btnHuyBoTotal = findViewById(R.id.btnHuyBoTotal);
        btnOrder = findViewById(R.id.btnOrder);
        oder = findViewById(R.id.oder);

        list.add(new HangHoa(1, R.drawable.bananas, "Chuối", "Chuối chín tàu", 100, 0));
        list.add(new HangHoa(2, R.drawable.apple, "Táo", "Táo tàu có độc", 80, 0));
        list.add(new HangHoa(3, R.drawable.cake, "Bánh gato", "Bánh gato tàu", 100, 0));
        list.add(new HangHoa(4, R.drawable.hamburger, "Hamburger", "Hamburger tàu", 20, 0));
        list.add(new HangHoa(5, R.drawable.milk, "Milk", "Sữa bò", 70, 0));
        list.add(new HangHoa(6, R.drawable.noodle, "noodle", "Mì tôm tàu", 60, 0));
        list.add(new HangHoa(7, R.drawable.ramen, "Ramen", "", 10, 0));
        list.add(new HangHoa(8, R.drawable.strawberry, "Strawberry", "Dâu tây tàu", 200, 0));
        list.add(new HangHoa(9, R.drawable.bananas, "Chuối", "Chuối chín tàu", 100, 0));
        list.add(new HangHoa(10, R.drawable.apple, "Táo", "Táo tàu có độc", 80, 0));
        list.add(new HangHoa(11, R.drawable.cake, "Bánh gato", "Bánh gato tàu", 100, 0));
        list.add(new HangHoa(12, R.drawable.hamburger, "Hamburger", "Hamburger tàu", 20, 0));
        list.add(new HangHoa(13, R.drawable.milk, "Milk", "Sữa bò", 70, 0));
        list.add(new HangHoa(14, R.drawable.noodle, "noodle", "Mì tôm tàu", 60, 0));
        list.add(new HangHoa(15, R.drawable.ramen, "Ramen", "", 10, 0));
        list.add(new HangHoa(16, R.drawable.strawberry, "Strawberry", "Dâu tây tàu", 200, 0));

        HangHoaAdapter adapter = new HangHoaAdapter(list, MainActivity2.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity2.this, 2, RecyclerView.VERTICAL, false);

        rcHangHoa.setLayoutManager(layoutManager);
        rcHangHoa.setAdapter(adapter);





        adapter.setiOnClickHangHoa(new IOnClickHangHoa() {
            @Override
            public void onClickBtnSub(HangHoa hangHoa) {
                hangHoa.setSoLuong(hangHoa.getSoLuong() - 1);
                tvSoLuong.setText(soLuong(list)+"");
                sum.setText(TongTien(list)+"");
                listOrder = AddHangHoa(list);
                Toast.makeText(MainActivity2.this, "Bạn đã hủy 1 đơn hàng: " + hangHoa.getName(), Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onClickBtnPlus(HangHoa hangHoa) {
                hangHoa.setSoLuong(hangHoa.getSoLuong() + 1);
                tvSoLuong.setText(soLuong(list)+"");
                sum.setText(TongTien(list)+" $");
                listOrder = AddHangHoa(list);
                Toast.makeText(MainActivity2.this, "Bạn đã thêm 1 đơn hàng: " + hangHoa.getName(), Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

        });

        oder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

                intent.putParcelableArrayListExtra("listHangHoa", (ArrayList<? extends Parcelable>) listOrder);

                startActivity(intent);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

                intent.putParcelableArrayListExtra("listHangHoa", (ArrayList<? extends Parcelable>) listOrder);

                startActivity(intent);
            }
        });

        //hủy hết
        btnHuyBoTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HuyBo(list);
                adapter.notifyDataSetChanged();
                sum.setText("0 $");
                Toast.makeText(MainActivity2.this, "Bạn đã hủy hết đơn hàng", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private List<HangHoa> AddHangHoa(List<HangHoa> list){
        List<HangHoa> newList = new ArrayList<>();
        for(HangHoa i : list)
            if(i.getSoLuong() > 0)
                newList.add(i);
        return newList;
    }

    private void HuyBo(List<HangHoa> list){
        for(HangHoa i : list)
            i.setSoLuong(0);
    }

    private int soLuong(List<HangHoa> list){
        int s = 0;
        for(HangHoa i : list)
            s += i.getSoLuong();
        return s;
    }
    private int TongTien(List<HangHoa> list){
        int s = 0;
        for(HangHoa i : list)
            s += i.getSoLuong()*i.getPrice();
        return s;
    }


}