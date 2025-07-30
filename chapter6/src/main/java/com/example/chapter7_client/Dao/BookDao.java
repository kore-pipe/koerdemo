package com.example.chapter7_client.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chapter7_client.entity.BookInfo;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(BookInfo... bookInfos);
    @Delete
    void delete(BookInfo... bookInfo);
    @Query("DELETE FROM BookInfo")
    void deleteAll();
    @Update
    void update(BookInfo... bookInfo);
    @Query("SELECT * FROM BookInfo")
    List<BookInfo> queryAll();

    @Query("SELECT * FROM BookInfo WHERE name = :name ORDER BY id DESC limit 1")
    BookInfo queryByName(String name);

}
