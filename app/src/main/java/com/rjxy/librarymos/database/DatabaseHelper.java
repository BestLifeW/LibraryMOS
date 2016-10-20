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
    public static final String BORROWINFO="borrow_info";
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
        db.execSQL("create table " + USERINFO + "( _id integer primary key AUTOINCREMENT,number varchar(30) ,password varchar(30),name varchar(30))");
        db.execSQL("create table " + ADMININFO + "( number varchar(30) primary key,password varchar(30))");
        db.execSQL("create table " + BOOKINFO + "( _id integer AUTOINCREMENT,bookname varchar(30),number integer(10),isbn integer(10) primary key,author varchar(30),press varchar(30),pressyear varchar(30),category varchar(30),summary varchar(30),photo blob)");
        db.execSQL("create table " + BORROWINFO + "( _id integer primary key AUTOINCREMENT,number varchar(30) REFERENCES user_info(_id),isbn integer(10) REFERENCES book_info(isbn),borrowtime varchar(30)) ");
        initAdmin(db);
        initBook(db);
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

    /*
    * 初始化图书信息
    * */
    private void initBook(SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put("bookname","独立日");
        value.put("number","3");
        value.put("isbn","9787807681625");
        value.put("author","魏小河 / 木卫二 / 陈宇慧@田螺姑娘 ");
        value.put("press","生活•读书•新知三联书店 生活书店出版有限公司");
        value.put("pressyear","2016-9-1");
        value.put("category","散文");
        value.put("summary","通哥没看过");
        db.insert(BOOKINFO,null,value);
        Log.i(TAG, "初始化图书数据成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
