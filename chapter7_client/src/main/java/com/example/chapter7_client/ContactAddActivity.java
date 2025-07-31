package com.example.chapter7_client;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapter7_client.entity.Contact;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ContactAddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_name, et_phone, et_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);

        findViewById(R.id.btn_add_contact).setOnClickListener(this);
        findViewById(R.id.btn_query_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_contact:
                Contact contact = new Contact();
                contact.setEmail(et_email.getText().toString().trim());
                contact.setName(et_name.getText().toString().trim());
                contact.setPhone(et_phone.getText().toString().trim());

                //通过contentResolver添加联系人
                //方式一，使用ContentResolver多次写入，每次一个字段
                addContacts(getContentResolver(), contact);

                //方式2，使用ContentProviderOperation一次写入，一次性写入所有字段,要么全部成功，要么全部失败
                addFullContacts(getContentResolver(), contact);
                break;
            case R.id.btn_query_contact:
                readPhoneContacts(getContentResolver());
                break;
        }


    }

    @SuppressLint("Range")
    private void readPhoneContacts(ContentResolver resolver) {
        //先查询raw_contacts 表，在根据raw_contact_id查询data表
        Cursor cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI,
                new String[]{ContactsContract.RawContacts._ID},
                null, null, null, null);
        while (cursor.moveToNext()) {
            int rawContentId = cursor.getInt(0);
            Uri uri = Uri.parse("content://com.android.contacts/contacts" + rawContentId + "/data");
            Cursor dataCursor = resolver.query(uri, new String[]{
                    ContactsContract.Contacts.Data.MIMETYPE,
                    ContactsContract.Contacts.Data.DATA1,
                    ContactsContract.Contacts.Data.DATA2,

            }, null, null, null);

            Contact contact = new Contact();

            while (dataCursor.moveToNext()) {
                String data1 = dataCursor
                        .getString(dataCursor.getColumnIndex(ContactsContract.Contacts.Data.DATA1));
                String mineType = dataCursor
                        .getString(dataCursor.getColumnIndex(ContactsContract.Contacts.Data.MIMETYPE));
                switch (mineType) {
                    case ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE:
                        contact.setName(data1);
                        break;
                    case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                        contact.setPhone(data1);
                        break;
                    case ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE:
                        contact.setEmail(data1);
                        break;
                }
            }
            dataCursor.close();
            if (contact.getName() != null){
                Log.d("dong",contact.toString());
            }

        }
        cursor.close();




    }

    private void addFullContacts(ContentResolver contentResolver, Contact contact) {
        ContentProviderOperation op_main = ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build();
        //创建一个插入联系人姓名记录的内容操作器
        ContentProviderOperation op_name = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.Contacts.Data.DATA2, contact.getName())
                .build();

        ContentProviderOperation op_phone = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.Contacts.Data.DATA1, contact.getPhone())
                .withValue(ContactsContract.Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build();

        ContentProviderOperation op_email = ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.Contacts.Data.DATA1, contact.getEmail())
                .withValue(ContactsContract.Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .build();
        ArrayList<ContentProviderOperation> list = new ArrayList<>();
        list.add(op_main);
        list.add(op_name);
        list.add(op_phone);
        list.add(op_email);

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, list);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //往手机通讯录添加一个联系人信息
    private void addContacts(ContentResolver resolver, Contact contact) {
        ContentValues values = new ContentValues();
        //往 raw_contacts表插入数据,并获取添加后的联系人编号
        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(uri);
        //姓名
        ContentValues name = new ContentValues();
        //关联联系人编号
        name.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        //姓名的数据类型
        name.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        //联系人名字
        name.put(ContactsContract.Contacts.Data.DATA2, contact.getName());
        resolver.insert(ContactsContract.Data.CONTENT_URI, name);


        //电话
        ContentValues phone = new ContentValues();
        //关联联系人编号
        name.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        //姓名的数据类型
        name.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        //联系人名字
        name.put(ContactsContract.Contacts.Data.DATA1, contact.getPhone());
        name.put(ContactsContract.Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        resolver.insert(ContactsContract.Data.CONTENT_URI, phone);

        //邮箱
        ContentValues email = new ContentValues();
        //关联联系人编号
        name.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        //姓名的数据类型
        name.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        //联系人名字
        name.put(ContactsContract.Contacts.Data.DATA1, contact.getEmail());
        name.put(ContactsContract.Contacts.Data.DATA2, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        resolver.insert(ContactsContract.Data.CONTENT_URI, email);


    }
}