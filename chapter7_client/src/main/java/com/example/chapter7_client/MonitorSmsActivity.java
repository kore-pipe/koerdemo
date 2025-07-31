package com.example.chapter7_client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MonitorSmsActivity extends AppCompatActivity {
    private SmsGetObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_sms);
        //给指定Uri容器注册内容观察器，一但发生数据改变时，就触发观察器的onChange()方法
        Uri uri = Uri.parse("content://sms");
        mObserver = new SmsGetObserver(this);
        getContentResolver().registerContentObserver(uri, true, mObserver);
    }

    private static class SmsGetObserver extends ContentObserver {

        private Context mContext;

        /**
         * Creates a content observer.
         */
        public SmsGetObserver(Context context) {
            super(new Handler(Looper.getMainLooper()));
            this.mContext = context;


        }

        @SuppressLint("Range")
        @Override
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);
            if (uri == null) {
                return;
            }
            if (uri.toString().contains("content://sms/raw") ||
                    uri.toString().equals("content://sms")) {
                return;
            }
            //通过内容解析器获取符合条件的结果集游标
            Cursor cursor = mContext.getContentResolver().query(uri, new String[]{"address", "body", "date"}
                    , null, null, "date desc");
            if (cursor.moveToNext()) {
                //短信的发送号码
                String sender = cursor.getString(cursor.getColumnIndex("address"));

                //短信的内容
                String body = cursor.getString(cursor.getColumnIndex("body"));
                 Log.d("ning",String.format("短信的内容是：%s,发送号码是：%s",body,sender));
            }
            cursor.close();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mObserver);
    }
}