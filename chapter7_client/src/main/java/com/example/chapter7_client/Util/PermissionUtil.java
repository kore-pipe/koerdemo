package com.example.chapter7_client.Util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    //检查多个权限，返回true表示已启用权限
    public static boolean checkPermissions(Activity act,String[] permissions,int requestCode){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int check = PackageManager.PERMISSION_GRANTED;
            for(String permission:permissions){
                check=ContextCompat.checkSelfPermission(act,permission);
                if(check != PackageManager.PERMISSION_GRANTED){
                    break;
                }

            }
            //未开启权限，请求系统弹窗
            if(check != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(act,permissions,requestCode);
                return false;
            }
        }


        return true;

    }


    //检查权限结果数组
    public static boolean checkGrant(int[] grantResults) {
        if(grantResults!=null){
            for (int result : grantResults) {
                if(result != PackageManager.PERMISSION_GRANTED){

                    return  false;

                }
            }
        }
        return true;
    }
}
