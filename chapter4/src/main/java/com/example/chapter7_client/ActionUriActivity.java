package com.example.chapter7_client;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActionUriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_uri);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String phoneNum = "20086";
        switch (v.getId()) {
            case R.id.btn_dial:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:" + phoneNum);
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                Uri uri2 = Uri.parse("smsto:" + phoneNum);
                intent.setData(uri2);
                startActivity(intent);
                break;
            case R.id.btn_my:
                intent = new Intent();
                intent.setAction("android.intent.action.DONG");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
                break;
        }

    }
}