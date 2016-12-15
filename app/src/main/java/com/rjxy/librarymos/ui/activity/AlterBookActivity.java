package com.rjxy.librarymos.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;
import com.rjxy.librarymos.utils.PrefUtils;

public class AlterBookActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEt_isbn;
    private EditText mEt_author;
    private EditText mEt_public;
    private Spinner mEt_classify;
    private AppCompatCheckBox mApb_isHot;
    private Button mBtn_addPic;
    private ImageView mIv_showPPic;
    private Button mBtn_sumbit;
    private EditText mEt_des;
    private static final String TAG = "AlterBookActivity";
    private BookBean bookInfo;
    private EditText mEt_bookname;
    private EditText mEt_count;
    private String book_ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterbook);
        book_ids = getIntent().getStringExtra("book_isbn");
        bookInfo = BookDatabaseDao.getBookInfoByISBN(getApplicationContext(), book_ids);
        init();

    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mEt_bookname = (EditText) findViewById(R.id.et_bookname);
        mEt_isbn = (EditText) findViewById(R.id.et_isbn);
        mEt_author = (EditText) findViewById(R.id.et_author);
        mEt_public = (EditText) findViewById(R.id.et_public);
        mEt_classify = (Spinner) findViewById(R.id.et_classify);
        mEt_count = (EditText) findViewById(R.id.et_count);
        mApb_isHot = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.apb_isHot);
        mBtn_addPic = (Button) findViewById(R.id.btn_addPic);
        mIv_showPPic = (ImageView) findViewById(R.id.iv_showPPic);
        mBtn_sumbit = (Button) findViewById(R.id.btn_sumbit);
        mEt_des = (EditText) findViewById(R.id.et_desc);
        mBtn_sumbit.setOnClickListener(this);
    }

    private void initData() {
        if (bookInfo != null) {
            mEt_bookname.setText(bookInfo.bookname);
            mEt_author.setText(bookInfo.author);
            mEt_isbn.setText(bookInfo.isbn);
            mEt_public.setText(bookInfo.press);
            mEt_des.setText(bookInfo.sunmmary);
            mEt_count.setText(bookInfo.number+"");
            byte[] photo = bookInfo.photo;
            Bitmap bitmap = PrefUtils.byteArrayToBmp(photo);
            mIv_showPPic.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sumbit:
                alertBook();
            break;
        }
    }

    /*
    * 修改图书
    * */
    private void alertBook() {
        BookBean bookBean = new BookBean();
        String bookname = mEt_bookname.getText().toString().trim();
        String isbn = mEt_isbn.getText().toString().trim();
        String author = mEt_author.getText().toString().trim();
        String publicname = mEt_public.getText().toString().trim();
        String desc = mEt_des.getText().toString().trim();
        int count = Integer.parseInt(mEt_count.getText().toString().trim());

        bookBean.number = count;
        bookBean.isbn=isbn;
        bookBean.author = author;
        bookBean.press = publicname;
        bookBean.sunmmary = desc;
        bookBean.bookname = bookname;

        boolean b = BookDatabaseDao.updateBookinfo(getApplicationContext(), book_ids, bookBean);
        if (b){
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
