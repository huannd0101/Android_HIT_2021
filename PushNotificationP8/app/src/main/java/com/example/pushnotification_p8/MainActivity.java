package com.example.pushnotification_p8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Main");

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendPushNotificationRegular();
                sendPushNotificationSpecial();
            }
        });
    }

    private void sendPushNotificationSpecial() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chat);

        // Create an Intent for the activity you want to start
        Intent notifyIntent = new Intent(this, Detail.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("Test notification 2")
                .setContentText("Nguyễn Đình Huân 2")
                .setSmallIcon(R.drawable.chat)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.design_default_color_primary))
                .setContentIntent(notifyPendingIntent) //sét cái click vào để chuyển đến activity
                .build();
        //cách 1:
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification); //không cần check because nó tự check r :v
    }

    private void sendPushNotificationRegular() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chat);

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, Detail.class); //chuyển đến activity nào khi click vào notification
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(getNotificationId(), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("Test notification 2")
                .setContentText("Nguyễn Đình Huân 2")
                .setSmallIcon(R.drawable.chat)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.design_default_color_primary))
                .setContentIntent(resultPendingIntent) //sét cái click vào để chuyển đến activity
                .build();
        //cách 1:
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification); //không cần check because nó tự check r :v
    }

    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}