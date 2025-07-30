package com.example.chapter7_client.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chapter7_client.Dao.BookDao;
import com.example.chapter7_client.entity.BookInfo;

@Database(entities = {BookInfo.class}, version = 1,exportSchema = true)
public abstract class BookDataBase extends RoomDatabase {

    //获取某张表的持久化对象
    public abstract BookDao bookDao();


}
