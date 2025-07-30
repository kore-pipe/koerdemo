package com.example.chapter7_client;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter7_client.Util.ToastUtil;
import com.example.chapter7_client.entity.User;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_heigh;
    private EditText et_weight;
    private CheckBox ck_married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        et_heigh = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.bt_save).setOnClickListener(this);
        findViewById(R.id.bt_search).setOnClickListener(this);
        findViewById(R.id.bt_delete).setOnClickListener(this);


    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save:
                ContentValues values = new ContentValues();
                values.put(UserInfoContent.USER_NAME, et_name.getText().toString());
                values.put(UserInfoContent.USER_AGE, et_age.getText().toString());
                values.put(UserInfoContent.USER_HEIGHT, et_heigh.getText().toString());
                values.put(UserInfoContent.USER_WEIGHT, et_weight.getText().toString());
                values.put(UserInfoContent.USER_MARRIED, ck_married.isChecked() ? "是" : "否");

                getContentResolver().insert(UserInfoContent.CONTENT_URI, values);

                ToastUtil.show(this, "保存成功");

                break;
            case R.id.bt_search:
                Cursor cursor = getContentResolver().query(UserInfoContent.CONTENT_URI,
                        null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        User info = new User();
                        info.setId(cursor.getInt(cursor.getColumnIndex(UserInfoContent._ID)));
                        info.setName(cursor.getString(cursor.getColumnIndex(UserInfoContent.USER_NAME)));
                        info.setAge(cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_AGE)));
                        info.setHeight(cursor.getLong(cursor.getColumnIndex(UserInfoContent.USER_HEIGHT)));
                        info.setWeight(cursor.getFloat(cursor.getColumnIndex(UserInfoContent.USER_WEIGHT)));
                        info.setMarried(cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_MARRIED))
                                == 1 ? true : false);

                        Log.d("dong", info.toString());
                    }

                    cursor.close();
                }
                break;
            case R.id.bt_delete:

//                int count = getContentResolver().delete(UserInfoContent.CONTENT_URI, "name = ?"
//                        , new String[]{"Lily"});
                Uri uri = ContentUris.withAppendedId(UserInfoContent.CONTENT_URI, 2);
                int count = getContentResolver().delete(uri,null,null);
                if (count > 0) {
                    ToastUtil.show(this, "删除成功");
                }
                break;
        }


    }
}