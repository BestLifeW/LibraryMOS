package com.rjxy.librarymos;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.rjxy.librarymos.bean.ReserveBean;
import com.rjxy.librarymos.dao.ReserveDatabassDao;
import com.rjxy.librarymos.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class testReserdatabasedao extends InstrumentationTestCase {
      DatabaseHelper databaseHelper;

    private static final String TAG = "BookDatabaseDao";
    public void test() throws Exception {
        databaseHelper = new DatabaseHelper(getInstrumentation().getContext(), null);
        ArrayList<ReserveBean> allReserve = ReserveDatabassDao.getAllReserve(getInstrumentation().getContext());
        for (ReserveBean reserveBean : allReserve) {
            Log.i("test", reserveBean.toString());
        }
    }
}
