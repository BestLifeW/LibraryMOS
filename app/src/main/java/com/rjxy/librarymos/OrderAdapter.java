package com.rjxy.librarymos;

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
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.dao.UserDatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ReserveBean> reserList;

    public OrderAdapter(Context context,ArrayList<ReserveBean> reserveinfo) {
        this.context = context;
        this.reserList = new ArrayList<>();
        this.reserList = reserveinfo;


    }

    /*
    * 初始化数据 nnm,
    * */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String userNumber = reserList.get(position).UserNumber;
        String isbn = reserList.get(position).BookIsbn;
        UserBean userBean = UserDatabaseDao.getUserInfoByNumber(userNumber,context);
        BookBean bookBean = BookDatabaseDao.getBookInfoByISBN(context,isbn);

        byte[] photo = bookBean.photo;
        Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);
        holder.iv_useritem_im.setImageBitmap(bitmap);

        holder.tv_orderitem_user.setText(userBean.name);
        holder.tv_orderitem_title.setText(bookBean.bookname);
        holder.tv_orderitem_reserve_number.setText(reserList.get(position).quantity);
        holder.tv_orderitem_resvertime.setText("预约时间:" + reserList.get(position).ReserveTime);

    }

    @Override
    public int getItemCount() {
        return reserList == null ? 0 : reserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_useritem_im;
        private final TextView tv_orderitem_user;
        private final TextView tv_orderitem_title;
        private final TextView tv_orderitem_reserve_number;
        private final TextView tv_orderitem_resvertime;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_useritem_im = (ImageView) itemView.findViewById(R.id.iv_useritem_im);
            tv_orderitem_user = (TextView) itemView.findViewById(R.id.tv_orderitem_user);
            tv_orderitem_title = (TextView) itemView.findViewById(R.id.tv_orderitem_title);
            tv_orderitem_reserve_number = (TextView) itemView.findViewById(R.id.tv_orderitem_reserve_number);
            tv_orderitem_resvertime = (TextView) itemView.findViewById(R.id.tv_orderitem_resvertime);
        }
    }
}
