package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.dao.UserDatabaseDao;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_returnLogin;
    private EditText et_usernumber;
    private EditText et_userpassword;
    private EditText et_username;
    private Button btn_register;
    private String usernumber;
    private String username;
    private String userpassword;
    //private DatabaseHelper databaseHelper;
   // private SQLiteDatabase db;
    //private static final String TAG = "RegisterActivity";
    private boolean isHave;
    private NestedScrollView activity_login;

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

        et_usernumber = (EditText) findViewById(R.id.et_usernumber);
        et_userpassword = (EditText) findViewById(R.id.et_userpassword);
        et_username = (EditText) findViewById(R.id.et_username);
        btn_register = (Button) findViewById(R.id.btn_register);
        activity_login = (NestedScrollView) findViewById(R.id.activity_login);
        //设置注册按钮点击事件
        btn_register.setOnClickListener(this);
    }

    //注册的逻辑
    private void registerLogic() {

        username = et_username.getText().toString().trim();
        usernumber = et_usernumber.getText().toString().trim();
        userpassword = et_userpassword.getText().toString().trim();
        if (!usernumber.equals("Admin")) {
            isHave = UserDatabaseDao.isUserExists(usernumber, getApplicationContext());
            if (!isHave) {
                if (!username.equals("") && !username.equals("") && !userpassword.equals("")) {
                    UserDatabaseDao.register2(usernumber, userpassword, username, getApplicationContext());
                    Snackbar.make(activity_login, "注册成功", Snackbar.LENGTH_LONG)
                            .setAction("返回登陆", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    enterLogin();
                                }
                            })
                            .show();
                } else {
                    Snackbar.make(activity_login, "以上三个为必填项", Snackbar.LENGTH_LONG).show();
                }
            } else {
                //存在
                Snackbar.make(activity_login, "用户名已经被注册", Snackbar.LENGTH_LONG).show();
                return;
            }
        } else {
            Snackbar.make(activity_login, "用管理员账号注册？异想天开~", Snackbar.LENGTH_LONG).show();
        }
    }

    //进入登录页面
    private void enterLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void onClick(View v) {
        registerLogic();
    }
}
