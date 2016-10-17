package com.rjxy.librarymos.ui.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.UserDatabaseDao;
import com.rjxy.librarymos.ui.activity.AccountManager;
import com.rjxy.librarymos.ui.activity.LoginActivity;
import com.rjxy.librarymos.utils.PrefUtils;

/**
 * Created by lovec on 2016/9/22.
 */
public class AboutMeFragment extends Fragment implements View.OnLongClickListener, View.OnClickListener {

    private View view;
    private TextView tv_username;
    private String number;
    private RelativeLayout rl_account_manager;
    private RelativeLayout rl_history;
    private RelativeLayout rl_setting;
    private UserBean userInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_aboutme, null);
        //获取保存的用户名
        number = PrefUtils.getString(getActivity(), PrefUtils.NUMBER, "");
        init();
        return view;
    }

    /*
    * 初始化
    * */
    private void init() {
        initView();
        initDate();
        intEvent();
    }

    /*
    *
    * */
    private void intEvent() {
        rl_account_manager.setOnClickListener(this);
        rl_history.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
    }

    /*
      * 初始化控件
      * */
    private void initView() {
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        rl_account_manager = (RelativeLayout) view.findViewById(R.id.rl_account_manager);
        rl_history = (RelativeLayout) view.findViewById(R.id.rl_history);
        rl_setting = (RelativeLayout)view.findViewById(R.id.rl_setting);
    }

    /*
    * 初始化数据
    * */
    private void initDate() {
        userInfo = UserDatabaseDao.getUserInfo(number, getActivity());
        if (number.equals("") || userInfo == null) {
            tv_username.setText("游客");
            tv_username.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ShowDialog("返回登陆", true);
                    return false;
                }
            });
        } else {
            tv_username.setText(userInfo.name);
            tv_username.setOnLongClickListener(this);
        }

    }

    //Fragment的实例化
    public static AboutMeFragment newInstance() {
        Bundle args = new Bundle();
        AboutMeFragment fragment = new AboutMeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /*
    * 文字点击事件  弹出对话框 询问是否注销
    * */
    @Override
    public boolean onLongClick(View v) {
        ShowDialog("注销用户？"+userInfo.name, false);
        return true;
    }

    /**
     *
     * @param alert  根据用户不同 提示不同
     * @param isUser  判断是否是用户
     */
    private void ShowDialog(String alert, final boolean isUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(alert);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isUser) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                } else {
                    PrefUtils.setBoolen(getActivity(), PrefUtils.ISLOGIN, false);
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


    /**
     * 按钮点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_account_manager:
                if (number.equals("")){
                    Snackbar.make(getView(),"抱歉，您还未登录",Snackbar.LENGTH_LONG).show();
                }else {

                enterManager();
                }
                break;
            case R.id.rl_history:
                enterHistory();
                break;
            case R.id.rl_setting:
                enterSetting();
                break;
            default:
                break;
        }
    }
    /*
    * 设置界面
    * */
    private void enterSetting() {
        Snackbar.make(getView(),"进入设置界面",Snackbar.LENGTH_LONG).show();

    }
    /*
    * 进入历史纪录
    * */
    private void enterHistory() {
        Snackbar.make(getView(),"进入历史纪录",Snackbar.LENGTH_LONG).show();
    }
    /*
    * 进入账号管理
    * */
    private void enterManager() {
        Intent intent = new Intent(getActivity(), AccountManager.class);
        intent.putExtra("number",number);
        startActivity(intent);
        Snackbar.make(getView(),"进入账号管理",Snackbar.LENGTH_LONG).show();
    }
}
