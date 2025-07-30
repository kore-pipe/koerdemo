package com.example.chapter7_server;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_string);
        TextView tv_resource = findViewById(R.id.tv_resource);
        String value = getString(R.string.weather_string);
        tv_resource.setText(value);

    }
}