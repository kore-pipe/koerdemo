package com.example.chapter6;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter6.util.FileUtil;
import com.example.chapter6.util.ToastUtil;

public class FileWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_high;
    private EditText et_weight;
    private CheckBox ck_married;
    private String path;
    private TextView tv_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_high = findViewById(R.id.et_high);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);
        tv_txt = findViewById(R.id.tv_txt);
        //初始话path变量
        //外部存储公有空间
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        //外部存储私有空间
        //directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
        String fileName =System.currentTimeMillis()+".txt";
        path = directory + "/" + fileName;

        findViewById(R.id.bt_save).setOnClickListener(this);
        findViewById(R.id.bt_read).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save:
                String age = et_age.getText().toString();
                String high = et_high.getText().toString();
                String weight = et_weight.getText().toString();
                String name = et_name.getText().toString();

                StringBuilder sb = new StringBuilder();
                sb.append("姓名：").append(name).append("\n");
                sb.append("年龄：").append(age).append("\n");
                sb.append("身高：").append(high).append("\n");
                sb.append("体重：").append(weight).append("\n");
                sb.append("是否已婚：").append(ck_married.isChecked()?"是":"否").append("\n");
                //外部存储的私有空间

                FileUtil.saveText(path,sb.toString());
                ToastUtil.show(this,"保存成功");
                break;
            case R.id.bt_read:
                String s = FileUtil.openText(path);
                Log.d("FileWriteActivity : ",s);
                tv_txt.setText(s);
                break;
        }

    }
}