package com.rjxy.librarymos;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.bean.UserBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.rjxy.librarymos.dao.UserDatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ReserveBean> reserList;

    public OrderAdapter(Context context, ArrayList<ReserveBean> reserveinfo) {
        this.mContext = context;
        this.reserList = new ArrayList<>();
        this.reserList = reserveinfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String userNumber = reserList.get(position).UserNumber;
        final String isbn = reserList.get(position).BookIsbn;
        UserBean userBean = UserDatabaseDao.getUserInfoByNumber(userNumber, mContext);
        BookBean bookBean = BookDatabaseDao.getBookInfoByISBN(mContext, isbn);
        byte[] photo = bookBean.photo;
        Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);
        holder.iv_useritem_im.setImageBitmap(bitmap);
        holder.tv_orderitem_user.setText(userBean.name);
        holder.tv_orderitem_title.setText(bookBean.bookname);
        holder.tv_orderitem_reserve_number.setText(reserList.get(position).quantity);
        holder.tv_orderitem_resvertime.setText("预约时间:" + reserList.get(position).ReserveTime);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(isbn, userNumber,position);
            }
        });
    }

    private void showDialog(final String isbn, final String userNumber, final int position) {
        //询问是否同意
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("");
        alertDialog.setMessage("是否同意该用户借书");
        alertDialog.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Boolean aBoolean = ReserveDatabassDao.updateReserveApprove(mContext, "已批准", userNumber, isbn);
                if (aBoolean) {
                    Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                    reserList.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.setNegativeButton("取消", null);
        alertDialog.show();
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
        private final CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_useritem_im = (ImageView) itemView.findViewById(R.id.iv_useritem_im);
            tv_orderitem_user = (TextView) itemView.findViewById(R.id.tv_orderitem_user);
            tv_orderitem_title = (TextView) itemView.findViewById(R.id.tv_orderitem_title);
            tv_orderitem_reserve_number = (TextView) itemView.findViewById(R.id.tv_orderitem_reserve_number);
            tv_orderitem_resvertime = (TextView) itemView.findViewById(R.id.tv_orderitem_resvertime);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
