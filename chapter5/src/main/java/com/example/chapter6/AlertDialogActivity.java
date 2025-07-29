package com.example.chapter6;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        findViewById(R.id.btn_alert).setOnClickListener(this);
         tv_alert = findViewById(R.id.tv_alert);

    }

    @Override
    public void onClick(View v) {
        //创建提醒对话框的建造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("尊敬的用户");
        builder.setMessage("你真的要卸载我吗？").setPositiveButton("残忍卸载",(dialog, which) -> {
             tv_alert.setText("虽然依依不舍，但只能离开");
        });
        builder.setNegativeButton("我再想想",(dialog, which) -> {
            tv_alert.setText("让我再陪你365个日夜");
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}