package com.rjxy.librarymos.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.ui.activity.AlterBookActivity;
import com.rjxy.librarymos.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created By TianChaoWang on 2016/10/31.
 */

public class BookManagerAdapter extends RecyclerView.Adapter<BookManagerAdapter.BookMangerHolder> {


    private ArrayList<BookBean> mList;
    private Context mContext;

    public BookManagerAdapter(Context context, ArrayList<BookBean> list) {
        mList = new ArrayList<>();
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public BookMangerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_managaitem,
                parent, false);
        return new BookMangerHolder(view);

    }

    @Override
    public void onBindViewHolder(BookManagerAdapter.BookMangerHolder holder, final int position) {
        final BookBean bookBean = mList.get(position);
        holder.book_title.setText(bookBean.bookname);
        Bitmap bitmap = PrefUtils.byteArrayToBmp(bookBean.photo);
        holder.iv_bookitem_im.setImageBitmap(bitmap);
        holder.tv_bookitem_dec.setText(bookBean.sunmmary);
        holder.tv_bookitem_press.setText(bookBean.press);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(mContext, AlterBookActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("book_isbn", bookBean.isbn);
                mContext.startActivity(intent);

            }
        });
        holder.card_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ShowDelDialog(position);
                return false;
            }
        });
    }
    /*
    * 删除对话框
    * */
    private void ShowDelDialog(final int position) {
        AlertDialog.Builder bulider = new AlertDialog.Builder(mContext,R.style.AlertDialogCustom);
        bulider.setMessage("不可逆操作，是否真的要删除?");
        bulider.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean b = BookDatabaseDao.delBookByISBN(mList.get(position).isbn, mContext);
                if (b) {
                    mList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bulider.setNegativeButton("取消", null);
        bulider.show();
    }

    @Override
    public int getItemCount() {
        return mList.size() != 0 ? mList.size() : 0;
    }

    class BookMangerHolder extends RecyclerView.ViewHolder {

        ImageView iv_bookitem_im;
        TextView book_title;
        TextView tv_bookitem_dec;
        CardView card_view;
        TextView tv_bookitem_press;

        BookMangerHolder(View itemView) {
            super(itemView);
            book_title = (TextView) itemView.findViewById(R.id.tv_bookitem_title);
            iv_bookitem_im = (ImageView) itemView.findViewById(R.id.iv_bookitem_im);
            tv_bookitem_dec = (TextView) itemView.findViewById(R.id.tv_bookitem_dec);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            tv_bookitem_press = (TextView) itemView.findViewById(R.id.tv_bookitem_press);
        }
    }
}
