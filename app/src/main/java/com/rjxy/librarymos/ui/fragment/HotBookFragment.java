package com.rjxy.librarymos.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjxy.librarymos.utils.UIUtils;

/**
 * Created by lovec on 2016/9/22.
 */
public class HotBookFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("热门图书");
        //textView.setTextSize(UIUtils.dip2px(18));
        return textView;
    }

    public static HotBookFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HotBookFragment fragment = new HotBookFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
