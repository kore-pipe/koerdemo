package com.example.chapter9;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter9.receiver.StandardReceiver;

public class BroadStandardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_standard);

        findViewById(R.id.btn_send_standard).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(StandardReceiver.ACTION_STANDARD);
        sendBroadcast(intent);

    }
    private  StandardReceiver standardReceiver;
    @Override
    protected void onStart() {
        super.onStart();
        standardReceiver = new StandardReceiver();
        IntentFilter filter = new IntentFilter(StandardReceiver.ACTION_STANDARD);

        registerReceiver(standardReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //注销接收器
        unregisterReceiver(standardReceiver);
    }
}