package com.example.broadcastreceiverpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiverExample broadcastReceiverExample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiverExample = new BroadcastReceiverExample();

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiverExample, intentFilter);
    }

    //app chạy nền thì sẽ hoạt động
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiverExample);
    }

    //app khi nào tắt hẳn đi thì thực hiện
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(broadcastReceiverExample);
//    }
}