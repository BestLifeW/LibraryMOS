package com.rjxy.librarymos.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.UserManaAdapter;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.UserDatabaseDao;

import java.util.ArrayList;

public class UsersManager extends AppCompatActivity {

    private RecyclerView rlv_userinfo;
    private static final String TAG = "UsersManager";
    private ArrayList<UserBean> allUserInfo;
    private boolean isHaveUser =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usertable);
        Log.i(TAG, "onCreate: 初始化啦");
        init();

    }

    private void init() {
        initData();
        initView();
    }

    //初始化填充数据
    private void initData() {
        allUserInfo = UserDatabaseDao.getAllUserInfo(getApplicationContext());
        if (allUserInfo!=null){
            isHaveUser = true;
        }
    }


    //初始化布局
    private void initView() {
        rlv_userinfo = (RecyclerView) findViewById(R.id.rlv_userinfo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rlv_userinfo.setLayoutManager(layoutManager);
        rlv_userinfo.setAdapter(new UserManaAdapter(allUserInfo,isHaveUser,getApplicationContext()));

    }

    public boolean getIsHaveUser() {
        return isHaveUser;
    }
}
