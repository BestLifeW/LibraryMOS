package com.rjxy.librarymos.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.HistoryAdapter;
import com.rjxy.librarymos.bean.HistoryBean;
import com.rjxy.librarymos.dao.HistoryDatabaseDao;
import com.rjxy.librarymos.ui.fragment.HotBookFragment;
import com.rjxy.librarymos.utils.PrefUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by llt on 2016/12/3.
 */

public class HistoryActivity extends AppCompatActivity {
    private SwipeMenuRecyclerView recycler_view;
    private String username;
    private ArrayList<HistoryBean> historyBeans;
    private static final String TAG = "HistoryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historys);
        initData();

    }

    private void initData(){
        username = PrefUtils.getString(this,PrefUtils.NUMBER,"");
        historyBeans = HistoryDatabaseDao.getHistoryByUsername(this,username);
        Log.i(TAG, "几条数据："+historyBeans.size());
        initView();
    }

    private void initView(){
        recycler_view = (SwipeMenuRecyclerView) findViewById(R.id.history_recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view.setAdapter(new HistoryAdapter(historyBeans,this));
        //recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.setHasFixedSize(true);
    }


}
