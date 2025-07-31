package com.example.chapter6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter6.Dao.BookDao;
import com.example.chapter6.entity.BookInfo;
import com.example.chapter6.util.ToastUtil;

import java.util.List;

public class RoomWriteActivity2 extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_price;
    private EditText et_author;
    private EditText et_publish;

    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room_write2);

        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        et_author = findViewById(R.id.et_author);
        et_publish = findViewById(R.id.et_publish);

        findViewById(R.id.bt_save).setOnClickListener(this);
        findViewById(R.id.bt_search).setOnClickListener(this);
        findViewById(R.id.bt_delete).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);

        bookDao = MyApplication.getInstance().getBookDB().bookDao();
    }

    @Override
    public void onClick(View v) {
        String price = et_price.getText().toString();
        String publish = et_publish.getText().toString();
        String author = et_author.getText().toString();
        String name = et_name.getText().toString();

        switch (v.getId()) {
            case R.id.bt_save:
                BookInfo b1 = new BookInfo();
                b1.setAuthor(author);
                b1.setName(name);
                b1.setPublish(publish);
                b1.setPrice(Double.parseDouble(price));
                bookDao.insert(b1);
                ToastUtil.show(this,"保存成功！");
                break;
            case R.id.bt_search:
                List<BookInfo> list = bookDao.queryAll();
                for (BookInfo b : list) {
                    Log.d("dong",b.toString());

                }

                break;
            case R.id.bt_delete:

                bookDao.delete();
                break;
            case R.id.bt_update:

                break;
        }
    }
}