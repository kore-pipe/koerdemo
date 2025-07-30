package com.example.chapter7_server.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chapter7_server.Dao.BookDao;
import com.example.chapter7_server.entity.BookInfo;

@Database(entities = {BookInfo.class}, version = 1,exportSchema = true)
public abstract class BookDataBase extends RoomDatabase {

    //获取某张表的持久化对象
    public abstract BookDao bookDao();


}
