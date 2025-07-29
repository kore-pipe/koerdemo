package com.example.chapter6;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter6.util.viewUtil;

public class DditFocusActivity extends AppCompatActivity  {
    EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddit_focus);
        et_phone = findViewById(R.id.et_phone);
        EditText et_password = findViewById(R.id.et_password);
//        et_password.setOnFocusChangeListener(this);
        et_phone.addTextChangedListener(new HideTextwatcher(et_phone,11));
        et_password.addTextChangedListener(new HideTextwatcher(et_password,6));



    }



    private  class HideTextwatcher implements TextWatcher {
        private EditText mView;
        private int mMaxLength;
        public HideTextwatcher(EditText etPhone, int maxLength) {
            this.mView = etPhone;
            this.mMaxLength = maxLength;

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //获得已输入的文本字符串
             String str = s.toString();
             if(str.length() == mMaxLength){
                 viewUtil.hideOneInputMethod(DditFocusActivity.this,mView);

        }
    }

//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if(hasFocus){
//            String phoneNum = et_phone.getText().toString();
//            if(TextUtils.isEmpty(phoneNum) || phoneNum.length() < 11){
//                et_phone.requestFocus();
//                Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
//
//
//            }
//        }

    }
}