package com.rjxy.librarymos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class ReserveDatabassDao {

    private static DatabaseHelper databaseHelper;
    private static final String TAG = "ReserveDatabassDao";


    //管理员所用
    public static ArrayList<ReserveBean> getAllReserveInfo(Context context) {
        ArrayList<ReserveBean> reserveList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.RESERVEINFO;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ReserveBean reserveinfo = new ReserveBean();
                String usernumber = cursor.getString(cursor.getColumnIndex("number"));  //获取用户number
                String isbn = cursor.getString(cursor.getColumnIndex("isbn"));  //获取isbn编码
                String reservetime = cursor.getString(cursor.getColumnIndex("reservetime"));  //获取isbn编码
                String submitime = cursor.getString(cursor.getColumnIndex("submitime"));  //获取isbn编码
                String quantity = cursor.getString(cursor.getColumnIndex("quantity"));
                reserveinfo.UserNumber = usernumber;
                reserveinfo.BookIsbn = isbn;
                reserveinfo.ReserveTime = reservetime;
                reserveinfo.SubmitTime = submitime;
                reserveinfo.quantity = quantity;
                reserveList.add(reserveinfo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reserveList;
    }

    public static boolean setReserveInfo(Context context, String usernumber, String isbn, String SubmitTime, String ReserveTime, String Quantity) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", usernumber);
        values.put("isbn", isbn);
        values.put("reservetime", ReserveTime);
        values.put("submitime", SubmitTime);
        values.put("quantity", Quantity);
        long l = db.insert(DatabaseHelper.RESERVEINFO, null, values);
        if (l > 0) {
            return true;
        }
        db.close();
        return false;

    }

    public static void updateReserveQuantity(Context context, int quantity, String isbn) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = "update " + DatabaseHelper.BOOKINFO + " set number=number-" + quantity + " where isbn=" + "\"" + isbn + "\"";
        db.execSQL(sql);
    }


}
