package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.database.DatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_register;
    private Button btn_signup;
    private EditText et_usernumber;
    private EditText et_userpassword;
    private TextView tv_taste;
    private String usernumber;
    private static final String TAG = "LoginActivity";

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
                PrefUtils.setString(getApplicationContext(),PrefUtils.NUMBER,"");
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


        //管理员登陆 默认账户/密码  admin/admin
        btn_signup.setOnClickListener(new View.OnClickListener() {

            private String password;


            @Override
            public void onClick(View v) {
                usernumber = et_usernumber.getText().toString().trim();
                password = et_userpassword.getText().toString().trim();

                if (usernumber.equals("admin") || usernumber.equals("Admin") && password.equals("admin")) {
                    //登录管理员界面
                    Toast.makeText(LoginActivity.this, "管理员登录啦！", Toast.LENGTH_SHORT).show();
                } else {
                    //判断用户的用户名 密码
                    if (!usernumber.equals("") || !password.equals("")) {
                        boolean isHave = DatabaseDao.checkLogin(usernumber, password, getApplicationContext());
                        if (isHave) {
                            enterHome();
                            PrefUtils.setBoolen(getApplicationContext(), "isLogin", true);
                            PrefUtils.setString(getApplicationContext(),PrefUtils.NUMBER,usernumber);

                        } else {
                            Snackbar.make(getCurrentFocus(), "登录失败，请检查输入", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar.make(getCurrentFocus(), "请完成输入", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                }
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
    public void enterRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("登录成功");
        builder.setMessage("欢迎" + usernumber + "正在加载，请稍候 ");
        builder.show();
    }


}
