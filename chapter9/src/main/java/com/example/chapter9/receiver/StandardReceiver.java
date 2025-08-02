package com.example.chapter9.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StandardReceiver extends BroadcastReceiver {
    public static final String ACTION_STANDARD = "com.example.chapter9.action.standard";
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null && intent.getAction().equals(ACTION_STANDARD)){
            Log.d("dong","收到一个标准广播");
        }

    }
}
