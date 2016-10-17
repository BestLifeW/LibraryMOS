package com.rjxy.librarymos;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.rjxy.librarymos.dao.UserDatabaseDao;

import static android.content.ContentValues.TAG;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        boolean b = UserDatabaseDao.isUserExists("wtc",getContext());
        Log.i(TAG, "ApplicationTest: "+b);
    }
}