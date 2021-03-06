package com.rjxy.librarymos.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by lovec on 2016/9/19.
 */
public class PrefUtils {

    public static final String NUMBER = "number";
    public static final String ISLOGIN = "isLogin";

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

    public static byte[] bmpToByteArray(Bitmap photo) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    public static Bitmap byteArrayToBmp(byte[] photo){
        return BitmapFactory.decodeByteArray(photo, 0, photo.length, null);
    }

    //打开相册
    public static void getImageFromAlbum(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, 0);
    }

    //相册获取Bitmapx
    public static Bitmap getBitmap(Uri uri,Activity activity){
        Bitmap bitmap=null;
        try{
            InputStream inputStream = activity.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    // Bitmap转换uri的方法
    /*public static Uri BitmapTOUri(){
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null,null));
    }*/

    // TODO: 2016/11/1 存取ArrayList的方法  
    public static boolean setStringArry(Context context, ArrayList<String> list) {
        SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("Status_size", list.size());

        for (int i = 0; i < list.size(); i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, list.get(i));
        }
        return mEdit1.commit();
    }

    //存储浏览记录
    public static void setToSharedPreference(Context context,String username, String isbn,String bookname,String time){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        HashSet<String> set = new HashSet<>();
        set.add(isbn);
        set.add(bookname);
        set.add(time);
        editor.putStringSet(username,set);

        editor.commit();
    }

    //获取浏览记录
    public static HashSet<String> getFormSharedPreference(Context context,String username){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sp.getStringSet(username,null);
        return set;
    }
    //获取当前时间
    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyy年mm月dd日   HH:mm:ss ");
        Date curDate = new Date();//获取当前时间
        String time = format.format(curDate);
        return  time;
    }
}
