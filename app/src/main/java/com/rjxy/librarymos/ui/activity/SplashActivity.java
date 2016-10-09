package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private boolean isLogin;
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLogin = PrefUtils.getBoolen(getApplicationContext(), PrefUtils.ISLOGIN, false);
        setContentView(R.layout.activity_splash);
        enterOtherActivity();
    }

    /*
    * 进入主页的逻辑
    * */
    private void enterOtherActivity() {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                //还未判断是否已经登录过,到时候加一个游客登录
                if (isLogin) {
                    //已经登录 进入主页
                    enterHome();
                } else {
                    //还未注册登录 进入注册登录界面
                    Log.i(TAG, "run: 将要进入注册登录界面");
                    enterLogin();
                }
            }
        };
        timer.schedule(task, 2500);
    }


    //进入主页
    private void enterHome() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    //进入登录注册界面
    private void enterLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
