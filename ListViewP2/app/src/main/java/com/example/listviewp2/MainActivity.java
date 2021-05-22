package com.example.listviewp2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Person> personList;
    PersonAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AnhXa();

    }

    private void AnhXa() {
        lv = findViewById(R.id.lvPerson);

        personList = new ArrayList<>();
        personList.add(new Person(R.drawable.brokenheart, "Nguyễn Đình Huân", "0375417808", "Ngu, Xấu, Lùn"));
        personList.add(new Person(R.drawable.artist, "Nguyễn Anh Linh", "0123455788", "Học giỏi, đẹp trai, không có người yêu"));
        personList.add(new Person(R.drawable.chef, "Bùi Tất Trung", "No Phone", "Học giỏi, có người yêu"));
        personList.add(new Person(R.drawable.bussinessman, "Vũ Văn Doan", "0987654321", "Chủ nghiệm CLB"));
        personList.add(new Person(R.drawable.artist, "Chí Tình", "No Phone", "Có Sừng"));
        personList.add(new Person(R.drawable.chef, "Nguyễn Đức Điệp", "No Phone", "Leader android, không có người yêu"));
        personList.add(new Person(R.drawable.accountant, "Cao Đắc Thuận", "No Phone", "Dancer"));
        personList.add(new Person(R.drawable.banker, "Trần Đình Hạo", "No Phone", "Đẹp trai"));
        personList.add(new Person(R.drawable.accountant, "Trương Vô Kỵ", "0231896564", "No Describe"));
        personList.add(new Person(R.drawable.bussinessman2, "Lý Thu Thủy", "0375417808", "Xinh Gái"));
        personList.add(new Person(R.drawable.chef, "Đông Phương Bất Bại", "No Phone", "Bê đê"));
        personList.add(new Person(R.drawable.brokenheart, "Nguyễn Đình Huân", "0375417808", "Ngu, Xấu, Lùn"));
        personList.add(new Person(R.drawable.artist, "Nguyễn Anh Linh", "0123455788", "Học giỏi, đẹp trai, không có người yêu"));
        personList.add(new Person(R.drawable.chef, "Bùi Tất Trung", "No Phone", "Học giỏi, có người yêu"));
        personList.add(new Person(R.drawable.bussinessman, "Vũ Văn Doan", "0987654321", "Chủ nghiệm CLB"));
        personList.add(new Person(R.drawable.artist, "Chí Tình", "No Phone", "Có Sừng"));
        personList.add(new Person(R.drawable.chef, "Nguyễn Đức Điệp", "No Phone", "Leader android, không có người yêu"));
        personList.add(new Person(R.drawable.accountant, "Cao Đắc Thuận", "No Phone", "Dancer"));
        personList.add(new Person(R.drawable.banker, "Trần Đình Hạo", "No Phone", "Đẹp trai"));
        personList.add(new Person(R.drawable.accountant, "Trương Vô Kỵ", "0231896564", "No Describe"));
        personList.add(new Person(R.drawable.bussinessman2, "Lý Thu Thủy", "0375417808", "Xinh Gái"));
        personList.add(new Person(R.drawable.chef, "Đông Phương Bất Bại", "No Phone", "Bê đê"));
        personList.add(new Person(R.drawable.brokenheart, "Nguyễn Đình Huân", "0375417808", "Ngu, Xấu, Lùn"));
        personList.add(new Person(R.drawable.artist, "Nguyễn Anh Linh", "0123455788", "Học giỏi, đẹp trai, không có người yêu"));
        personList.add(new Person(R.drawable.chef, "Bùi Tất Trung", "No Phone", "Học giỏi, có người yêu"));
        personList.add(new Person(R.drawable.bussinessman, "Vũ Văn Doan", "0987654321", "Chủ nghiệm CLB"));
        personList.add(new Person(R.drawable.artist, "Chí Tình", "No Phone", "Có Sừng"));
        personList.add(new Person(R.drawable.chef, "Nguyễn Đức Điệp", "No Phone", "Leader android, không có người yêu"));
        personList.add(new Person(R.drawable.accountant, "Cao Đắc Thuận", "No Phone", "Dancer"));
        personList.add(new Person(R.drawable.banker, "Trần Đình Hạo", "No Phone", "Đẹp trai"));
        personList.add(new Person(R.drawable.accountant, "Trương Vô Kỵ", "0231896564", "No Describe"));
        personList.add(new Person(R.drawable.bussinessman2, "Lý Thu Thủy", "0375417808", "Xinh Gái"));
        personList.add(new Person(R.drawable.chef, "Đông Phương Bất Bại", "No Phone", "Bê đê"));

        adapter = new PersonAdapter(this, R.layout.person, personList);

        lv.setAdapter(adapter);
    }
}