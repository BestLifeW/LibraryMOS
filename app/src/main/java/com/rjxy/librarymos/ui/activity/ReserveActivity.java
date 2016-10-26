package com.rjxy.librarymos.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjxy.librarymos.R;
import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.dao.BookDatabaseDao;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;

public class ReserveActivity extends AppCompatActivity implements View.OnClickListener {

    private String book_isbn;
    private BookBean bookBean;
    private TextView tv_reserve_bookname;
    private Button btn_reserve_choosetime;
    private TextView tv_reserve_time;
    private Button btn_reserve_number;
    private TextView tv_reserve_shownumber;
    private int choosenumber;
    private Button btn_reserve_submit;
    private TextView tv_reserve_number;
    private static final String TAG = "ReserveActivity";
    private RelativeLayout rl_reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        book_isbn = getIntent().getStringExtra("book_info");
        bookBean = BookDatabaseDao.getBookInfoByISBN(getApplicationContext(), book_isbn);

        init();

    }

    //初始化
    private void init() {
        initView();
        initData();
    }

    private void initData() {
        tv_reserve_bookname.setText(bookBean.bookname);
        tv_reserve_shownumber.setText(bookBean.number + "本");
    }

    private void initView() {
        tv_reserve_bookname = (TextView) findViewById(R.id.tv_reserve_bookname);
        btn_reserve_choosetime = (Button) findViewById(R.id.btn_reserve_choosetime);
        tv_reserve_time = (TextView) findViewById(R.id.tv_reserve_time);
        tv_reserve_number = (TextView) findViewById(R.id.tv_reserve_number);
        tv_reserve_shownumber = (TextView) findViewById(R.id.tv_reserve_shownumber);
        btn_reserve_choosetime.setOnClickListener(this);
        btn_reserve_number = (Button) findViewById(R.id.btn_reserve_number);
        btn_reserve_number.setOnClickListener(this);
        btn_reserve_submit = (Button) findViewById(R.id.btn_reserve_submit);
        btn_reserve_submit.setOnClickListener(this);
        rl_reserve = (RelativeLayout) findViewById(R.id.rl_reserve);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reserve_choosetime:
                ShowDataPicker();
                break;
            case R.id.btn_reserve_number:
                ShowNumberPicker();
                break;
            case R.id.btn_reserve_submit:
                Submit();
                break;
        }

    }


    private void Submit() {
        //提交
        String reserve_number = tv_reserve_number.getText().toString();
        String tv_reserve_time = this.tv_reserve_time.getText().toString();
        int number = Integer.parseInt(reserve_number);
        Log.i(TAG, "Submit: " + number);
        if (number > bookBean.number) {
            Snackbar.make(rl_reserve, "抱歉，图书不足，请重新选择", Snackbar.LENGTH_LONG).show();
            return;
        }
        if (tv_reserve_time == null && tv_reserve_time.equals("")) {
            Snackbar.make(rl_reserve, "预定日期是必选项", Snackbar.LENGTH_LONG).show();
            return;
        }


    }


    private void ShowNumberPicker() {
        NumberPicker picker = new NumberPicker(this);
        picker.setOffset(2);//偏移量
        picker.setRange(1, 5, 1);//数字范围
        picker.setSelectedItem(1);
        picker.setLabel("本");
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {

                tv_reserve_number.setText(option);
            }
        });
        picker.show();
    }

    private void ShowDataPicker() {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
        picker.setRangeStart(2016, 8, 29);//开始范围
        picker.setRangeEnd(2022, 1, 1);//结束范围
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                tv_reserve_time.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }


}
