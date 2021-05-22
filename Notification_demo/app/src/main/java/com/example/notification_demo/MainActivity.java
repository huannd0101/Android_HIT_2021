package com.example.notification_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final Object NOTIFICATION_ID = 1;
    Button btnSend;
    Button btnSend2;
    Button btnCustomNotifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btnSend = findViewById(R.id.btnSend);
        btnSend2 = findViewById(R.id.btnSend2);
        btnCustomNotifi = findViewById(R.id.btnCustomNotifi);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        btnSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification2();
            }
        });

        btnCustomNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendCustomNotifitcation();
                Toast.makeText(MainActivity.this, "Xem video số 7", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chat);
        Bitmap bitmap_2 = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        String TEST_LONG_TEXT = "at android.view.ViewGroup androidx.appcompat.app.AppCompatDelegateImpl.createSubDecor() (AppCompatDelegateImpl.java:896)" +
                "at void androidx.appcompat.app.AppCompatDelegateImpl.ensureSubDecor() (AppCompatDelegateImpl.java:806)";

        //set sound default
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //set sound custom
        Uri uri_cus = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.teemo);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("Test notification")
                .setContentText(TEST_LONG_TEXT)
                .setSmallIcon(R.drawable.chat)
//                .setLargeIcon(bitmap)
                .setSound(uri_cus)
                .setPriority(NotificationCompat.PRIORITY_MAX) //sét độ ưu tiên của thông báo: 5 độ(android < 8.0 - còn trên thì cài đặt channel)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(TEST_LONG_TEXT)) //hiển thị nội dung dài
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap_2).bigLargeIcon(null)) //Img lớn
                .setColor(getResources().getColor(R.color.design_default_color_primary))
                .build();
        //cách 1:
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification); //không cần check because nó tự check r :v

        //cách 2
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if(notification != null) {
//            //khác null mới thực hiện notify()
//            notificationManager.notify(getNotificationId(), notification);
//        }
    }

    private void sendNotification2() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chat);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setContentTitle("Test notification 2")
                .setContentText("Nguyễn Đình Huân 2")
                .setSmallIcon(R.drawable.chat)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.design_default_color_primary))
                .build();
        //cách 1:
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification); //không cần check because nó tự check r :v
    }

    private int getNotificationId(){
        return (int) new Date().getTime();
    }

    private void sendCustomNotifitcation() {
        //trạng thái collapsed
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification);
//        notificationLayout.setTextViewText(R.id.tvTilte, "this text"); //nội dung tương tự truyền vào
//        notificationLayout.setTextViewText(R.id.tvContent, "this text");

        //trạng thái expanded
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.custom_notification);
//        notificationLayoutExpanded.setTextViewText(R.id.tvTilte, "this text"); //nội dung tương tự truyền vào
//        notificationLayoutExpanded.setTextViewText(R.id.tvContent, "this text");


        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
                .setSmallIcon(R.drawable.chat)
                .setCustomContentView(notificationLayout) //custom notification: collapsed
                .setCustomBigContentView(notificationLayoutExpanded) //custom notification: expanded
                .build();
        //cách 1:
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(), notification); //không cần check because nó tự check r :v
    }
}