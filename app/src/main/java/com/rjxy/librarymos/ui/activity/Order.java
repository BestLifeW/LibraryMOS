package com.rjxy.librarymos.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.rjxy.librarymos.OrderAdapter;
import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.HistoryAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.HistoryBean;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.rjxy.librarymos.dao.UserDatabaseDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

public class Order extends AppCompatActivity {
    private SwipeMenuRecyclerView recycler_view;;
    private ArrayList<ReserveBean> reserveBeens;
    private static final String TAG = "OrderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initData();
    }

    private void initData() {
        reserveBeens = ReserveDatabassDao.getAllReserveInfo(this);
        Log.i(TAG, "几条数据："+reserveBeens.size());
        initView();
    }

    private void initView() {
        recycler_view = (SwipeMenuRecyclerView) findViewById(R.id.order_recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view.setAdapter(new OrderAdapter(this,reserveBeens));
    }
}
