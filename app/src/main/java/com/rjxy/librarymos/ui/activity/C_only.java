package com.rjxy.librarymos.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.C_all_ManagerAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;

import java.util.ArrayList;

public class C_only extends AppCompatActivity {

    private ArrayList<BookBean> bookInfo;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_only);

        mContext = this.getApplicationContext();
        iniData();
        initView();
    }

    /*
初始化数据
* */
    private void iniData() {
        bookInfo = BookDatabaseDao.getBookInfo(getApplicationContext());
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new C_all_ManagerAdapter(mContext, bookInfo));

    }
}
