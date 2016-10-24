package com.rjxy.librarymos.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rjxy.librarymos.bean.AdminBean;
import com.rjxy.librarymos.database.DatabaseHelper;

public class AdminDatabaseDao {

    private static DatabaseHelper databaseHelper;
    private static final String TAG = "AdminDatabaseDao";


    public static AdminBean getAdminiInfo(String number, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        AdminBean adminBean = new AdminBean();
        //sqllite自带的方法
        Cursor cursor = db.query(databaseHelper.ADMININFO, null, "number=?", new String[]{number}, null, null, null, null);
        if (cursor.moveToFirst()) {
            Log.i(TAG, "getAdminiInfo: 准备查询");
            String number1 = cursor.getString(cursor.getColumnIndex("number"));
            Log.i(TAG, "getAdminiInfo: 获取到姓名" + number1);
            String password = cursor.getString(cursor.getColumnIndex("password"));
            Log.i(TAG, "getAdminiInfo: 获取到密码" + password);
            adminBean.number = number1;
            adminBean.password = password;
            Log.i(TAG, "getAdminiInfo: 填充完毕");
            Log.i(TAG, "getAdminiInfo: " + adminBean.toString());
        }
        cursor.close();
        db.close();
        return adminBean;
    }

    public static boolean setAdminPassword(String password, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("password", password);
        //返回被影响到的个数，如果大于0 说明修改成功 返回true;
        int update = db.update(DatabaseHelper.ADMININFO, value, "number=\"Admin\"", null);
        if (update > 0) {
            return true;
        }
        db.close();
        return false;
    }//
}
