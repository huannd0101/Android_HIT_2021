package com.example.volume;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.volume.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    int maxVolumeValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rang_khon);
        mediaPlayer.start();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolumeValue/2,AudioManager.FLAG_SHOW_UI);

        maxVolumeValue = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        binding.btnTangAm.setOnClickListener(v -> {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolumeValue++,AudioManager.FLAG_SHOW_UI);
        });

        binding.btnGiamAm.setOnClickListener(v -> {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolumeValue--,AudioManager.FLAG_SHOW_UI);
        });

        binding.btnBatAm.setOnClickListener(v -> {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
        });

        binding.btnTatAm.setOnClickListener(v -> {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
        });




    }
}