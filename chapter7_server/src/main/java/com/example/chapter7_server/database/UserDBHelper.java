package com.example.chapter7_server.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "user.db";
    public static final String TABLE_NAME = "user_info";
    public static final int DB_VERSION = 1;

    private static UserDBHelper mhelper = null;

    private SQLiteDatabase mRDB = null;
    private SQLiteDatabase mWDB = null;
    public static UserDBHelper getInstance(Context context){
        if(mhelper == null){
            mhelper = new UserDBHelper(context);
        }
        return mhelper;
    }
    //打开数据库的读链接

    //关闭数据库连接

    private UserDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "name VARCHAR NOT NULL," +
                    "age INTEGER NOT NULL," +
                    "height LONG NOT NULL," +
                    "weight FLOAT NOT NULL," +
                    "married INTEGER NOT NULL);";
            db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





}
