package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.UserDatabaseDao;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_bookmanage;
    private RelativeLayout rl_userorders;
    private RelativeLayout rl_allusers;
    private RelativeLayout rl_admin_manager;
    private RelativeLayout rl_exit;
    private ArrayList<UserBean> allUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }

    private void init() {
        initData();
        initView();
        intEvent();
    }

    /*初始化数据*/
    private void initData() {
        allUserInfo = UserDatabaseDao.getAllUserInfo(getApplicationContext());
    }

    private void initView() {
        rl_bookmanage = (RelativeLayout) findViewById(R.id.rl_bookmanage);
        rl_userorders = (RelativeLayout) findViewById(R.id.rl_userorders);
        rl_allusers = (RelativeLayout) findViewById(R.id.rl_allusers);
        rl_admin_manager = (RelativeLayout) findViewById(R.id.rl_admin_manager);
        rl_exit = (RelativeLayout) findViewById(R.id.rl_exit);
    }

    private void intEvent() {
        rl_bookmanage.setOnClickListener(this);
        rl_userorders.setOnClickListener(this);
        rl_allusers.setOnClickListener(this);
        rl_admin_manager.setOnClickListener(this);
        rl_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_bookmanage:
                enterBooksManager();
                break;
            case R.id.rl_userorders:
                enterUsersOrder();
                break;
            case R.id.rl_allusers:
                enterUsers();
                break;
            case R.id.rl_admin_manager:
                enterMAP();
                break;
            case R.id.rl_exit:
                enterLogin();
                break;
            default:
                break;
        }
    }

    //进入图书管理界面
    public void enterBooksManager() {
        Intent intent = new Intent(getApplicationContext(), BooksManagerActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //进入用户订单管理界面
    public void enterUsersOrder() {
        Intent intent = new Intent(getApplicationContext(), Order.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    //进入用户管理界面
    public void enterUsers() {
        if (allUserInfo.size()!=0) {
            Intent intent = new Intent(getApplicationContext(), UsersManager.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } else {
            Toast.makeText(this, "抱歉，还没有用户注册", Toast.LENGTH_SHORT).show();
        }
    }

    //进入管理员密码修改界面
    public void enterMAP() {
        Intent intent = new Intent(getApplicationContext(), ModifyAdminPassword.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    //退回登录界面
    public void enterLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
