package com.example.chapter7_server;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.btn_start).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, FinishActivity.class));


    }
}