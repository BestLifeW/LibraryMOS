package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rjxy.librarymos.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_bookmanage;
    private RelativeLayout rl_userorders;
    private RelativeLayout rl_allusers;
    private RelativeLayout rl_admin_manager;
    private RelativeLayout rl_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }

    private void init() {
        initView();
        intEvent();
    }
    private void initView(){
        rl_bookmanage= (RelativeLayout) findViewById(R.id.rl_bookmanage);
        rl_userorders= (RelativeLayout) findViewById(R.id.rl_userorders);
        rl_allusers= (RelativeLayout) findViewById(R.id.rl_allusers);
        rl_admin_manager= (RelativeLayout) findViewById(R.id.rl_admin_manager);
        rl_exit= (RelativeLayout) findViewById(R.id.rl_exit);
    }
    private void intEvent(){
        rl_bookmanage.setOnClickListener(this);
        rl_userorders.setOnClickListener(this);
        rl_allusers.setOnClickListener(this);
        rl_admin_manager.setOnClickListener(this);
        rl_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_bookmanage:
                enterBooksManager();
                Toast.makeText(AdminActivity.this, "进入图书管理界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_userorders:
                Toast.makeText(AdminActivity.this, "进入用户订单界面", Toast.LENGTH_SHORT).show();
                enterUsersOrder();
                break;
            case R.id.rl_allusers:
                enterUsers();
                Toast.makeText(AdminActivity.this, "进入用户表界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_admin_manager:
                enterMAP();
                Toast.makeText(AdminActivity.this, "进入管理员密码修改界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_exit:
                enterLogin();
                Toast.makeText(AdminActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    //进入图书管理界面
    public void enterBooksManager() {
        Intent intent = new Intent(getApplicationContext(), BooksManager.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
    //进入用户订单管理界面
    public void enterUsersOrder() {
        Intent intent = new Intent(getApplicationContext(), UsersOrdersManager.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
    //进入用户管理界面
    public void enterUsers() {
        Intent intent = new Intent(getApplicationContext(), UsersManager.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

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
