package com.rjxy.librarymos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.HistoryBean;
import com.rjxy.librarymos.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by llt on 2016/12/3.
 */

public class HistoryDatabaseDao {
    private static DatabaseHelper databaseHelper;
    private static final String TAG = "HistoryDatabaseDao";

    //添加历史记录
    public static boolean addHistory(Context context, HistoryBean historyBean){
        databaseHelper = new DatabaseHelper(context,null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("isbn", historyBean.BookIsbn);
        values.put("username", historyBean.UserName);
        values.put("time", historyBean.Time);
        long insert = db.insert(DatabaseHelper.HISTORYINFO, null, values);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    //根据用户名查找历史记录
    public static ArrayList<HistoryBean> getHistoryByUsername(Context context, String username){
        databaseHelper = new DatabaseHelper(context,null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.HISTORYINFO + " where username = ? order by time desc";

        Cursor cursor = db.rawQuery(sql, new String[]{username});
        ArrayList<HistoryBean> historyBeens = new ArrayList<>();

        while (cursor.moveToNext()) {
            HistoryBean history = new HistoryBean();
            String isbn = cursor.getString(cursor.getColumnIndex("isbn"));
            String time = cursor.getString(cursor.getColumnIndex("time"));

            history.BookIsbn = isbn;
            history.Time = time;

            historyBeens.add(history);
        }
        cursor.close();
        db.close();
        return historyBeens;
    }

    //修改历史记录
    public  static  boolean updateHistory(Context context,String bookisbn,String time){
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        db.execSQL("update " + DatabaseHelper.HISTORYINFO + " set time = '" + time + "' where isbn='" + bookisbn + "'");
        return  true;
    }

    //判断记录是否存在
    public static  boolean checkHistory(Context context, HistoryBean historyBean){
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.HISTORYINFO + " where isbn = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{historyBean.BookIsbn});
        HistoryBean historyBean1 = new HistoryBean();

        if (cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));

            historyBean1.UserName = username;
        }
        if ( historyBean.UserName.equals(historyBean1.UserName)) {
            Log.i(TAG, "已经存在历史记录，请修改时间");
            cursor.close();
            db.close();
            return true;
        }
        Log.i(TAG, "无数据，请添加历史记录");
        cursor.close();
        db.close();
        return false;
    }
}
