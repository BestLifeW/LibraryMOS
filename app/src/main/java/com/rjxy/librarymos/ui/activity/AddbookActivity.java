package com.rjxy.librarymos.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class AddbookActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private int SELECT_PIC = 1;
    private int SELECT_PIC_KITKAT = 2;
    private EditText mEt_bookname;
    private EditText mEt_isbn;
    private EditText mEt_author;
    private EditText mEt_public;
    private EditText mEt_count;
    private Spinner mEt_classify;
    private android.support.v7.widget.AppCompatCheckBox mApb_isHot;
    private Button mBtn_addPic;
    private ImageView mIv_showPPic;
    private Button mBtn_sumbit;
    private Uri uri;
    private Bitmap bitmap;
    private static final String TAG = "图片";
    private String classify;
    private EditText mEt_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        context = getApplicationContext();
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        mEt_bookname = (EditText) findViewById(R.id.et_bookname);
        mEt_isbn = (EditText) findViewById(R.id.et_isbn);
        mEt_author = (EditText) findViewById(R.id.et_author);
        mEt_public = (EditText) findViewById(R.id.et_public);
        mEt_count = (EditText) findViewById(R.id.et_count);
        mEt_classify = (Spinner) findViewById(R.id.et_classify);
        mApb_isHot = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.apb_isHot);
        mBtn_addPic = (Button) findViewById(R.id.btn_addPic);
        mIv_showPPic = (ImageView) findViewById(R.id.iv_showPPic);
        mBtn_sumbit = (Button) findViewById(R.id.btn_sumbit);
        mEt_des = (EditText) findViewById(R.id.et_desc);
        mBtn_addPic.setOnClickListener(this);
        mBtn_sumbit.setOnClickListener(this);
    }

    private void ChoosePic() {
        PrefUtils.getImageFromAlbum(AddbookActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        uri =  data.getData();
        Log.i(TAG, "onActivityResult: "+uri);
        bitmap = PrefUtils.getBitmap(uri,AddbookActivity.this);
        mIv_showPPic.setImageBitmap(bitmap);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addPic:
                ChoosePic();
                break;
            case R.id.btn_sumbit:
                SumbitInfo();
                break;

            default:
                break;
        }
    }

    /*
    * 提交数据库
    * */
    private void SumbitInfo() {
            /*
            *  mEt_bookname  mEt_isbn mEt_author  mEt_public mEt_count mEt_classify mApb_isHot mBtn_addPic  mIv_showPPic mBtn_sumbit
            */
        String bookname = mEt_bookname.getText().toString().trim();
        String isbn = mEt_isbn.getText().toString().trim();
        String author = mEt_author.getText().toString().trim();
        String publicname = mEt_public.getText().toString().trim();
        String desc = mEt_des.getText().toString().trim();
        int count = Integer.parseInt(mEt_count.getText().toString().trim());
        classify = mEt_classify.getSelectedItem().toString();

            BookBean bookBean = new BookBean();
        bookBean.author = author;
        bookBean.bookname = bookname;
        bookBean.isbn = isbn;
        bookBean.press = publicname;
        bookBean.number = count;
        bookBean.category = classify;
        bookBean.sunmmary = desc;
        boolean b = BookDatabaseDao.AddBookInfo(bookBean, getApplicationContext(), bitmap);
        if (b) {
            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }



}
