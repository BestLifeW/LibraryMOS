package com.rjxy.librarymos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.database.DatabaseHelper;

/**
 * Created by lovec on 2016/9/22.
 */

public class UserDatabaseDao {

    private static DatabaseHelper databaseHelper;
    private static final String TAG = "databaseDao";
    private static String password="";
    private static String oknumber;


    //注册时，检查用户名是否已经被使用
    public static boolean isUserExists(String user, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.USERINFO + " where number=?";
        Log.i(TAG, "检查用户名是否被使用,传入数据" + user);
        Cursor cursor = db.rawQuery(sql, new String[]{user});
        if (cursor.getCount() > 0) {
            Log.i(TAG, "已经被使用");
            cursor.close();
            db.close();
            return true;

        }
        Log.i(TAG, "未被使用");
        cursor.close();
        return false;
    }

    //检查用户名 登录时是否正确
    public static boolean checkLogin(String number, String password, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.USERINFO + " where number =? and password=?";
        Log.i(TAG, "检查登陆密码是否正确,传入数据:" + number + "密码是:" + password);
        Cursor cursor = db.rawQuery(sql, new String[]{number, password});
        if (cursor.getCount() > 0) {
            Log.i(TAG, "有数据，密码正确");
            cursor.close();
            db.close();
            return true;
        }
        Log.i(TAG, "无数据，密码错误");
        return false;
    }

    public static boolean isHaveNumber(String number ,Context context){
        databaseHelper = new DatabaseHelper(context,null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from "+DatabaseHelper.USERINFO + " where number = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{number});
        if (cursor.getCount()>0){
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }

    //返回用户名的密码
    public static String getUserPassword(String number, Context context) {

        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + databaseHelper.USERINFO + " where number = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{number});
        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();
            db.close();
            Log.i(TAG, "得到的密码是:"+password);
        }
        return password;
    }

    //插入注册信息
    public static boolean register(String number, String password, String name, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        db.execSQL("insert into " + DatabaseHelper.USERINFO + "(_id,number, password,name) values(?,?,?,?)", new String[]{null, number, password, name});
        return true;
    }

    //新方法
    public static boolean register2(String number,String password,String name,Context context){
        databaseHelper=new DatabaseHelper(context,null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("number",number);
        value.put("password",password);
        value.put("name",name);
        long insert = db.insert(DatabaseHelper.USERINFO, null, value);
        if (insert>0){
            return true;
        }
        return false;
    }

    //根据用户名查询用户信息
    public static UserBean getUserInfo(String number, Context context) {

        UserBean userBean = new UserBean();
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.USERINFO + " where number = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{number});
        //因为一个用户对应一个数据，所以我直接写，不用while循环了
        if (cursor.moveToFirst()) {
            String Number = cursor.getString(1);  //获取账户
            String password = cursor.getString(2);//获取密码
            String name = cursor.getString(3);  //获取名字
            userBean.name = name;
            userBean.password = password;
            userBean.number = Number;
        }
        return userBean;
    }

    //修改密码啥的
    public static boolean setUserInfo(String password, String name, String number, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        if (name.equals("")) {
            //姓名为空 那么 只改密码
            db.execSQL("update " + DatabaseHelper.USERINFO + " set password = " + password + " where number='" + number + "'");
        } else {
            //姓名不为空 一起改
            db.execSQL("update " + DatabaseHelper.USERINFO + " set password = '" + password + "'" + ",name = '" + name + "'" + " where number='" + number + "'");
        }
        return true;
    }
}
