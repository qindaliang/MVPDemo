package com.qin.mvpdemo.presenter;

import android.widget.EditText;

import com.qin.mvpdemo.bean.User;

public interface ILoginView {
    void showLoginDialog();
    void hideLoginDialog();
    void toLoginSuccessAct(User user);
    void showLoginFailureMsg();
    String getUserName();
    String getPassWord();
    void clearPassWord();
    void clearUserNmae();
    boolean isEmpty(String string);
    void setEdtiTextErrorTxt(String s,String s1);
}
