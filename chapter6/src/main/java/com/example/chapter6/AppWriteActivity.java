package com.example.chapter6;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AppWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_high;
    private EditText et_weight;
    private CheckBox ck_married;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_high = findViewById(R.id.et_high);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.bt_save).setOnClickListener(this);
        //初始化容器
        app = MyApplication.getInstance();

        reload();

    }

    private void reload() {

        String name = app.infoMap.get("name");
        if(name == null){
            return;
        }
        et_name.setText(name);

        String age = app.infoMap.get("age");
        et_age.setText(age);

        String high = app.infoMap.get("high");
        et_high.setText(high);

        String weight = app.infoMap.get("weight");
        et_weight.setText(weight);

        String married = app.infoMap.get("married");

        if ("是".equals(married)) {
            ck_married.setChecked(true);
        } else {
            ck_married.setChecked(false);
        }


    }

    @Override
    public void onClick(View v) {
        String age = et_age.getText().toString();
        String high = et_high.getText().toString();
        String weight = et_weight.getText().toString();
        String name = et_name.getText().toString();


        app.infoMap.put("name", name);
        app.infoMap.put("age", age);
        app.infoMap.put("high", high);
        app.infoMap.put("weight", weight);
        app.infoMap.put("married", ck_married.isChecked() ? "是" : "否");


    }
}