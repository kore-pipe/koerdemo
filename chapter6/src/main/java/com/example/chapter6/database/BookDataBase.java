package com.example.chapter6.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chapter6.Dao.BookDao;
import com.example.chapter6.entity.BookInfo;

@Database(entities = {BookInfo.class}, version = 1,exportSchema = true)
public abstract class BookDataBase extends RoomDatabase {

    //获取某张表的持久化对象
    public abstract BookDao bookDao();


}
