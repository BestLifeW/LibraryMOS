package com.rjxy.librarymos.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.rjxy.librarymos.view.DialogPopup;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/20.
 */

public class UserManaAdapter extends RecyclerView.Adapter<UserManaAdapter.UserManaHolder> {

    private ArrayList<UserBean> list = new ArrayList<>();
    private boolean isHave;
    private  final Context context;
    private UserManaAdapter.UserManaHolder UserManaHolder;


    public UserManaAdapter(ArrayList<UserBean> list, boolean isHave, Context context) {
        this.list = list;
        this.isHave = isHave;
        this.context = context;
    }

    @Override
    public UserManaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent, viewType, isHave);
        UserManaHolder = new UserManaHolder(view);
        return UserManaHolder;
    }

    @Override
    public void onBindViewHolder(UserManaHolder holder, final int position) {
        String number =list.get(position).number;
        int odercount = ReserveDatabassDao.getUserReserveQuantity(context,number);
        holder.tv_useritem_number.setText(number);
        holder.tv_useritem_name.setText(list.get(position).name);
        holder.tv_useritem_odercout.setText(odercount+"本");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserManaHolder extends RecyclerView.ViewHolder {

        TextView tv_useritem_number;
        TextView tv_useritem_name;
        TextView tv_useritem_odercout;

        public UserManaHolder(View itemView) {
            super(itemView);
            tv_useritem_number = (TextView) itemView.findViewById(R.id.tv_useritem_number);
            tv_useritem_name = (TextView) itemView.findViewById(R.id.tv_useritem_name);
            tv_useritem_odercout = (TextView) itemView.findViewById(R.id.tv_useritem_odercout);
        }
    }


    private View getView(ViewGroup parent, int viewType, boolean isHave) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.user_item,
                parent, false);
        return view;
    }
}
