package com.rjxy.librarymos.bean;

/**
 * Created by llt on 2016/12/3.
 */

public class HistoryBean {
    public int id;
    public String BookIsbn;
    public String UserName;
    public String BookName;
    public String Time;

    @Override
    public String toString() {
        return "HistoryBean{" +
                "id=" + id +
                ", BookIsbn='" + BookIsbn + '\'' +
                ", UserName='" + UserName + '\'' +
                ", BookName='" + BookName + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
