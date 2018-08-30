package com.qin.mvpdemo.presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.qin.mvpdemo.bean.User;
import com.qin.mvpdemo.model.LoginListener;
import com.qin.mvpdemo.model.UserModle;

import java.lang.ref.WeakReference;

public class UserPresenter {

    private UserModle mUserModle;
    private static ILoginView mILoginView;
    private static final int LOGIN_FAILURE = 100;
    private MyHandler mMyHandler;

    public UserPresenter(ILoginView loginView, Activity activity) {
        this.mILoginView = loginView;
        mUserModle = new UserModle();
        mMyHandler = new MyHandler(activity);
    }

    public void login() {
        String userName = mILoginView.getUserName();
        String passWord = mILoginView.getPassWord();
        if (mILoginView.isEmpty(userName)&&mILoginView.isEmpty(passWord)) {
            mILoginView.setEdtiTextErrorTxt(userName, passWord);
            return;
        }
        mILoginView.showLoginDialog();
        mUserModle.login(userName, passWord, new LoginListener() {
            @Override
            public void loginSuccess(User user) {
                mILoginView.hideLoginDialog();
                mILoginView.toLoginSuccessAct(user);
            }

            @Override
            public void loginFailure() {
                mILoginView.hideLoginDialog();
                Message message = mMyHandler.obtainMessage();
                message.what = LOGIN_FAILURE;
                mMyHandler.sendMessage(message);
            }
        });
    }

    public void clearEditTxt() {
        mILoginView.clearPassWord();
        mILoginView.clearUserNmae();
    }

    static class MyHandler extends Handler {

        private WeakReference<Activity> mActivityWeakReference;

        public MyHandler(Activity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivityWeakReference.get() != null) {
                switch (msg.what) {
                    case LOGIN_FAILURE:
                        mILoginView.showLoginFailureMsg();
                        break;
                }
            }
        }
    }
}
