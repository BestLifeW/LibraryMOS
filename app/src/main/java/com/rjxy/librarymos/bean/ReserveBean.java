package com.rjxy.librarymos.bean;

/**
 * Created by TianChaoWang on 2016/10/28.
 */

public class ReserveBean {
    public int id;
    public String BookIsbn;
    public String UserNumber;
    public String SubmitTime;
    public String ReserveTime;
    public String quantity;//数量
    public String approve;//是否批准

    @Override
    public String toString() {
        return "ReserveBean{" +
                "id=" + id +
                ", BookIsbn='" + BookIsbn + '\'' +
                ", UserNumber='" + UserNumber + '\'' +
                ", SubmitTime='" + SubmitTime + '\'' +
                ", ReserveTime='" + ReserveTime + '\'' +
                ", quantity='" + quantity + '\'' +
                ", approve='" + approve + '\'' +
                '}';
    }
}
