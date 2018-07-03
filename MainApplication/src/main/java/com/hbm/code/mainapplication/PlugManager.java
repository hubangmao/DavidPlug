package com.hbm.code.mainapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 介绍: 插件管理
 * author:胡邦茂
 * CreateDate:2018年07月03日 17:24
 */
public class PlugManager {
    private static final PlugManager ourInstance = new PlugManager();


    private Context mContext;
    private DexClassLoader mDexClassLoader;
    private Resources mResources;

    public static PlugManager getInstance() {
        return ourInstance;
    }

    private PlugManager() {

    }

    public PlugManager setContext(Context context) {
        mContext = context.getApplicationContext();
        return this;
    }

    public PlugManager initApk(String path) {

        //加载Dex
        File dexOutFile = mContext.getDir("dex", Context.MODE_PRIVATE);
        mDexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath(), null, mContext.getClassLoader());


        //加载Plug资源
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, path);
            mResources = new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return this;
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }
}
