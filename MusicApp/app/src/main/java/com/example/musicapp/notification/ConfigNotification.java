package com.example.musicapp.notification;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import com.example.musicapp.R;
import com.example.musicapp.utilities.Constants;

public class ConfigNotification extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //config sound notification
//            Uri uri_cus = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.teemo);
            AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();


            // config channel one
            CharSequence name = Constants.CHANNEL_NAME;
            String description = Constants.CHANNEL_DESCRIPTION;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;//mức độ ưu tiên
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //set sound notification
//            channel.setSound(uri_cus, attributes);
            channel.setSound(null, null);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }


}
