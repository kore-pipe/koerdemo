package com.example.chapter7_client;



import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter7_client.Util.PermissionUtil;
import com.example.chapter7_client.Util.ToastUtil;

import java.io.File;

public class ProviderApkActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_apk);
        findViewById(R.id.btn_install).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            Log.d("dong","android 11+");
            checkAndInstall();
        }
        installApk();

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkAndInstall() {
        //检查是否拥有MANAGE_EXTERNAL_STORAGE权限，没有则跳到设置页面
        if(!Environment.isExternalStorageManager()){
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.fromParts("package",getPackageName(),null));
            startActivity(intent);



        }else {
            if(PermissionUtil.checkPermissions(this,PERMISSIONS,PERMISSION_REQUEST_CODE)){
            installApk();}
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE
                && PermissionUtil.checkGrant(grantResults)){
            installApk();
        }


    }

    private void installApk() {
        String apkPath = Environment.
                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
                + "/chapter6_debug.apk";
        Log.d("dong","apk path:"+apkPath);

        //获取包管理器
        PackageManager pm = getPackageManager();

        //获取apk文件信息
        PackageInfo pi = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if(pi == null){
            ToastUtil.show(this,"未找到该apk文件");
            return;
        }
        Uri uri = Uri.parse(apkPath);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
             uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(apkPath));
             Log.d("dong",String.format("new uri:%s",uri.toString()));
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);



    }
}