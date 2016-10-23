package com.rjxy.librarymos.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.utils.PrefUtils;

/**
 * Created by lovec on 2016/9/22.
 */
public class SubscribeFrament extends Fragment {

    private String userNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        TextView textView = new TextView(getActivity());
        TextView textView1 = new TextView(getActivity());
        textView.setText("抱歉，您还为登陆");
        textView1.setText("你已经登陆了");
        View view = View.inflate(getActivity(), R.layout.book_item, null);
        if (userNumber.equals("")){
            return textView;
        }
        return view;
    }

    private void init() {
        initData();
    }

    private void initData() {
        userNumber = PrefUtils.getString(getActivity(), PrefUtils.NUMBER, "");
    }

    public static SubscribeFrament newInstance() {
        
        Bundle args = new Bundle();
        
        SubscribeFrament fragment = new SubscribeFrament();
        fragment.setArguments(args);
        return fragment;
    }
}
