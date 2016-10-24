package com.rjxy.librarymos.bean;

/**
 * Created by llt on 2016/10/19.
 */

public class BookBean {
    public int id;
    public String bookname;
    public int number;
    public String isbn;
    public String author;
    public String press;
    public String pressyear;
    public String category;
    public String sunmmary;
    public byte[] photo;

    @Override
    public String toString() {
        return "BookBean{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", number=" + number +
                ", isbn=" + isbn +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", pressyear='" + pressyear + '\'' +
                ", category='" + category + '\'' +
                ", sunmmary='" + sunmmary + '\'' +
                ", photo=" + photo +
                '}';
    }
}
