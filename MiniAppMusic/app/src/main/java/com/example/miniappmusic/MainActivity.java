package com.example.miniappmusic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle, tvTime, tvSum, listSong;
    ImageButton rewind, play, stop, forward;
    SeekBar sb;
    List<Song> songList;
    int position = 0;
    MediaPlayer mediaPlayer;
    ImageView imgView;
    Animation animation, animationTitle;
    int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();
        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);
        imgView.startAnimation(animation);

//        animationTitle = AnimationUtils.loadAnimation(this, R.anim.name_of_song);
//        tvTitle.startAnimation(animationTitle);

        AddSong();

        mediaPlayer = MediaPlayer.create(MainActivity.this, songList.get(position).getFile());

        tvTitle.setText(songList.get(position).getTitle());
        SetTimeTotal();
//        KhoiTaoMediaPlayer();
//        UpdateTimeSong();


        listSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListSong.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.play);
                }else {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                }
                UpdateTimeSong();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play.setImageResource(R.drawable.play);

                KhoiTaoMediaPlayer();
                SetTimeTotal();
            }
        });

        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                if(position != 0){
                    position--;
                }else {
                    position = songList.size() - 1;
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                if (position == songList.size() - 1){
                    position = 0;
                }else {
                    position++;
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //trường hợp này cho dừng lại kéo thả thì mới cập nhập
                mediaPlayer.seekTo(sb.getProgress());
            }
        });
    }

    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                tvTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                //update sb
                sb.setProgress(mediaPlayer.getCurrentPosition());

                //ktra khi hết thì chuyển bài
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //code của chuyển bài
                        if(mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                        if (position == songList.size() - 1){
                            position = 0;
                        }else {
                            position++;
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        play.setImageResource(R.drawable.pause);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });

                //gọi lại k ngừng
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            int fileSong = data.getIntExtra("fileSong", 0);
            Song song = new Song();

            for(Song i : songList){
                if(i.getFile() == fileSong){
                    song = i;
                    break;
                }
            }
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(MainActivity.this, song.getFile());
            tvTitle.setText(song.getTitle());
            SetTimeTotal();
            UpdateTimeSong();
        }else {
            Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SetTimeTotal(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvSum.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sb.setMax(mediaPlayer.getDuration()); //gán thanh seekbar bằng thời gian của bài hát
        tvTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
    }

    private void KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, songList.get(position).getFile());

        tvTitle.setText(songList.get(position).getTitle());
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
        sb = findViewById(R.id.sb);
        tvTitle = findViewById(R.id.tvTitle);
        rewind = findViewById(R.id.rewind);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        forward = findViewById(R.id.forward);
        tvTime = findViewById(R.id.tvTime);
        tvSum = findViewById(R.id.tvSum);
        imgView = findViewById(R.id.imgView);
        listSong = findViewById(R.id.listSong);
    }
}