package com.example.chapter7_server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.chapter7_server.UserInfoContent;
import com.example.chapter7_server.database.UserDBHelper;

public class UserInfoProvider extends ContentProvider {

    private UserDBHelper dbHelper;

    private static final UriMatcher URI_MATCHER =new UriMatcher(UriMatcher.NO_MATCH);
    private static final int USERS = 1;
    private static final int USER = 2;

    static {
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES, "/user", USERS);
        URI_MATCHER.addURI(UserInfoContent.AUTHORITIES, "/user/#", USER);
    }


    public UserInfoProvider() {
    }

    @Override
    public boolean onCreate() {
        Log.d("dong", "UserInfoProvider onCreate");
        dbHelper = UserDBHelper.getInstance(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (URI_MATCHER.match( uri)){
            //删除多行
            case USERS:
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                count = db1.delete(UserDBHelper.TABLE_NAME, selection, selectionArgs);
                db1.close();
                break;
                //删除单行
            case USER:
                String id = uri.getLastPathSegment();
                SQLiteDatabase db2 = dbHelper.getWritableDatabase();
                db2.delete(UserDBHelper.TABLE_NAME,"_id=?",new String[]{id});
                db2.close();


                break;
        }


        Log.d("dong", "UserInfoProvider delete");
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    //content://com.example.chapter7_server.provider.UserInfoProvider/user
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("dong", "UserInfoProvider insert");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(UserDBHelper.TABLE_NAME, null, values);
        db.close();
        return uri;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("dong", "UserInfoProvider query");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(UserDBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);


    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}