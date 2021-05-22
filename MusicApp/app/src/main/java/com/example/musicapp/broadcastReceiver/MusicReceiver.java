package com.example.musicapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.musicapp.services.MusicService;
import com.example.musicapp.utilities.Constants;

public class MusicReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int actionMusic = intent.getIntExtra(Constants.ACTION_PUT_TO_BROADCAST, 0);

        Intent intentService = new Intent(context, MusicService.class);

        intentService.putExtra(Constants.ACTION_FROM_BROADCAST_TO_SERVICE, actionMusic);

        context.startService(intentService);//k khởi tạo lại service. Chuyền vào onStartCommand()
    }
}
