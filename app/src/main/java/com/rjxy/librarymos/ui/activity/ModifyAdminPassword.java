package com.rjxy.librarymos.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.AdminBean;
import com.rjxy.librarymos.dao.AdminDatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;


public class ModifyAdminPassword extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_adminsetpassword;
    private View view;
    private EditText et_oldPassword;
    private EditText et_newPassword;
    private String AdminNumber;
    private AdminBean adminiInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_admin_password);
        AdminNumber = PrefUtils.getString(getApplicationContext(), PrefUtils.NUMBER, "");
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initData() {


    }

    private void initView() {
        rl_adminsetpassword = (RelativeLayout) findViewById(R.id.rl_adminsetpassword);
        rl_adminsetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showSetPasswordDialog();

    }

    private void showSetPasswordDialog() {
        view = View.inflate(getApplicationContext(), R.layout.adminsetpassword_info, null);
        et_oldPassword = (EditText) view.findViewById(R.id.et_oldPassword);
        et_newPassword = (EditText) view.findViewById(R.id.et_newPassword);

        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyAdminPassword.this);

        builder.setTitle("管理员密码修改");
        builder.setView(view);
        builder.setPositiveButton("确认修改", new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int which) {
                adminiInfo = AdminDatabaseDao.getAdminiInfo(AdminNumber, getApplicationContext());
                String oldPassword = et_oldPassword.getText().toString().trim();
                String newPassword = et_newPassword.getText().toString().trim();
                if (!oldPassword.equals(adminiInfo.password)) {
                    Toast.makeText(ModifyAdminPassword.this, "原密码不符合", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //UserDatabaseDao.setUserInfo(newPassword, newName, number, getApplicationContext());
                    boolean isOK = AdminDatabaseDao.setAdminPassword(newPassword, getApplicationContext());
                    if (isOK){
                        Toast.makeText(ModifyAdminPassword.this, "修改成功,请重新登录", Toast.LENGTH_SHORT).show();
                        PrefUtils.setString(getApplicationContext(), PrefUtils.NUMBER, "");//将保存的账户设置为空
                        enterLogin();
                        ModifyAdminPassword.this.finish();
                    }else {
                        Toast.makeText(ModifyAdminPassword.this, "修改失败，未知问题", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();



    }


        //退回登录界面
        public void enterLogin() {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }


}
