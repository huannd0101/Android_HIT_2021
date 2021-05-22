package com.example.miniappmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListSong extends AppCompatActivity {
    RelativeLayout item;
    RecyclerView recyclerView;
    TextView nameOfSong, textView1;
    List<Song> songList = new ArrayList<>();
    Song songClick = new Song();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();
        AddSong();

        ListSongAdapter adapter = new ListSongAdapter(songList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        adapter.setIOnClickSong(new IOnClickSong() {
            @Override
            public void onClickItemOfListSong(Song song) {
//                songClick = song;
                Toast.makeText(ListSong.this, song.getTitle().trim(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickItemOfSong(Song song) {
                Toast.makeText(ListSong.this, song.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

//        nameOfSong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent();
////                intent.putExtra("fileSong", songClick.getFile());
////                setResult(RESULT_OK, intent);
////                finish();
//                Toast.makeText(ListSong.this, nameOfSong.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void AddSong(){
        songList = new ArrayList<>();
        songList.add(new Song("Đôi ta", R.raw.doi_ta));
        songList.add(new Song("Chỉ muốn bên em lúc này", R.raw.chi_muon_ben_em_luc_nay));
        songList.add(new Song("Anh sẽ không đổi thay", R.raw.anh_se_khong_doi_thay));
        songList.add(new Song("Níu Duyên", R.raw.niu_duyen));
        songList.add(new Song("Không phải em đúng không", R.raw.khong_phai_em_dung_khong));
        songList.add(new Song("Lời xin lỗi vụng về", R.raw.loi_xin_loi_vung_ve));
        songList.add(new Song("Điều anh biết", R.raw.dieu_anh_biet));
        songList.add(new Song("Kẹo bông gòn", R.raw.keo_bong_gon));
        songList.add(new Song("Làm vợ anh nhé", R.raw.lam_vo_anh_nhe));
        songList.add(new Song("1 2 3 4 - Chi Dân", R.raw.mot_hai_ba_bon));
        songList.add(new Song("Níu Duyên", R.raw.niu_duyen));
    }

    private void AnhXa(){
        item = findViewById(R.id.item);
        recyclerView = findViewById(R.id.rcSong);
        nameOfSong = findViewById(R.id.nameOfSong);
        textView1 = findViewById(R.id.textView1);
    }
}