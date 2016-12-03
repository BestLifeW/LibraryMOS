package com.rjxy.librarymos.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.BookManagerAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

public class BooksManager extends AppCompatActivity {

    private ArrayList<BookBean> bookInfo;
    private Context mContext;
    private FloatingActionButton flab_addbook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
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
        SwipeMenuRecyclerView swipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        // 设置菜单创建器。
        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                showDeleteDialog(adapterPosition);
                Toast.makeText(mContext, adapterPosition + "被电击了"+"这个是什么"+direction, Toast.LENGTH_SHORT).show();

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        swipeMenuRecyclerView.setLayoutManager(linearLayoutManager);
        swipeMenuRecyclerView.setAdapter(new BookManagerAdapter(mContext, bookInfo));

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
        startActivity(new Intent(getApplicationContext(),AddbookActivity.class));
    }

    /*
    * 展示删除提示框
    * */
    private void showDeleteDialog(int adapterPosition) {
        Toast.makeText(mContext, bookInfo.get(adapterPosition).bookname, Toast.LENGTH_SHORT).show();
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplication())
                    //.setBackgroundDrawable(R.drawable.ic_delete_white_24dp)
                    .setImage(R.drawable.ic_delete_white_24dp) // 图标。
                    .setText("删除") // 文字。
                    .setTextColor(Color.parseColor("#3c3c3c")) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(300)
                    .setHeight(100);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.

            // 上面的菜单哪边不要菜单就不要添加。
        }
    };


}
