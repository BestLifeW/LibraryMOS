package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/31.
 */

public class BookManagerAdapter extends SwipeMenuAdapter<BookManagerAdapter.BookMangerHolder> {


    private ArrayList<BookBean> mList;
    private Context mContext;

    public BookManagerAdapter(Context context, ArrayList<BookBean> list) {
        mList = new ArrayList<>();
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_item,
                parent, false);
        return view;
    }

    @Override
    public BookManagerAdapter.BookMangerHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        BookManagerAdapter.BookMangerHolder holder = new BookManagerAdapter.BookMangerHolder(realContentView);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookManagerAdapter.BookMangerHolder holder, int position) {
        holder.book_title.setText(mList.get(position).bookname);
    }

    @Override
    public int getItemCount() {
            return mList.size()!=0?mList.size():0;
    }

    public class BookMangerHolder extends RecyclerView.ViewHolder {

        TextView book_title;

        public BookMangerHolder(View itemView) {
            super(itemView);
            book_title = (TextView) itemView.findViewById(R.id.tv_bookitem_title);

        }
    }
}
