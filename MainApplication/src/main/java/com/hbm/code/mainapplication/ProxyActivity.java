package com.hbm.code.mainapplication;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 介绍:解析插件包
 * author:胡邦茂
 * CreateDate: 2018/7/3 17:26
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug_page);
        PlugManager.getInstance().setContext(this).initApk("");
    }

    @Override
    public ClassLoader getClassLoader() {
        return PlugManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PlugManager.getInstance().getResources();
    }

}
