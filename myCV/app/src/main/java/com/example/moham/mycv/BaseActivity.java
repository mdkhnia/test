package com.example.moham.mycv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.hawk.Hawk;

public class BaseActivity extends AppCompatActivity {
    public Context mContext = this;
    public Activity mActivity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(mContext).build();
    }
}
