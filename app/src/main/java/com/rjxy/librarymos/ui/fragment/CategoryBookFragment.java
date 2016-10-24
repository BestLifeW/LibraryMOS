package com.rjxy.librarymos.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjxy.librarymos.R;

/**
 * Created by lovec on 2016/9/22.
 */
public class CategoryBookFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_categorybook, null);


        return view;
    }

    public static CategoryBookFragment newInstance() {

        Bundle args = new Bundle();

        CategoryBookFragment fragment = new CategoryBookFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
