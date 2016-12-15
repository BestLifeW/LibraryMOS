package com.rjxy.librarymos.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rjxy.librarymos.OrderAdapter;
import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

public class Order extends AppCompatActivity {
    private SwipeMenuRecyclerView recycler_view;
    private ArrayList<ReserveBean> reserveBeens;
    private static final String TAG = "OrderActivity";
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initData();
    }

    private void initData() {
        reserveBeens = ReserveDatabassDao.getAllReserveInfo(this);
        Log.i(TAG, "几条数据：" + reserveBeens.size());
        initView();
    }

    private void initView() {
        recycler_view = (SwipeMenuRecyclerView) findViewById(R.id.order_recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new OrderAdapter(this, reserveBeens);
        recycler_view.setAdapter(adapter);
        Order.SpacesItemDecoration decoration = new Order.SpacesItemDecoration(20);
        recycler_view.addItemDecoration(decoration);
    }


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
