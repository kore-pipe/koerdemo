package com.example.chapter6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chapter6.entity.User;

public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";
    private static final String TABLE_NAME = "user_info";
    private static final int DB_VERSION = 1;

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
    public SQLiteDatabase openReadLink(){
        if (mRDB==null||!mRDB.isOpen()){
            mRDB = mhelper.getReadableDatabase();

        }
        return mRDB;
    }
    //打开数据库的写链接
    public SQLiteDatabase openWriteLink(){
        if(mWDB == null || !mWDB.isOpen()){
            mWDB = mhelper.getWritableDatabase();
        }
        return mWDB;
    }
    //关闭数据库连接
    public void closeLink(){
        if(mRDB!= null && mRDB.isOpen()){
            mRDB.close();
            mRDB = null;
        }
        if(mWDB != null && mWDB.isOpen()){
            mWDB.close();
            mWDB = null;
        }
    }
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

    public long insert(User user){
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        values.put("married", user.isMarried()?1:0);
        return mWDB.insert(TABLE_NAME, null, values);



    }
}
