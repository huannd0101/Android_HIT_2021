package com.example.musicapp.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.musicapp.R;
import com.example.musicapp.activities.MainActivity;
import com.example.musicapp.broadcastReceiver.MusicReceiver;
import com.example.musicapp.models.Song;
import com.example.musicapp.utilities.Constants;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private boolean isPlaying;
    private Song mSong;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Song song = (Song) bundle.get(Constants.PUT_SERIALIZABLE_SONG);

            if(song != null) {
                mSong = song;
                startMusic(song);
                sendNotification(song);
            }
        }

        int actionMusic = intent.getIntExtra(Constants.ACTION_FROM_BROADCAST_TO_SERVICE, 0);
        handleMusic(actionMusic);

        return START_NOT_STICKY;
    }

    private void startMusic(Song song) {
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        }
        isPlaying = true;
        mediaPlayer.start();
        sendActionToActivity(Constants.ACTION_START);
    }

    private void sendNotification(Song song) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                Constants.REQUEST_CODE_NOTIFI_PENDING_INTENT,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), song.getImage());

        @SuppressLint("RemoteViewLayout") RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.song_layout);
        remoteViews.setTextViewText(R.id.tvNameOfSong, song.getNameOfSong());
        remoteViews.setTextViewText(R.id.tvNameOfSingle, song.getSingleOfSong());
        remoteViews.setImageViewBitmap(R.id.imageSong, bitmap);
        remoteViews.setImageViewResource(R.id.imageViewPlayOrPause, R.drawable.ic_baseline_pause_circle_outline_24);
        //click
        if(isPlaying) {
            remoteViews.setOnClickPendingIntent(R.id.imageViewPlayOrPause, getPendingIntent(this, Constants.ACTION_PAUSE));
            remoteViews.setImageViewResource(R.id.imageViewPlayOrPause, R.drawable.ic_baseline_pause_circle_outline_24);
        }else {
            remoteViews.setOnClickPendingIntent(R.id.imageViewPlayOrPause, getPendingIntent(this, Constants.ACTION_RESUME));
            remoteViews.setImageViewResource(R.id.imageViewPlayOrPause, R.drawable.ic_baseline_play_circle_outline_24);
        }

        remoteViews.setOnClickPendingIntent(R.id.imageViewCancel, getPendingIntent(this, Constants.ACTION_CANCEL));


        Notification notification = new NotificationCompat.Builder(this, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setSound(null)
                .build();

        startForeground(Constants.ID_START_FOREGROUND, notification);
    }

    private PendingIntent getPendingIntent(Context context, int action) {
        Intent intent = new Intent(this, MusicReceiver.class);
        intent.putExtra(Constants.ACTION_PUT_TO_BROADCAST, action);

        return PendingIntent.getBroadcast(context.getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    void handleMusic (int action){
        switch (action){
            case Constants.ACTION_PAUSE:
                clickPauseMusic();
                break;
            case Constants.ACTION_RESUME:
                clickResumeMusic();
                break;
            case Constants.ACTION_CANCEL:
                clickCancelMusic();
                break;
        }
    }

    private void clickPauseMusic() {
        if(mediaPlayer != null && isPlaying){
            mediaPlayer.pause();
            isPlaying = false;
            sendNotification(mSong);
            sendActionToActivity(Constants.ACTION_PAUSE);
        }
    }

    private void clickResumeMusic() {
        if(mediaPlayer != null && !isPlaying){
            mediaPlayer.start();
            isPlaying = true;
            sendNotification(mSong);
            sendActionToActivity(Constants.ACTION_RESUME);
        }
    }

    private void clickCancelMusic() {
        sendActionToActivity(Constants.ACTION_CANCEL);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    //send action to MainActivity
    private void sendActionToActivity(int action){
        Intent intent = new Intent(Constants.SEND_DATA_TO_ACTIVITY);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PUT_SERIALIZABLE_SONG, mSong);
        bundle.putBoolean(Constants.PUT_BOOLEAN_STATUS, isPlaying);
        bundle.putInt(Constants.PUT_ACTION_MUSIC, action);

        intent.putExtras(bundle);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
