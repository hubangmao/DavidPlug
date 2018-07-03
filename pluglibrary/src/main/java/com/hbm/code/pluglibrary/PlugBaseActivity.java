package com.hbm.code.pluglibrary;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 介绍: 实现与宿主Context实例的切换
 * author:胡邦茂
 * CreateDate:2018年07月03日 17:02
 */
public class PlugBaseActivity extends AppCompatActivity implements PlugActivity {
    //宿主实例
    public Activity mProxyInstance;

    @Override
    public void attach(Activity att) {
        mProxyInstance = att;
    }

    @Override
    public void onProxyCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState);
    }

    @Override
    public void onProxyStart() {
        onStart();
    }

    @Override
    public void onProxyResume() {
        onResume();
    }

    @Override
    public void onProxyPause() {
        onPause();
    }

    @Override
    public void onProxyStop() {
        onStop();
    }

    @Override
    public void onProxyDestroy() {
        onDestroy();
    }

    @Override
    public void onProxyRestart() {
        onRestart();
    }

    @Override
    public Resources getResources() {
        return mProxyInstance == null ? super.getResources() : mProxyInstance.getResources();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return (T) (mProxyInstance == null ? super.findViewById(id) : mProxyInstance.findViewById(id));
    }

}
