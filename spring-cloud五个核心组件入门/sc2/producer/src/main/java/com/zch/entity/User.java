package com.zch.entity;

import java.util.Date;

public class User {

    private Integer id;
    private Date date;

    public User() {
    }

    public User(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
