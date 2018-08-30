package com.qin.mvpdemo.view;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qin.mvpdemo.R;
import com.qin.mvpdemo.bean.User;
import com.qin.mvpdemo.presenter.ILoginView;
import com.qin.mvpdemo.presenter.UserPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private EditText mEtPwd;
    private EditText mEtName;
    private Button mBtnLogin;
    private Button mBtnClearNmae;
    private Button mBtnClearPwd;
    private UserPresenter mUserPresenter;
    private AlertDialog mDialog;
    private String mUserName;
    private String mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initOnClickLilstener();
        initDialog();
        mUserPresenter = new UserPresenter(this, this);
    }

    private void initOnClickLilstener() {
        mBtnLogin.setOnClickListener(v -> {
            mUserPresenter.login();
        });
        mBtnClearNmae.setOnClickListener(v -> {
            mUserPresenter.clearEditTxt();
        });
        mBtnClearPwd.setOnClickListener(v -> {
            mUserPresenter.clearEditTxt();
        });
    }

    private void initViews() {
        mEtPwd = findViewById(R.id.et_password);
        mEtName = findViewById(R.id.et_username);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnClearNmae = findViewById(R.id.btn_name_clear);
        mBtnClearPwd = findViewById(R.id.btn_pwd_clear);
    }

    @Override
    public void showLoginDialog() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    private void initDialog() {
        mDialog = new AlertDialog.Builder(this)
                .setMessage("loading...")
                .create();
    }

    @Override
    public void hideLoginDialog() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void toLoginSuccessAct(User user) {
        MainActivity.startActivity(this, user);
    }

    @Override
    public void showLoginFailureMsg() {
        Toast.makeText(this, "login come no things", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserName() {
        mUserName = mEtName.getText().toString().trim();
        return mUserName;
    }

    @Override
    public String getPassWord() {
        mPassWord = mEtPwd.getText().toString().trim();
        return mPassWord;
    }

    @Override
    public void clearPassWord() {
        mEtPwd.setText("");
    }

    @Override
    public void clearUserNmae() {
        mEtName.setText("");
    }

    @Override
    public boolean isEmpty(String string) {
        return "".equals(string);
    }

    @Override
    public void setEdtiTextErrorTxt(String s,String s1) {
        if (isEmpty(s)) {
            mEtName.setError("please input username");
        } else if (isEmpty(s1))
            mEtPwd.setError("please input password");

    }

    @Override
    protected void onDestroy() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        super.onDestroy();
    }
}
