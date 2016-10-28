package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class ResverAdapter extends RecyclerView.Adapter<ResverAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BookBean> mlist;

    public ResverAdapter(Context context, ArrayList<BookBean> bookList) {
        this.context = context;
        mlist = new ArrayList<>();
        mlist = bookList;
        initDate();
    }

    /*
    * 初始化数据
    * */
    private void initDate() {
        String UserNumber = PrefUtils.getString(context, PrefUtils.NUMBER, "");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
