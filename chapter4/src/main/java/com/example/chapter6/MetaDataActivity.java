package com.example.chapter6;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MetaDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_data);
        TextView tv_metadata = findViewById(R.id.tv_metaData);
        PackageManager pm = getPackageManager();
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            String value = activityInfo.metaData.getString("weather");
            tv_metadata.setText(value);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}