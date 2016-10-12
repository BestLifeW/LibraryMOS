package com.rjxy.librarymos.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rjxy.librarymos.bean.UserBean;

/**
 * Created by lovec on 2016/9/22.
 */

public class DatabaseDao {

    private static DatabaseHelper databaseHelper;

    //注册时，检查用户名是否已经被使用
    public static boolean isUserExists(String user, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.USERINFO + " where NUMBER=?";
        Cursor cursor = db.rawQuery(sql, new String[]{user});
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //检查用户名 登录时是否正确
    public static boolean checkLogin(String number, String password, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.USERINFO + " where NUMBER =? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{number, password});
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }

    //插入注册信息
    public static boolean register(String number, String password, String name, Context context) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        db.execSQL("insert into " + DatabaseHelper.USERINFO + "(_id,NUMBER, password,name) values(?,?,?,?)", new String[]{null, number, password, name});
        return true;
    }

    //根据用户名查询用户信息
    public static UserBean getUserInfo(String number, Context context) {

        UserBean userBean = new UserBean();
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.USERINFO + " where NUMBER = ?";

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
    public static boolean alertUserInfo(String password,String name,String number,Context context){
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        if (name.equals("")){
            //姓名为空 那么 只改密码
            db.execSQL("update " + DatabaseHelper.USERINFO + " set password = "+ password + " where NUMBER='"+number+"'");
        }else {
            //姓名不为空 一起改
            db.execSQL("update " + DatabaseHelper.USERINFO + " set password = '"+ password+"'" + ",name = '"+ name +"'" + " where NUMBER='"+number+"'");
        }
        return true;
    }
}
