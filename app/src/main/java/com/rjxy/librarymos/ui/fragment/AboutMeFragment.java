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
public class AboutMeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("关于我");
        return textView;
    }

    public static AboutMeFragment newInstance() {

        Bundle args = new Bundle();

        AboutMeFragment fragment = new AboutMeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
