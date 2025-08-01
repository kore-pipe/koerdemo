package com.example.chapter8;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter8.Util.ToastUtil;
import com.example.chapter8.adapter.PlanetBaseAdapter;
import com.example.chapter8.bean.Planet;

import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<Planet> planList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
        Spinner sp_planet = findViewById(R.id.sp_planet);

         planList = Planet.getDefaultList();

        PlanetBaseAdapter adapter = new PlanetBaseAdapter(this,planList);
        sp_planet.setAdapter(adapter);
        sp_planet.setSelection(0);
        sp_planet.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this,"你点击了第"+planList.get(position).name+"项");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}