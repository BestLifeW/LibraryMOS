package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rjxy.librarymos.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_returnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    /*
    * 初始化布局
    * */
    private void initView() {
        tv_returnLogin = (TextView) findViewById(R.id.tv_returnLogin);
        tv_returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterLogin();
            }
        });
    }

    //进入登录页面
    private void enterLogin(){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
}
