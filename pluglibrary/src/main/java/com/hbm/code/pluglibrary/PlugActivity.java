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
    public String ACTION_CLASS_NAME = "className";

    void attach(Activity att);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onRestart();

    boolean onTouchEvent(MotionEvent event);
}
