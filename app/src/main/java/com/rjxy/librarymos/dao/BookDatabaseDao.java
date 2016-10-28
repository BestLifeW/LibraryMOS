package com.rjxy.librarymos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.rjxy.librarymos.bean.BookBean;
import com.rjxy.librarymos.database.DatabaseHelper;

import java.util.ArrayList;

import static com.rjxy.librarymos.utils.UIUtils.img;

/**
 * Created by llt on 2016/10/19.
 */

public class BookDatabaseDao {
    private static DatabaseHelper databaseHelper;
    private static final String TAG = "BookDatabaseDao";

    //查询所有图书信息
    public static ArrayList<BookBean> getBookInfo(Context context) {
        ArrayList<BookBean> booklist = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.BOOKINFO;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                BookBean book = new BookBean();
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));  //获取书名
                int number = cursor.getInt(cursor.getColumnIndex("number"));//获取剩余数量
                String isbn = cursor.getString(cursor.getColumnIndex("isbn"));  //获取isbn编码
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String press = cursor.getString(cursor.getColumnIndex("press"));
                String pressyear = cursor.getString(cursor.getColumnIndex("pressyear"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                book.bookname = bookname;
                book.number = number;
                book.isbn = isbn;
                book.author = author;
                book.press = press;
                book.pressyear = pressyear;
                book.category = category;
                book.sunmmary = summary;
                booklist.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return booklist;
    }


    //添加图书信息
    public static boolean AddBookInfo(BookBean book, Context context, Drawable drawable) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        book.photo = img(drawable);
        values.put("bookname", book.bookname);
        values.put("number", book.number);
        values.put("isbn", book.isbn);
        values.put("author", book.author);
        values.put("press", book.press);
        values.put("pressyear", book.pressyear);
        values.put("category", book.category);
        values.put("sunmmary", book.sunmmary);
        values.put("photo", book.photo);
        long insert = db.insert(DatabaseHelper.BOOKINFO, null, values);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    public static BookBean getBookInfoByISBN(Context context, String id) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from " + DatabaseHelper.BOOKINFO + " where isbn = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{id});
        BookBean book = new BookBean();
        if (cursor.moveToFirst()) {
            String bookname = cursor.getString(cursor.getColumnIndex("bookname"));  //获取书名
            int number = cursor.getInt(cursor.getColumnIndex("number"));//获取剩余数量
            String isbn = cursor.getString(cursor.getColumnIndex("isbn"));  //获取isbn编码
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String press = cursor.getString(cursor.getColumnIndex("press"));
            String pressyear = cursor.getString(cursor.getColumnIndex("pressyear"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String summary = cursor.getString(cursor.getColumnIndex("summary"));
            book.bookname = bookname;
            book.number = number;
            book.isbn = isbn;
            book.author = author;
            book.press = press;
            book.pressyear = pressyear;
            book.category = category;
            book.sunmmary = summary;
        }
        cursor.close();
        db.close();
        return book;
    }
    /*
    * select * from book_info WHERE isbn in (
    select reserve_info.isbn FROM reserve_info WHERE number = "")
    * */

    public static ArrayList<BookBean> getByResverISBN(Context context, String usernumber) {
        databaseHelper = new DatabaseHelper(context, null);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        //String sql = "select * from " + DatabaseHelper.BOOKINFO + " where isbn = ?";
        String sql = "select * from " + DatabaseHelper.BOOKINFO + "where isbn in (select reserve_info.isbn FROM reserve_info where number = ?)";
        ArrayList<BookBean> bookList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, new String[]{usernumber});

        if (cursor.moveToFirst()) {
            BookBean book = new BookBean();
            String bookname = cursor.getString(cursor.getColumnIndex("bookname"));  //获取书名
            int number = cursor.getInt(cursor.getColumnIndex("number"));//获取剩余数量
            String isbn = cursor.getString(cursor.getColumnIndex("isbn"));  //获取isbn编码
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String press = cursor.getString(cursor.getColumnIndex("press"));
            String pressyear = cursor.getString(cursor.getColumnIndex("pressyear"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String summary = cursor.getString(cursor.getColumnIndex("summary"));
            book.bookname = bookname;
            book.number = number;
            book.isbn = isbn;
            book.author = author;
            book.press = press;
            book.pressyear = pressyear;
            book.category = category;
            book.sunmmary = summary;
            bookList.add(book);
        }
        cursor.close();
        db.close();
        return bookList;
    }
}
