package com.example.chapter9.receiver;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.net.PortUnreachableException;

public  class AlarmReceiver extends BroadcastReceiver {
    public static final String ALARM_ACTION = "com.example.chapter9.alarm";
    private Context mContext;

    public AlarmReceiver(Context context){
        super();
        mContext = context;
    }
    {

    }
    @Override
    public void onReceive(Context context, Intent intent) {
         if(intent != null && intent.getAction().equals(ALARM_ACTION)){
             Log.d("dong","收到一个定时广播");
         }
    }

    public void  sendAlaRm(){
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,1000,pendingIntent);

        }else {
            alarmManager.set(AlarmManager.RTC_WAKEUP,1000,pendingIntent);

        }

    }
}
