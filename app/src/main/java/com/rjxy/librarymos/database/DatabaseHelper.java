package com.rjxy.librarymos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lovec on 2016/9/22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Library_DB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String USERINFO = "user_info";
    public static final String BOOKINFO ="book_info";

    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //测试阶段
        db.execSQL("create table " + USERINFO + "(  _id integer primary key AUTOINCREMENT,NUMBER varchar(30) ,password varchar(30),name varchar(30))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
