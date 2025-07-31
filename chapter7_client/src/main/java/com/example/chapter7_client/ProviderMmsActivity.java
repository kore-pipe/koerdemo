package com.example.chapter7_client;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter7_client.Util.FileUtil;
import com.example.chapter7_client.Util.PermissionUtil;
import com.example.chapter7_client.Util.ToastUtil;
import com.example.chapter7_client.Util.Utils;
import com.example.chapter7_client.entity.ImageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProviderMmsActivity extends AppCompatActivity {

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    private static final int PERMISSION_REQUEST_CODE = 1;
    private List<ImageInfo> imageInfoList = new ArrayList<>();
    private GridLayout gl_app;

    private EditText et_phone;
    private EditText et_message;
    private EditText et_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_mms);
        gl_app = findViewById(R.id.gl_app);
        //手动让mediaStore扫描入库
        MediaScannerConnection.scanFile(this, new String[]{
                Environment.getExternalStorageDirectory().getPath()}, null, null);

        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);

        if (PermissionUtil.checkPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)) {
            //加载图片链表
            loadImageList();

            //显示图片网格
            showImageGrid();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && PermissionUtil.checkGrant(grantResults)) {
            //加载图片链表
            loadImageList();

            //显示图片网格
            showImageGrid();

        }
    }

    private void showImageGrid() {
        gl_app.removeAllViews();
        for (ImageInfo info : imageInfoList) {
            ImageView iv_appendix = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeFile(info.getPath());
            iv_appendix.setImageBitmap(bitmap);
            //设置图像缩放类型
            iv_appendix.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //设置图像视图的布局参数
            int px = Utils.dip2px(this, 110);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px, px);
            iv_appendix.setLayoutParams(params);
            // 设置图像视图的内部间距
            int padding = Utils.dip2px(this, 5);
            iv_appendix.setPadding(padding, padding, padding, padding);
            iv_appendix.setOnClickListener(v -> {
                //发送彩信
                sendSms(et_phone.getText().toString(),
                        et_message.getText().toString(),
                        et_title.getText().toString(),
                        info.path


                );


            });

            //把图像视图添加至网格布局中
            gl_app.addView(iv_appendix);


        }

    }

    @SuppressLint("Range")
    private void loadImageList() {
        String[] columns = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATA//文件路径
        };
        //通过mediaStore获取图片（音频，视频等也可以获取
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 307200",
                null,
                "_size desc"

        );
        int count = 0;
        if (cursor != null && count < 6) {
            while (cursor.moveToNext()) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
                imageInfo.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE)));
                imageInfo.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                imageInfo.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE)));

                if (FileUtil.checkFile(this, imageInfo.getPath())) {
                    imageInfoList.add(imageInfo);
                    count++;
                    Log.d("dong", "image" + imageInfo.toString());
                }


            }
        }
    }

    private void sendSms(String phone, String content, String title, String path) {
        Uri uri = Uri.parse(path);
        //兼容7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(this, getString(R.string.file_provider), new File(path));

            Log.d("dong", String.format("new uri:%s", uri.toString()));

        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //允许对面读我发过去的uri
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        intent.putExtra("address", phone);
        intent.putExtra("subject", title);
        intent.putExtra("sms_body", content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        intent.setType("image/*");

        //因为未指定要打开哪个页面，所以系统会在底部弹出选择窗口
        startActivity(intent);
        ToastUtil.show(this, "请在弹窗中选择");


    }
}