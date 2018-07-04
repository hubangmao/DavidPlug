package com.hbm.code.pluglibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 介绍: 实现与宿主Context实例的切换
 * author:胡邦茂
 * CreateDate:2018年07月03日 17:02
 */
public class BasePlugActivity extends AppCompatActivity implements PlugActivity {
    //宿主实例
    public Activity mProxyInstance;

    @Override
    public void attach(Activity att) {
        mProxyInstance = att;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (mProxyInstance == null) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        if (mProxyInstance == null) {
            super.onStart();
        }

    }

    @Override
    public void onResume() {
        if (mProxyInstance == null) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mProxyInstance == null) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mProxyInstance == null) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mProxyInstance == null) {
            super.onDestroy();
        }
    }

    @Override
    public void onRestart() {
        if (mProxyInstance == null) {
            super.onRestart();
        }
    }

    public Activity getActivity() {
        return mProxyInstance == null ? this : mProxyInstance;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mProxyInstance == null) {
            super.setContentView(layoutResID);
        } else {
            mProxyInstance.setContentView(layoutResID);
        }
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return (T) (mProxyInstance == null ? super.findViewById(id) : mProxyInstance.findViewById(id));
    }

    @Override
    public ClassLoader getClassLoader() {
        return mProxyInstance == null ? super.getClassLoader() : mProxyInstance.getClassLoader();
    }

    @Override
    public Resources getResources() {
        return mProxyInstance == null ? super.getResources() : mProxyInstance.getResources();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return mProxyInstance == null ? super.getLayoutInflater() : mProxyInstance.getLayoutInflater();
    }

    @Override
    public Window getWindow() {
        return mProxyInstance == null ? super.getWindow() : mProxyInstance.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return mProxyInstance == null ? super.getWindowManager() : mProxyInstance.getWindowManager();
    }

    @Override
    public void startActivity(Intent intent) {
        if (mProxyInstance == null) {
            super.startActivity(intent);
        } else {
            Intent newIntent = new Intent();
            newIntent.putExtra(ACTION_CLASS_NAME, intent.getComponent().getClassName());
            mProxyInstance.startActivity(newIntent);
        }
    }
}
