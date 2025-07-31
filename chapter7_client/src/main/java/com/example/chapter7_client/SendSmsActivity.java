package com.example.chapter7_client;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter7_client.Util.ToastUtil;

public class SendSmsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_appendix;
    private ActivityResultLauncher<Intent> mLauncher;

    private EditText et_phone;
    private EditText et_content;
    private EditText et_title;

    private Uri uriPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        iv_appendix = findViewById(R.id.iv_appendix);
        iv_appendix.setOnClickListener(this);
        mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    uriPic = intent.getData();
                    if (uriPic != null) {
                        iv_appendix.setImageURI(uriPic);
                        Log.d("dong", "picUri:" + uriPic.toString());

                    }
                }
            }
        });


        findViewById(R.id.btn_send).setOnClickListener(this);
        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_appendix:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //设置内容为图片
                intent.setType("image/*");
                //跳转到相册并返回
                mLauncher.launch(intent);

                break;
            case R.id.btn_send:
                sendSms(et_phone.getText().toString(),
                        et_content.getText().toString(),
                        et_title.getText().toString());
        }

    }
   //发送彩信，带图片
    private void sendSms(String phone, String content, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //允许对面读我发过去的uri
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        intent.putExtra("address",phone);
        intent.putExtra("subject",title);
        intent.putExtra("sms_body",content);
        intent.putExtra(Intent.EXTRA_STREAM,uriPic);
        intent.setType("image/*");

        //因为未指定要打开哪个页面，所以系统会在底部弹出选择窗口
        startActivity(intent);
        ToastUtil.show(this,"请在弹窗中选择");


    }
}