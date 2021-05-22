package com.example.broadcastreceiverpart1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class BroadcastReceiverExample extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if(isNetworkAvailable(context)){
                Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Internet disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null)
            return false;

        //mỗi version check 1 kiểu
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network = connectivityManager.getActiveNetwork();
            if(network == null)
                return false;

            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}
