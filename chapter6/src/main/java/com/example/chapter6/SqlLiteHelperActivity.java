package com.example.chapter6;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter6.database.UserDBHelper;
import com.example.chapter6.entity.User;
import com.example.chapter6.util.ToastUtil;

public class SqlLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_age, et_name, et_high, et_weight;
    private CheckBox ck_married2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_helper);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        et_high = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        ck_married2 = findViewById(R.id.ck_married2);

        findViewById(R.id.bt_save2).setOnClickListener(this);
        findViewById(R.id.bt_search).setOnClickListener(this);
        findViewById(R.id.bt_delete).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);

    }

    UserDBHelper mHelper;

    @Override
    protected void onStart() {
        super.onStart();
        mHelper = UserDBHelper.getInstance(this);
        mHelper.openReadLink();
        mHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.closeLink();

    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        int age = Integer.parseInt(et_age.getText().toString());
        long height = Long.parseLong(et_high.getText().toString());
        float weight = Float.parseFloat(et_weight.getText().toString());
        boolean married = ck_married2.isChecked();
        User user = null;
        switch (v.getId()) {
            case R.id.bt_save2:
                user = new User(name, age, height, weight, married);
                long id = mHelper.insert(user);
                if(id > 0){
                    ToastUtil.show(this,"添加成功！");
                }
                break;
        }

    }
}