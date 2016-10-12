package com.rjxy.librarymos.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.database.DatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

public class AccountManager extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_inforevise;
    private String userNumber;
    private View view;
    private UserBean userInfo;
    private String number;
    private EditText et_oldPassword;
    private EditText et_newPassword;
    private EditText et_newName;
    private RelativeLayout rl_about;
    private TextView tv_loginout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);
        number = getIntent().getExtras().getString("number");
        userInfo = DatabaseDao.getUserInfo(number, getApplicationContext());
        //初始化
        init();

    }

    private void init() {
        initView();
        initData();

    }

    private void initView() {
        //密码姓名设置
        rl_inforevise = (RelativeLayout) findViewById(R.id.rl_inforevise);
        rl_about = (RelativeLayout) findViewById(R.id.rl_about);
        tv_loginout = (TextView) findViewById(R.id.tv_loginout);
        rl_inforevise.setOnClickListener(this);
        rl_about.setOnClickListener(this);
        tv_loginout.setOnClickListener(this);

    }

    private void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_inforevise:
                ShowDialog();
                break;
            case R.id.rl_about:
                enterAbout();

            case R.id.tv_loginout:
                loginOut();
            default:
                break;
        }
    }
    /*
    * 退出登录
    * */
    private void loginOut() {
        PrefUtils.setString(getApplicationContext(),PrefUtils.NUMBER,"");
        PrefUtils.setBoolen(getApplicationContext(),PrefUtils.ISLOGIN,false);
        enterLogin();
        finish();
    }
    /*
    * 进入关于界面
    * */
    private void enterAbout() {
        Intent intent  = new Intent(getApplicationContext(),AboutActivity.class);
        startActivity(intent);
    }

    //弹出修改密码对话框
    private void ShowDialog() {


        view = View.inflate(getApplicationContext(), R.layout.alertdialog_info, null);
        et_oldPassword = (EditText) view.findViewById(R.id.et_oldPassword);
        et_newPassword = (EditText) view.findViewById(R.id.et_newPassword);
        et_newName = (EditText) view.findViewById(R.id.et_newName);



        AlertDialog.Builder builder = new AlertDialog.Builder(AccountManager.this);

        builder.setTitle("修改信息:"+userInfo.number);
        builder.setView(view);
        builder.setPositiveButton("确认修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 String oldPassword = et_oldPassword.getText().toString().trim();
                 String newName = et_newName.getText().toString().trim();
                 String newPassword = et_newPassword.getText().toString().trim();
                if (!oldPassword.equals(userInfo.password)){
                    Toast.makeText(AccountManager.this, "原密码不符合", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    DatabaseDao.alertUserInfo(newPassword,newName,number,getApplicationContext());
                    Toast.makeText(AccountManager.this, "修改成功,请重新登录", Toast.LENGTH_SHORT).show();
                    PrefUtils.setString(getApplicationContext(),PrefUtils.NUMBER,"");//将保存的账户设置为空
                    enterLogin();
                    AccountManager.this.finish();
                }
            }
        });
        builder.setNegativeButton("取消",null);
        builder.show();
    }

    private void enterLogin(){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}
