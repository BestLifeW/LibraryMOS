package com.rjxy.librarymos.ui.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.ResverAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/9/22.
 */
public class SubscribeFrament extends Fragment {

    private String userNumber;
    private View view;
    private RecyclerView rlv_reserve;
    private ArrayList<BookBean> bookList;
    private SwipeRefreshLayout srl_reserve;
    private ArrayList<ReserveBean> reserveinfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        ImageView iamgeView = new ImageView(getActivity());
        iamgeView.setMaxWidth(10);
        iamgeView.setImageResource(R.drawable.error);

        view = View.inflate(getActivity(), R.layout.fragment_reserve, null);
        if (userNumber.equals("")) {
            return iamgeView;
        }
        initView();
        return view;
    }

    /*
    * 初始化View
    * */
    private void initView() {
        rlv_reserve = (RecyclerView) view.findViewById(R.id.rlv_reserve);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv_reserve.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        SpacesItemDecoration decoration = new SpacesItemDecoration(20);
        rlv_reserve.addItemDecoration(decoration);
        rlv_reserve.setAdapter(new ResverAdapter(getContext(), reserveinfo));
        srl_reserve = (SwipeRefreshLayout) view.findViewById(R.id.srl_reserve);
        
        srl_reserve.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        bookList = BookDatabaseDao.getByResverISBN(getContext(), userNumber);
                        //rlv_reserve.setAdapter(new ResverAdapter(getContext(), bookList, reserveinfo));
                        rlv_reserve.refreshDrawableState();
                        srl_reserve.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void init() {
        initData();
    }

    private void initData() {
        userNumber = PrefUtils.getString(getActivity(), PrefUtils.NUMBER, "");
        bookList = BookDatabaseDao.getByResverISBN(getContext(), userNumber);
        reserveinfo = ReserveDatabassDao.getReserveinfo(getContext(), userNumber);
    }

    public static SubscribeFrament newInstance() {
        Bundle args = new Bundle();
        SubscribeFrament fragment = new SubscribeFrament();
        fragment.setArguments(args);
        return fragment;
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
