package com.rjxy.librarymos.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lovec on 2016/9/22.
 */

public class DatabaseDao {

    private static DatabaseHelper databaseHelper;

    //注册时，检查用户名是否已经被使用
    public static boolean isUserExists(String user, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.TABLE_NAME + " where number=?";
        Cursor cursor = db.rawQuery(sql, new String[]{user});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //检查用户名 登录时 是否正确
    public static boolean checkLogin(String number, String password, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.TABLE_NAME + " where number =? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{number, password});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        return false;
    }

    //插入注册信息
    public static boolean register(String number, String password, String name, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + "(_id,number, password,name) values(?,?,?,?)", new String[]{null, number, password, name});
        return true;
    }
}
