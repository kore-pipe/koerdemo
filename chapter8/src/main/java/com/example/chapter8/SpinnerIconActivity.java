package com.example.chapter8;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter8.Util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private  static final int[] iconArray = {
            R.drawable.diqiu,
            R.drawable.huoxing,
            R.drawable.jinxing,
            R.drawable.muxing,
            R.drawable.tuxing,
            R.drawable.shuixing

    };

    private static final String[] starArray = {
            "地球",
            "火星",
            "金星",
            "木星",
            "土星",
            "水星"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < iconArray.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("icon",iconArray[i]);
            item.put("name",starArray[i]);
            list.add(item);
        }

        SimpleAdapter startAdapter = new SimpleAdapter(this,list,R.layout.item_simple,
                new String[]{"icon","name"},
                new int[]{R.id.iv_icon,R.id.tv_name});

        Spinner sp_icon = findViewById(R.id.sp_spinner);
        sp_icon.setAdapter(startAdapter);
        sp_icon.setSelection(0);
        sp_icon.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"你点击了第"+starArray[position]+"项");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}