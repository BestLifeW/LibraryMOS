package com.rjxy.librarymos.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjxy.librarymos.R;

/**
 * 作者：lovec on 2016/9/25 20:23
 * 邮箱：lovecanon0823@gmail.com
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private int[] imagesId;

    public RecyclerViewAdapter(Context context, int[] image) {
        this.context = context;
        this.imagesId = image;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,
                parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.rlv_item_img.setImageResource(imagesId[position]);
        holder.rlv_item_title.setText("这里是位置"+position);
    }

    @Override
    public int getItemCount() {
        return imagesId.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView rlv_item_img;
        TextView rlv_item_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlv_item_img = (ImageView) itemView.findViewById(R.id.rlv_item_img);
            rlv_item_title = (TextView) itemView.findViewById(R.id.rlv_item_title);
        }
    }
}
