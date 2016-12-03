package com.rjxy.librarymos.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.view.WindowManager;

import com.rjxy.librarymos.global.LibraryMOSApplication;

import java.io.ByteArrayOutputStream;

/**
 * 工具类
 * Created by lovec on 2016/9/18.
 */
public class UIUtils {


    // 返回Context
    public static Context getContext() {
        return LibraryMOSApplication.getContext();
    }

    //返回handler
    public static Handler getHandler() {
        return LibraryMOSApplication.getHandler();
    }

    //获取主线程ID
    public static int getMainThread() {
        return LibraryMOSApplication.getMainThreadId();
    }

    //获取字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    //获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    //获取图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    //获取颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }


    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    //打气筒填充布局
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }


    //判断是否运行在主线程中
    public static boolean isRunOnUIThread() {
        //获取当前线程id
        int myTid = Process.myTid();
        if (myTid == getMainThread()) {
            return true;
        }
        return false;
    }

    //运行在主线程的方法
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            r.run();
        } else {
            //如果是子线程，借助handler 让其运行在主线程
            getHandler().post(r);
        }
    }
    //把图片转换成字节码
    public static byte[] img(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        return os.toByteArray();
    }

    //设置屏幕亮度
    public static void setBrightness(Activity activity , float brightnessValue)
    {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        if(brightnessValue > 1.0f)
        {
            lp.screenBrightness = 1.0f;
        }
        else if(brightnessValue <= 0.0f)
        {
            lp.screenBrightness = 0.0f;
        }
        else
        {
            lp.screenBrightness = brightnessValue;
        }
        activity.getWindow().setAttributes(lp);
    }
}
