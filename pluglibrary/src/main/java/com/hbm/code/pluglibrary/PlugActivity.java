package com.hbm.code.pluglibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 介绍: 与宿主应用达成共识协议
 * author:胡邦茂
 * CreateDate:2018年07月03日 16:53
 */
public interface PlugActivity {

    void attach(Activity att);

    void onProxyCreate(Bundle savedInstanceState);

    void onProxyStart();

    void onProxyResume();

    void onProxyPause();

    void onProxyStop();

    void onProxyDestroy();

    void onProxyRestart();

    boolean onTouchEvent(MotionEvent event);
}
