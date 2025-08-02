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

import com.example.chapter9.receiver.OrderAReceiver;
import com.example.chapter9.receiver.OrderBReceiver;

public class BroadOrderActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ACTION_ORDER = "com.example.chapter9.action.order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_broad_order);
        findViewById(R.id.btn_send_standard).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //创建一个指定动作的意图
        Intent intent = new Intent(ACTION_ORDER);
        sendOrderedBroadcast(intent, null);


    }

    private OrderAReceiver orderAReceiver;
    private OrderBReceiver orderBReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        //优先级越大的接收器，越早收到有序广播
        //优先级相同的接收器，越早注册越早收到有序广播
        orderAReceiver = new OrderAReceiver();
        IntentFilter filterA = new IntentFilter(ACTION_ORDER);
        filterA.setPriority(8);
        registerReceiver(orderAReceiver, filterA);

        orderBReceiver = new OrderBReceiver();
        IntentFilter filterB = new IntentFilter(ACTION_ORDER);
        filterB.setPriority(10);
        registerReceiver(orderBReceiver, filterB);


    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(orderAReceiver);
        unregisterReceiver(orderBReceiver);
    }
}