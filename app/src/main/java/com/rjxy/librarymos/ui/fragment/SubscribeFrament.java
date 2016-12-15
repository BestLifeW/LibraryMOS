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
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/9/22.
 * 订阅模块
 */
public class SubscribeFrament extends Fragment {

    private String userNumber;
    private View view;
    private SwipeMenuRecyclerView rlv_reserve;
    private ArrayList<BookBean> resverBooklist;
    private SwipeRefreshLayout srl_reserve;
    private ArrayList<ReserveBean> reserveinfo;
    private ResverAdapter resverAdapter;
    private static final String TAG = "SubscribeFrament";

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
        rlv_reserve = (SwipeMenuRecyclerView) view.findViewById(R.id.rlv_reserve);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv_reserve.setLayoutManager(layoutManager);
        //rlv_reserve.setItemViewSwipeEnabled(true);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        SpacesItemDecoration decoration = new SpacesItemDecoration(20);
        rlv_reserve.addItemDecoration(decoration);
        resverAdapter = new ResverAdapter(getContext(),reserveinfo,resverBooklist);
        rlv_reserve.setAdapter(resverAdapter);
        srl_reserve = (SwipeRefreshLayout) view.findViewById(R.id.srl_reserve);

        srl_reserve.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resverBooklist = BookDatabaseDao.getByResverISBN(getContext(), userNumber);
                        rlv_reserve.setAdapter(new ResverAdapter(getContext(),reserveinfo, resverBooklist));
                        rlv_reserve.refreshDrawableState();
                        srl_reserve.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        /*rlv_reserve.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(int fromPosition, int toPosition) {
                return true;
            }
            @Override
            public void onItemDismiss(int position) {
                resverAdapter.notifyItemRemoved(position);
                int number = Integer.parseInt(reserveinfo.get(position).quantity);  //借的数量
                Log.i(TAG, "onItemDismiss: " + reserveinfo.get(position).quantity);
                int number1 = resverBooklist.get(position).number;//剩余的数量
                int i = number + number1; //退还，然后一共多少本
                Log.i(TAG, "onItemDismiss: " + resverBooklist.get(position).number);
                Boolean aBoolean = ReserveDatabassDao.delReserveByISBNAnd(resverBooklist.get(position).isbn, userNumber, getContext());
                if (aBoolean) {
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    boolean b = BookDatabaseDao.updateBookNumber(i, resverBooklist.get(position).isbn, getContext());
                    if (b) {
                        Log.i(TAG, "删除成功" + "成功返还");
                        //rlv_reserve.setAdapter(new ResverAdapter(getContext(), resverBooklist, reserveinfo));

                        //rlv_reserve.refreshDrawableState();
                        //resverAdapter.notifyDataSetChanged();
                    } else {
                        Log.i(TAG, "删除失败");
                    }
                } else {
                    Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void init() {
        initData();
    }

    private void initData() {
        userNumber = PrefUtils.getString(getActivity(), PrefUtils.NUMBER, "");
        resverBooklist = BookDatabaseDao.getByResverISBN(getContext(), userNumber);
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
