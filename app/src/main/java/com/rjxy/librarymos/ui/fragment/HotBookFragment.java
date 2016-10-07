package com.rjxy.librarymos.ui.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.adapter.RecyclerViewAdapter;
import com.rjxy.librarymos.utils.UIUtils;

/**
 * Created by lovec on 2016/9/22.
 */
public class HotBookFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private int[] imagesID = {R.drawable.s1319265,R.drawable.s1913020,R.drawable.s1934734,R.drawable.s2660498,R.drawable.s3297116};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_hotbook, null);
        init();
        return view;
    }

    /**
     *
     */
    public void init(){
        initView();
        initEvent();
    }

    private void initEvent() {

    }

    private void initView() {

        recyclerView = (RecyclerView) view.findViewById(R.id.rlv_content);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(),imagesID));
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
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

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}
