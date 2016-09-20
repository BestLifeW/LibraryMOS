package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rjxy.librarymos.R;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_register;
    private Button btn_signup;
    private EditText et_usernumber;
    private EditText et_userpassword;
    private TextView tv_taste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }



    
    /*
    * 初始化布局控件
    * */
    private void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        et_usernumber = (EditText) findViewById(R.id.et_usernumber);
        et_userpassword = (EditText) findViewById(R.id.et_userpassword);
        tv_taste = (TextView) findViewById(R.id.tv_taste);


        //游客体验入口
        tv_taste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterHome();
            }
        });

        //注册入口
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterRegister();
            }
        });

    }



    //进入主页
    private void enterHome() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //进入注册页面
    private void enterRegister(){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
}
