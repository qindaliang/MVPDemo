package com.qin.mvpdemo.model;

import com.qin.mvpdemo.bean.User;

public class UserModle implements IUser {

    @Override
    public void login(final String userName, final String password, final LoginListener loginListener) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ("qin".equals(userName)&&"000".equals(password)){
                User user = new User(userName,password);
                loginListener.loginSuccess(user);
            }else{
                loginListener.loginFailure();
            }

        }).start();
    }
}
