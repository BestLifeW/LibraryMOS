package com.rjxy.librarymos.ui.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.C_all_ManagerAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;

import java.util.ArrayList;

public class C_all extends AppCompatActivity {


    private ArrayList<BookBean> bookInfo;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_all);
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
        SpacesItemDecoration decoration = new SpacesItemDecoration(20);
        recyclerView.addItemDecoration(decoration);


    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }


}
