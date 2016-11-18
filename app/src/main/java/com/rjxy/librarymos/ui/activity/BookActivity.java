package com.rjxy.librarymos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_bookitem_bookname;
    private TextView tv_bookitem_isbn;
    private TextView tv_bookitem_author;
    private TextView tv_bookitem_press;
    private TextView tv_bookitem_number;
    private Toolbar toolbar2;
    private String book_ids;
    private BookBean bookInfo;
    private TextView tv_bookitem_des;
    private Button btn_reserve;
    private RelativeLayout rl_book;
    private static final String TAG = "BookActivity";
    private String usernumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        usernumber = PrefUtils.getString(getApplicationContext(), PrefUtils.NUMBER, "");
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initData() {
        book_ids = getIntent().getStringExtra("book_isbn");
        bookInfo = BookDatabaseDao.getBookInfoByISBN(getApplicationContext(), book_ids);
    }

    private void initView() {

        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_reserve = (Button) findViewById(R.id.btn_reserve);
        btn_reserve.setOnClickListener(this);
        tv_bookitem_bookname = (TextView) findViewById(R.id.tv_bookitem_bookname);
        tv_bookitem_isbn = (TextView) findViewById(R.id.tv_bookitem_isbn);
        tv_bookitem_author = (TextView) findViewById(R.id.tv_bookitem_author);
        tv_bookitem_press = (TextView) findViewById(R.id.tv_bookitem_press);
        tv_bookitem_number = (TextView) findViewById(R.id.tv_bookitem_number);
        tv_bookitem_des = (TextView) findViewById(R.id.tv_bookitem_des);
        rl_book = (RelativeLayout) findViewById(R.id.rl_book);


        tv_bookitem_des.setText(bookInfo.sunmmary);
        tv_bookitem_bookname.setText(bookInfo.bookname);
        tv_bookitem_author.setText(bookInfo.author);
        tv_bookitem_isbn.setText(bookInfo.isbn);
        tv_bookitem_press.setText(bookInfo.press);
        tv_bookitem_number.setText(bookInfo.number + "本");
        Log.i(TAG, "initView:" + bookInfo.toString());

    }

    /*
    * 点击进入预定界面
    * */
    @Override
    public void onClick(View v) {
        //判断如果数量少于1提示进不去

        if (!usernumber.equals("")) {
            if (bookInfo.number < 1) {
                Snackbar.make(rl_book, "抱歉，图书数量不足,无法借阅", Snackbar.LENGTH_LONG).show();
                return;
            } else {
                Intent intent = new Intent(getApplicationContext(), ReserveActivity.class);
                intent.putExtra("book_info", bookInfo.isbn);
                finish();
                startActivity(intent);
            }
        } else {
            Snackbar.make(rl_book, "抱歉,您还未登陆", Snackbar.LENGTH_LONG).setAction("返回登陆", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnLogin();
                }
            }).show();
        }

    }

    private void returnLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
