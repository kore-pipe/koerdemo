package com.example.chapter8;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chapter8.Util.ToastUtil;

public class SpinnerDropDownActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final static String[] starArray = {"水星","金星","地球","火星","木星","土星"};
    private Spinner sp_dropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_drop_down);
        sp_dropDown = findViewById(R.id.sp_dropDown);

        //声明一个数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                R.layout.item_selector, starArray);

        sp_dropDown.setAdapter(starAdapter);
        //默认选中第一项
        sp_dropDown.setSelection(0);

        sp_dropDown.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, "你选择了：" + starArray[position]);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}