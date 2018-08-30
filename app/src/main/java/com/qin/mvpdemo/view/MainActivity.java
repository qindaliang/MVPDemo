package com.qin.mvpdemo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qin.mvpdemo.R;
import com.qin.mvpdemo.bean.User;

public class MainActivity extends AppCompatActivity {


    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getBundleExtra("user");
        mUser = bundle.getParcelable("user");
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvPwd = findViewById(R.id.tv_pwd);
        tvName.setText(mUser.getUserName());
        tvPwd.setText(mUser.getPassWord());
    }

    public static void startActivity(Context context, User user){
        Intent intent = new Intent(context,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user",user);
        intent.putExtra("user",bundle);
        context.startActivity(intent);
    }
}
