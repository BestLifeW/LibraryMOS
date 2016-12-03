package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class ResverAdapter extends RecyclerView.Adapter<ResverAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BookBean> mlist;
    private ArrayList<ReserveBean> reserList;
    private String userNumber;

    public ResverAdapter(Context context, ArrayList<BookBean> bookList, ArrayList<ReserveBean> reserveinfo) {
        this.context = context;
        mlist = new ArrayList<>();
        this.reserList = new ArrayList<>();
        mlist = bookList;
        this.reserList = reserveinfo;
        initDate();
    }

    /*
    * 初始化数据 nnm,
    * */
    private void initDate() {
        userNumber = PrefUtils.getString(context, PrefUtils.NUMBER, "");

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

        byte[] photo = mlist.get(position).photo;
        Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);
        holder.iv_bookitem_im.setImageBitmap(bitmap);

        holder.tv_bookitem_title.setText(mlist.get(position).bookname);
        holder.tv_bookitem_anthuor.setText("提交时间:" + reserList.get(position).SubmitTime);
        holder.tv_bookitem_dec.setText(mlist.get(position).sunmmary);
        holder.tv_bookitem_resvertime.setText("预约时间:" + reserList.get(position).ReserveTime);

    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_bookitem_im;
        private final TextView tv_bookitem_title;
        private final TextView tv_bookitem_anthuor;
        private final TextView tv_bookitem_dec;
        private final TextView tv_bookitem_resvertime;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_bookitem_im = (ImageView) itemView.findViewById(R.id.iv_bookitem_im);
            tv_bookitem_title = (TextView) itemView.findViewById(R.id.tv_bookitem_title);
            tv_bookitem_anthuor = (TextView) itemView.findViewById(R.id.tv_bookitem_anthuor);
            tv_bookitem_dec = (TextView) itemView.findViewById(R.id.tv_bookitem_dec);
            tv_bookitem_resvertime = (TextView) itemView.findViewById(R.id.tv_bookitem_resvertime);
        }
    }
}
