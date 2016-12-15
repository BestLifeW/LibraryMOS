package com.rjxy.librarymos.ui.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.HotBookAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/9/22.
 */
public class HotBookFragment extends Fragment {

    private View view;
    //private int[] imagesID = {R.drawable.s1319265, R.drawable.s1913020, R.drawable.s1934734, R.drawable.s2660498, R.drawable.s3297116};
    private ArrayList<BookBean> bookInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_hotbook, null);
        init();
        return view;
    }

    public void init() {
        initDate();
        initView();
        initEvent();
    }

    private void initDate() {
        //初始化数据
        bookInfo = BookDatabaseDao.getBookInfo(getActivity());

    }

    private void initEvent() {

    }

    private void initView() {

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rlv_content);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setAdapter(new HotBookAdapter(getActivity(), bookInfo));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        SpacesItemDecoration decoration = new SpacesItemDecoration(20);
        mRecyclerView.addItemDecoration(decoration);
    }

    public static HotBookFragment newInstance() {
        Bundle args = new Bundle();
        HotBookFragment fragment = new HotBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*
    *
    * 设置间距
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
