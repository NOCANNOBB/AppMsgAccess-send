package com.example.zhang.backgroundsoundplay;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by zhang on 2017/8/21.
 */

public class MyContentProvider extends ContentProvider {
    private static final UriMatcher um;
    static {
        um = new UriMatcher(UriMatcher.NO_MATCH);
        um.addURI("com.bn.pp4.provider.student","stu",1);
    }
    SQLiteDatabase sld;

    @Override
    public String getType(Uri uri)
    {
        return  null;
    }


    @Override
    public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs,String sortOrder){
        switch (um.match(uri)){
            case 1:
                Cursor cur = sld.query("student",projection,selection,selectionArgs,null,null,sortOrder);
                return cur;
        }
        return null;
    }
    @Override
    public int delete(Uri uri,String arg1,String[] arg2)
    {
        return 0;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values){
        return null;
    }
    @Override
    public boolean onCreate(){
        sld = SQLiteDatabase.openDatabase("/data/data/com.bn.pp4/mydb",null,
                SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
        return false;
    }
    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs)
    {
        return 0;
    }
}
