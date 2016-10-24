package com.rjxy.librarymos.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.ui.activity.BookActivity;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * 作者：lovec on 2016/9/25 20:23
 * 邮箱：lovecanon0823@gmail.com
 */
public class HotBookAdapter extends RecyclerView.Adapter<HotBookAdapter.MyViewHolder> {

    private Context context;
    private int[] imagesId;
    private ArrayList<BookBean> mList = new ArrayList<>();

    public HotBookAdapter(Context context, int[] image, ArrayList<BookBean> bookInfo) {
        this.context = context;
        this.imagesId = image;
        this.mList = bookInfo;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,
                parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        //holder.rlv_item_img.setImageResource(imagesId[position]);
        holder.rlv_item_title.setText(mList.get(position).isbn);
        holder.news_desc.setText(mList.get(position).sunmmary);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("book_isbn", mList.get(position).isbn);
                Log.i(TAG, "传输过去" + mList.get(position).isbn);
                context.startActivity(intent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                //intent.putExtra(Intent.EXTRA_TEXT, newses.get(j).getDesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(context,NewsActivity.class);
                //intent.putExtra("News",newses.get(j));
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button share;
        Button readMore;
        ImageView rlv_item_img;
        TextView rlv_item_title;
        TextView news_desc;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlv_item_img = (ImageView) itemView.findViewById(R.id.rlv_item_img);
            rlv_item_title = (TextView) itemView.findViewById(R.id.rlv_item_title);
            news_desc = (TextView) itemView.findViewById(R.id.news_desc);
            rlv_item_title.setBackgroundColor(Color.argb(20, 0, 0, 0));
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            share = (Button) itemView.findViewById(R.id.btn_share);
            readMore = (Button) itemView.findViewById(R.id.btn_more);
        }
    }
}
