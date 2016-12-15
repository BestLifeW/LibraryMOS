package com.rjxy.librarymos.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.BookManagerAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

public class BooksManagerActivity extends AppCompatActivity {

    private ArrayList<BookBean> bookInfo;
    private Context mContext;
    private FloatingActionButton flab_addbook;
    private BookManagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        mContext = this;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("图书管理");
        }
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
        final SwipeMenuRecyclerView swipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        assert swipeMenuRecyclerView != null;
        //swipeMenuRecyclerView.setItemViewSwipeEnabled(true);
        mAdapter = new BookManagerAdapter(mContext, bookInfo);
        swipeMenuRecyclerView.setAdapter(mAdapter);
        BooksManagerActivity.SpacesItemDecoration decoration = new BooksManagerActivity.SpacesItemDecoration(20);
        swipeMenuRecyclerView.addItemDecoration(decoration);
        swipeMenuRecyclerView.setLayoutManager(linearLayoutManager);
        //初始化按钮
        flab_addbook = (FloatingActionButton) findViewById(R.id.flab_addbook);
        flab_addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加书籍界面
                enterAddBook();
            }
        });
    }

    //添加书籍
    private void enterAddBook() {
        startActivity(new Intent(getApplicationContext(), AddbookActivity.class));
    }

    /*
    * 展示删除提示框
    * */
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        SpacesItemDecoration(int space) {
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
