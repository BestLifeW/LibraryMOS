package com.rjxy.librarymos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lovec on 2016/9/22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Library_DB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String USERINFO = "user_info";
    public static final String BOOKINFO ="book_info";
    public static final String ADMININFO="admin_info";
    private static final String TAG = "DatabaseHelper";
    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "数据库初始化");
        init(db);
    }
    /*
    * 初始化数据
    * */
    private void init(SQLiteDatabase db) {
        db.execSQL("create table " + USERINFO + "(  _id integer primary key AUTOINCREMENT,number varchar(30) ,password varchar(30),name varchar(30))");
        db.execSQL("create table " + ADMININFO + "( number varchar(30) primary key,password varchar(30))");
        initAdmin(db);
    }
    /*
    * 初始化管理员账户密码
    * */
    private void initAdmin(SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put("number","Admin");
        value.put("password","admin");
        db.insert(ADMININFO,null,value);
        Log.i(TAG, "初始化管理员数据成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
