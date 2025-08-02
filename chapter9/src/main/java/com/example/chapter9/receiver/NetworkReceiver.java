package com.example.chapter9.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            NetworkInfo networkInfo = intent.getParcelableExtra("networkInfo");
            String text = String.format("收到一个网络变更广播，网络大类为 %s,网络状态变化：%s"
                    ,networkInfo.getTypeName(),
                    networkInfo.getState());

            Log.d("dong",text);
        }
    }
}
