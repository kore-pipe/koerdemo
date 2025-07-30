package com.example.chapter6;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class MyApplication extends Application {
    private static MyApplication mApp;

    public HashMap<String,String> infoMap = new HashMap<>();
    public static  MyApplication getInstance(){
        return mApp;
    }
   //在app启动的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("dong","MyApplication onCreate");
    }
    //终止的时候调用
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
   //在配置改变的时候调用，例如从竖屏变为横屏
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("dong","onConfigurationChanged");
    }
}
