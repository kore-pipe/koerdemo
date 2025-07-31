package com.example.chapter6;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.chapter6.database.BookDataBase;

import java.util.HashMap;

public class MyApplication extends Application {
    private static MyApplication mApp;

    public HashMap<String,String> infoMap = new HashMap<>();
    public static  MyApplication getInstance(){
        return mApp;
    }

    //声明一个数据库对象
    private BookDataBase bookDatabase;



   //在app启动的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("dong","MyApplication onCreate");
        mApp = this;


        bookDatabase = Room.databaseBuilder(this,BookDataBase.class,"book")
                //如果没有这个参数，发生数据库变更时，默认删除原数据库再新建数据库
                .addMigrations()
                .allowMainThreadQueries()
                .build();
    }
    public BookDataBase getBookDB(){
        return bookDatabase;
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
