package com.a1_1801040189.myfriends.model;

import java.io.Serializable;

public class Friend implements Serializable {
private String name;
private String email;
private  String phoneNo;
private int id;
    public Friend(String name, String email, String phoneNo ) {
        this.email = email;
        this.name=name;
        this.phoneNo=phoneNo;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
