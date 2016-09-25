package com.rjxy.librarymos.bean;

/**
 * 用户been类
 * Created by lovec on 2016/9/22.
 */

public class UserBean {

    public int id;
    public String name;
    public String password;
    public String number;

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
