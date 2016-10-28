package com.rjxy.librarymos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

/**
 * Created by lovec on 2016/9/19.
 */
public class PrefUtils {
    
    public static final String NUMBER = "number";
    public  static  final  String ISLOGIN = "isLogin";
    
    public static boolean getBoolen(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void setBoolen(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static byte[] bmpToByteArray(Bitmap photo){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            photo.compress(Bitmap.CompressFormat.PNG,100,bos);
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    public static void getImageFromAlbum(Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent,0);
    }
}
