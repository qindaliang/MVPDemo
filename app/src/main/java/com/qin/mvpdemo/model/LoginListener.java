package com.qin.mvpdemo.model;

import com.qin.mvpdemo.bean.User;

public interface LoginListener {
    void loginSuccess(User user);
    void loginFailure();
}
