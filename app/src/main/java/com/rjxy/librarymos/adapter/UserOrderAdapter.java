package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rjxy.librarymos.bean.ReserveBean;

import java.util.ArrayList;

/**
 * Created by lovecanon0823 on 16-12-1.
 */
public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.MyViewHolder> {

    private ArrayList<ReserveBean> mList;

    private Context context;

    public UserOrderAdapter(Context context, ArrayList<ReserveBean> mList) {
        mList = new ArrayList<>();
        this.context = context;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
