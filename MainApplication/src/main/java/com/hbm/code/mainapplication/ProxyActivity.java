package com.hbm.code.mainapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hbm.code.pluglibrary.PlugActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 介绍:承载插件的宿主
 * author:胡邦茂
 * CreateDate: 2018/7/3 17:26
 */
public class ProxyActivity extends AppCompatActivity {
    public static String ACTION_CLASS_NAME = "className";
    private String mClassName;
    private PlugActivity mPlugActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra(ACTION_CLASS_NAME);

        try {
            Class loadClass = getClassLoader().loadClass(mClassName);
            Constructor constructor = loadClass.getConstructor(new Class[]{});
            mPlugActivity = (PlugActivity) constructor.newInstance(new Object[]{});

            Bundle build = new Bundle();
            mPlugActivity.attach(this);

            //注：生命周期方法传递
            mPlugActivity.onCreate(build);

            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "插件加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public ClassLoader getClassLoader() {
        //注：插件APK 里面的ClassLoader
        return PlugManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        //注：插件APK 里面的Resources
        return PlugManager.getInstance().getResources();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPlugActivity.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPlugActivity.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlugActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlugActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlugActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlugActivity.onDestroy();
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void startActivity(Intent intent) {
        Intent newIntent = new Intent(this, ProxyActivity.class);
        newIntent.putExtra(ACTION_CLASS_NAME, intent.getStringExtra(ACTION_CLASS_NAME));
        super.startActivity(newIntent);
    }
}
