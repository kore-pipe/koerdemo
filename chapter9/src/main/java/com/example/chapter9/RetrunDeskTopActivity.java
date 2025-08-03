package com.example.chapter9;

import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Rational;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RetrunDeskTopActivity extends AppCompatActivity {

    private DesktopReceiver desktopReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrun_desk_top);
        desktopReceiver = new DesktopReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(desktopReceiver, intentFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(desktopReceiver);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, @NonNull Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode) {
            Log.d("dong", "进入画中画模式");

        } else {
            Log.d("dong", "退出画中画模式");

        }
    }

    private class DesktopReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                //回到桌面或者打开了任务列表
                String reasons = intent.getStringExtra("reason");

                if (!TextUtils.isEmpty(reasons) &&
                        (reasons.equals("homekey") || reasons.equals("recentapps"))) {
                    //提供画中画模式
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !isInPictureInPictureMode()) {
                        //创建画中画构建器
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
                        Rational ratio = new Rational(10, 5);
                        builder.setAspectRatio(ratio);
                        //进入画中画模式
                        enterPictureInPictureMode(builder.build());
                    }
                }
            }
        }
    }
}