package com.example.notification_demo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "Channel one";
    public static final String CHANNEL_ID_2 = "Channel two";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    //cop tren mạng: https://developer.android.com/training/notify-user/channels
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //config sound notification
            Uri uri_cus = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.teemo);
            AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();


            // config channel one
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;//mức độ ưu tiên
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //set sound notification
            channel.setSound(uri_cus, attributes);
            // config channel two

            CharSequence name2 = getString(R.string.channel_name2);
            String description2 = getString(R.string.channel_description2);
            int importance2 = NotificationManager.IMPORTANCE_DEFAULT;//mức độ ưu tiên
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, name2, importance2);
            channel_2.setDescription(description2);
            channel_2.setSound(uri_cus, attributes);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel_2);
            }
        }
    }
}
