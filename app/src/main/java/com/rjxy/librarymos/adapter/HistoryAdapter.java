package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.HistoryBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by llt on 2016/12/3.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    private Context context;
    private ArrayList<HistoryBean> historyBeans;
    private static final String TAG = "HistoryAdapter";

    public HistoryAdapter(ArrayList<HistoryBean> historyBeans, Context context) {

        this.context = context;
        this.historyBeans = historyBeans;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoryHolder holder = new HistoryHolder(LayoutInflater.from(
                context).inflate(R.layout.history_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        String isbn = historyBeans.get(position).BookIsbn;
        BookBean bookBean = BookDatabaseDao.getBookInfoByISBN(context,isbn);
        byte[] photo = bookBean.photo;
        Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);

        Log.i(TAG, "书名：" + bookBean.bookname);
        holder.iv_historyitem_im.setImageBitmap(bitmap);
        holder.tv_historyitem_title.setText(bookBean.bookname);
        holder.tv_historyitem_dec.setText(bookBean.sunmmary);
    }

    @Override
    public int getItemCount() {
        return historyBeans.size();
    }

    class HistoryHolder extends RecyclerView.ViewHolder {

        ImageView iv_historyitem_im;
        TextView tv_historyitem_title;
        TextView tv_historyitem_time;
        TextView tv_historyitem_dec;

        public HistoryHolder(View itemView) {
            super(itemView);
            iv_historyitem_im = (ImageView) itemView.findViewById(R.id.iv_historyitem_im);
            tv_historyitem_title = (TextView) itemView.findViewById(R.id.tv_historyitem_title);
            tv_historyitem_dec = (TextView) itemView.findViewById(R.id.tv_historyitem_dec);
        }
    }
}
