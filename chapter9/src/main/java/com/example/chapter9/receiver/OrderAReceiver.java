package com.example.chapter9.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chapter9.BroadOrderActivity;

public class OrderAReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!= null && intent.getAction().equals(BroadOrderActivity.ACTION_ORDER)){
            Log.d("dong","收到一个有序广播 OrderAReceiver");
        }
    }
}
