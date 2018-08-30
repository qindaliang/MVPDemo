package com.qin.mvpdemo.model;

public interface IUser {
    void login(String userName,String password,LoginListener loginListener);
}
