package com.rjxy.librarymos.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.text.NoCopySpan;

/**
 * Created by lovec on 2016/9/18.
 */
public class LibraryMOSApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = Process.myTid();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}
