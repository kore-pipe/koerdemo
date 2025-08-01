package com.example.chapter8;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter8.Util.ToastUtil;
import com.example.chapter8.adapter.PlanetGridAdapter;
import com.example.chapter8.bean.Planet;

import java.util.List;

public class GridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gv_planet;

    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

         gv_planet = findViewById(R.id.gv_planet);
         planetList = Planet.getDefaultList();

        PlanetGridAdapter adapter = new PlanetGridAdapter(this,planetList);
        gv_planet.setAdapter(adapter);

        gv_planet.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ToastUtil.show(this,"点击了："+planetList.get(position).name);

    }
}