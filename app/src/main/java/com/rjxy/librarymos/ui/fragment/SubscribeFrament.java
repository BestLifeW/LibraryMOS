package com.rjxy.librarymos.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.ResverAdapter;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        TextView textView = new TextView(getActivity());
        TextView textView1 = new TextView(getActivity());
        textView.setText("抱歉，您还为登陆");
        textView1.setText("你已经登陆了");

        view = View.inflate(getActivity(), R.layout.fragment_reserve, null);
        if (userNumber.equals("")){
            return textView;
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

        rlv_reserve.setAdapter(new ResverAdapter(getContext(),bookList));
    }

    private void init() {
        initData();
    }

    private void initData() {
        userNumber = PrefUtils.getString(getActivity(), PrefUtils.NUMBER, "");
        bookList = BookDatabaseDao.getByResverISBN(getContext(), userNumber);
    }

    public static SubscribeFrament newInstance() {
        Bundle args = new Bundle();
        SubscribeFrament fragment = new SubscribeFrament();
        fragment.setArguments(args);
        return fragment;
    }
}
