package com.example.chapter7_client;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_database;
    String mDataBase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        tv_database = findViewById(R.id.tv_database);
        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        mDataBase = getFilesDir() + "/test.db";

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                SQLiteDatabase db = openOrCreateDatabase(mDataBase, Context.MODE_PRIVATE, null);
                String desc = String.format("数据库%s创建%s",db.getPath(),(db!=null)?"成功":"失败");
                tv_database.setText(desc);
                break;
            case R.id.btn_delete:
                boolean result = deleteDatabase(mDataBase);
                 desc = String.format("数据库%s删除%s",mDataBase,result?"成功":"失败");
                 tv_database.setText(desc);

                break;
        }

    }
}