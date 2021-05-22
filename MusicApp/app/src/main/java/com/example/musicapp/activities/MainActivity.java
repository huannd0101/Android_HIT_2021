package com.example.musicapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.musicapp.R;
import com.example.musicapp.databinding.ActivityMainBinding;
import com.example.musicapp.models.Song;
import com.example.musicapp.services.MusicService;
import com.example.musicapp.utilities.Constants;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private Song mSong;
    private boolean isPlaying;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle == null){
                return;
            }
            mSong = (Song) bundle.get(Constants.PUT_SERIALIZABLE_SONG);
            isPlaying = bundle.getBoolean(Constants.PUT_BOOLEAN_STATUS);
            int actionMusic = bundle.getInt(Constants.PUT_ACTION_MUSIC);

            handleLayoutMusic(actionMusic);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        //Đăng ký nhận broadcastReceiver
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(Constants.SEND_DATA_TO_ACTIVITY));

        binding.btnStart.setOnClickListener(v -> clickStartService());
        binding.btnStop.setOnClickListener(v -> clickStopService());



    }

    private void clickStartService() {
        Intent intent = new Intent(this, MusicService.class);

        Song song = new Song("Đôi ta", "Nguyễn Đình Huân 1", R.drawable.ic_launcher_background, R.raw.doi_ta);


        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PUT_SERIALIZABLE_SONG, song);

        intent.putExtras(bundle);

        startService(intent);
    }

    private void clickStopService() {
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void handleLayoutMusic(int actionMusic) {
        switch (actionMusic){
            case Constants.ACTION_START:
                binding.relativeLayout.setVisibility(View.VISIBLE);
                showInfoSong();
                setStatusPlayOrPause();
                break;

            case Constants.ACTION_PAUSE:
            case Constants.ACTION_RESUME:
                setStatusPlayOrPause();
                break;

            case Constants.ACTION_CANCEL:
                binding.relativeLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void showInfoSong(){
        if (mSong == null){
            return;
        }
        binding.imageSongMain.setImageResource(mSong.getImage());
        binding.tvNameOfSongMain.setText(mSong.getNameOfSong());
        binding.tvNameOfSingleMain.setText(mSong.getSingleOfSong());

        //tương tác ngược lại service
        binding.imageViewPlayOrPauseMain.setOnClickListener(v -> {
            if(isPlaying){
                sendActionToService(Constants.ACTION_PAUSE);
            }else {
                sendActionToService(Constants.ACTION_RESUME);
            }
        });
        binding.imageViewCancelMain.setOnClickListener(v -> {
            sendActionToService(Constants.ACTION_CANCEL);
        });
    }

    private void setStatusPlayOrPause(){
        if(isPlaying){
            binding.imageViewPlayOrPauseMain.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
        }else {
            binding.imageViewPlayOrPauseMain.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
        }
    }

    private void sendActionToService(int action){
        Intent intent = new Intent(this, MusicService.class);
        //truyền đúng key của server nhận đc để đỡ phải code lại
        intent.putExtra(Constants.ACTION_FROM_BROADCAST_TO_SERVICE, action);

        startService(intent);
    }
}