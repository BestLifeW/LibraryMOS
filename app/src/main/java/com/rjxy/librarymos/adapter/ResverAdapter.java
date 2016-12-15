package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class ResverAdapter extends RecyclerView.Adapter<ResverAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ReserveBean> reserList;
    private String userNumber;

    private static final String TAG = "ResverAdapter";
    private ArrayList<BookBean> resverBooklist;

    public ResverAdapter(Context context, ArrayList<ReserveBean> reserveinfo, ArrayList<BookBean> resverBooklist) {
        this.context = context;
        this.reserList = new ArrayList<>();
        this.resverBooklist = new ArrayList<>();
        this.resverBooklist = resverBooklist;
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
        View view = LayoutInflater.from(context).inflate(R.layout.resver_item,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String isbn = reserList.get(position).BookIsbn;
        BookBean bookBean = BookDatabaseDao.getBookInfoByISBN(context, isbn);
        byte[] photo = bookBean.photo;
        if (photo != null) {
            Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);
            holder.iv_resveritem_im.setImageBitmap(bitmap);
        }
        holder.tv_resveritem_title.setText(bookBean.bookname);
        holder.tv_resveritem_anthuor.setText("提交时间:" + reserList.get(position).SubmitTime);
        holder.tv_resveritem_dec.setText(bookBean.sunmmary);
        holder.tv_resveritem_resvertime.setText("预约时间:" + reserList.get(position).ReserveTime);
        holder.tv_resveritem_approve.setText(reserList.get(position).approve);
        holder.tv_resveritem_quantity.setText("数量:" + reserList.get(position).quantity + "本");

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDelDialog(position);
            }
        });
    }

    private void showDelDialog(final int position) {
        //删除逻辑
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否删除订单？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int number = Integer.parseInt(reserList.get(position).quantity);  //借的数量
                int number1 = resverBooklist.get(position).number;//剩余的数量
                int j = number + number1; //退还，然后一共多少本
                Boolean aBoolean = ReserveDatabassDao.delReserveByISBNAnd(resverBooklist.get(position).isbn, userNumber, context);
                if (aBoolean) {
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    boolean b = BookDatabaseDao.updateBookNumber(j, resverBooklist.get(position).isbn, context);
                    if (b) {
                        Log.i(TAG, "删除成功" + "成功返还");
                        reserList.remove(position);
                        notifyDataSetChanged();

                    } else {
                        Log.i(TAG, "删除失败");
                    }
                } else {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return reserList == null ? 0 : reserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_resveritem_im;
        private final TextView tv_resveritem_title;
        private final TextView tv_resveritem_anthuor;
        private final TextView tv_resveritem_dec;
        private final TextView tv_resveritem_resvertime;
        private final TextView tv_resveritem_approve;
        private final TextView tv_resveritem_quantity;
        CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_resveritem_im = (ImageView) itemView.findViewById(R.id.iv_resveritem_im);
            tv_resveritem_title = (TextView) itemView.findViewById(R.id.tv_resveritem_title);
            tv_resveritem_anthuor = (TextView) itemView.findViewById(R.id.tv_resveritem_anthuor);
            tv_resveritem_dec = (TextView) itemView.findViewById(R.id.tv_resveritem_dec);
            tv_resveritem_resvertime = (TextView) itemView.findViewById(R.id.tv_resveritem_resvertime);
            tv_resveritem_approve = (TextView) itemView.findViewById(R.id.tv_resveritem_approve);
            tv_resveritem_quantity = (TextView) itemView.findViewById(R.id.tv_resveritem_quantity);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
