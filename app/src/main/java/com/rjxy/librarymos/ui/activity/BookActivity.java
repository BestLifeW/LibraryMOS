package com.rjxy.librarymos.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;

public class BookActivity extends AppCompatActivity {

    private TextView tv_bookitem_bookname;
    private TextView tv_bookitem_isbn;
    private TextView tv_bookitem_author;
    private TextView tv_bookitem_press;
    private TextView tv_bookitem_pressyear;
    private Toolbar toolbar2;
    private String book_ids;
    private BookBean bookInfo;
    private TextView tv_bookitem_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

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
        tv_bookitem_bookname = (TextView) findViewById(R.id.tv_bookitem_bookname);
        tv_bookitem_isbn = (TextView) findViewById(R.id.tv_bookitem_isbn);
        tv_bookitem_author = (TextView) findViewById(R.id.tv_bookitem_author);
        tv_bookitem_press = (TextView) findViewById(R.id.tv_bookitem_press);
        tv_bookitem_pressyear = (TextView) findViewById(R.id.tv_bookitem_pressyear);
        tv_bookitem_des = (TextView) findViewById(R.id.tv_bookitem_des);

        tv_bookitem_des.setText(bookInfo.sunmmary);
        tv_bookitem_bookname.setText(bookInfo.bookname);
        tv_bookitem_author.setText(bookInfo.author);
        tv_bookitem_isbn.setText(bookInfo.isbn);
        tv_bookitem_press.setText(bookInfo.press);
        tv_bookitem_pressyear.setText(bookInfo.pressyear);


    }
}
