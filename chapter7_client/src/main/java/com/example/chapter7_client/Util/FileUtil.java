package com.example.chapter7_client.Util;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.example.chapter7_client.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    //把字符串保存到指定路径的文本文件
    public static void saveText(String path, String content) {
        BufferedWriter os = null;
        try {
            os = new BufferedWriter(new FileWriter(path));
            os.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String openText(String path) {
        BufferedReader is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = is.readLine()) != null) {
                Log.d("FileUtil", "Read line: " + line);
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return sb.toString();

    }

    public static void saveImage(String path, Bitmap bitmap) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public static Bitmap openImage(String path) {
        Bitmap bitmap = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(path);
            bitmap = BitmapFactory.decodeStream(fis);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return bitmap;

    }

    //检查文件是否存在，以及文件是否合法
    public static boolean checkFile(Context context,String path) {
        File file = new File(path);

        if(!file.exists() || file.length() <= 0 ||!file.isFile()){
            return  false;
        }
        try{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            FileProvider.getUriForFile(context, context.getString(R.string.file_provider),file );

        }
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }


        return true;


    }

}
