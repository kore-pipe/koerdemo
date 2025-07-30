package com.example.chapter7_client;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter7_client.Util.PermissionUtil;

public class PermissionLazyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] PERMISSIONS_CONTACTS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };
    private static final String[] PERMISSIONS_SMS = new String[]{
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS
    };

    private static final int REQUEST_CODE_CONTACTS = 1;
    private static final int REQUEST_CODE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_contacts).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sms:
                PermissionUtil.checkPermissions(this,PERMISSIONS_SMS,REQUEST_CODE_SMS);
                break;
            case R.id.btn_contacts:
                PermissionUtil.checkPermissions(this,PERMISSIONS_CONTACTS,REQUEST_CODE_CONTACTS);

                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CONTACTS:
                if(PermissionUtil.checkGrant(grantResults)){
                    Log.d("dong","通讯录读写权限申请成功");
                }else {
                    Log.d("dong","通讯录读写权限申请失败");
                    jumpToAppSetting();
                }

                break;
            case REQUEST_CODE_SMS:
                if(PermissionUtil.checkGrant(grantResults)){
                    Log.d("dong","收发短信权限申请成功");
                }else {
                    Log.d("dong","收发短信权限申请失败");
                    jumpToAppSetting();
                }

                break;
        }
    }

    //跳转到应用设置界面
    private void jumpToAppSetting(){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package",getPackageName(),null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}