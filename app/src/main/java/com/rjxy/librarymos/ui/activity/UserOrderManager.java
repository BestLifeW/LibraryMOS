package com.rjxy.librarymos.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.UserOrderAdapter;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.dao.ReserveDatabassDao;

import java.util.ArrayList;

public class UserOrderManager extends AppCompatActivity {


    private RecyclerView rlv_userorder;

    //用户订单界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        rlv_userorder = (RecyclerView) findViewById(R.id.rlv_userorder);


        rlv_userorder.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //rlv_userorder.setAdapter(new UserOrderAdapter());

        ArrayList<ReserveBean> allReserve = ReserveDatabassDao.getAllReserve(getApplicationContext());
        for (ReserveBean reserveBean : allReserve) {
            Log.i("test", reserveBean.toString());
        }


    }
}
