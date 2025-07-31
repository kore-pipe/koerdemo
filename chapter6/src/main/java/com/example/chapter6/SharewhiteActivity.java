package com.example.chapter6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SharewhiteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_age;
    private EditText et_high;
    private EditText et_weight;
    private CheckBox ck_married;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharewhite);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_high = findViewById(R.id.et_high);
        et_weight = findViewById(R.id.et_weight);
        ck_married = findViewById(R.id.ck_married);

        findViewById(R.id.bt_save).setOnClickListener(this);
        preferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        reload();


    }

    private void reload() {
        int age = preferences.getInt("age", 0);
        if (age != 0){
            et_age.setText(age + "");
        }
        float high = preferences.getFloat("high", 0f);
        if (high != 0f){
            et_high.setText(high + "");
        }
        float weight = preferences.getFloat("weight", 0f);
        if (weight != 0f){
            et_weight.setText(weight + "");
        }
        boolean married = preferences.getBoolean("married", false);
        ck_married.setChecked(married);
        String name = preferences.getString("name", null);
        if (name != null){
            et_name.setText(name);
        }


    }

    @Override
    public void onClick(View v) {
        String age = et_age.getText().toString();
        String high = et_high.getText().toString();
        String weight = et_weight.getText().toString();
        String name = et_name.getText().toString();
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("name", name);
        edit.putInt("age", Integer.parseInt(age));
        edit.putFloat("high", Float.parseFloat(high));
        edit.putFloat("weight", Float.parseFloat(weight));
        edit.putBoolean("married", ck_married.isChecked());
        edit.commit();

    }
}