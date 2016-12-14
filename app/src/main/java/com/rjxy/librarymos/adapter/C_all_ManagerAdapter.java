package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
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
import com.rjxy.librarymos.dao.HistoryDatabaseDao;
import com.rjxy.librarymos.ui.activity.BookActivity;
import com.rjxy.librarymos.utils.PrefUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;


public class C_all_ManagerAdapter extends SwipeMenuAdapter<C_all_ManagerAdapter.BookMangerHolder> {


    private ArrayList<BookBean> mList;
    private Context mContext;


    public C_all_ManagerAdapter(Context context, ArrayList<BookBean> list) {
        mList = new ArrayList<>();
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.c_all_item,
                parent, false);
        return view;
    }

    @Override
    public C_all_ManagerAdapter.BookMangerHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new BookMangerHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(C_all_ManagerAdapter.BookMangerHolder holder, final int position) {

        byte[] photo=mList.get(position).photo;
        Log.i("图片：","photo："+photo);
        Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);
        Log.i("图片：","bitmap："+bitmap);
        holder.iv_bookitem_im.setImageBitmap(bitmap);

        holder.book_title.setText(mList.get(position).bookname);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BookActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("book_isbn", mList.get(position).isbn);
                Log.i(TAG, "传输过去" + mList.get(position).isbn);
                String username  = PrefUtils.getString(mContext, PrefUtils.NUMBER, "");
                String isbn = mList.get(position).isbn;
                String bookname = mList.get(position).bookname;
                SimpleDateFormat format = new SimpleDateFormat("yyy年mm月dd日   HH:mm:ss ");
                Date curDate = new Date();//获取当前时间
                String time = format.format(curDate);
                HistoryBean historyBean = new HistoryBean();
                historyBean.BookIsbn = isbn;
                Log.i(TAG, "传输过去" + historyBean.BookIsbn);
                historyBean.UserName = username;
                Log.i(TAG, "传输过去" + historyBean.UserName);
                historyBean.Time = time;
                Log.i(TAG, "传输过去" + historyBean.Time);

                if (HistoryDatabaseDao.checkHistory(mContext,historyBean)) {
                    HistoryDatabaseDao.updateHistory(mContext,historyBean.BookIsbn,historyBean.Time);
                }else HistoryDatabaseDao.addHistory(mContext, historyBean);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
            return mList.size()!=0?mList.size():0;
    }

     class BookMangerHolder extends RecyclerView.ViewHolder {

         ImageView iv_bookitem_im;
         TextView book_title;
         CardView card_view;

         BookMangerHolder(View itemView) {
            super(itemView);
            book_title = (TextView) itemView.findViewById(R.id.tv_c_all_title);
             iv_bookitem_im = (ImageView) itemView.findViewById(R.id.iv_c_all_im);
             card_view = (CardView) itemView.findViewById(R.id.c_all_item);
         }
    }
}
