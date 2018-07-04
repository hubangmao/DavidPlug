package com.hbm.code.mainapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 介绍:  宿主 应用
 * author:胡邦茂
 * CreateDate: 2018/7/3 16:50
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlugManager.getInstance().setContext(this);
    }


    public void startPlug(View view) throws Exception {
        File file = new File(getExternalCacheDir(), "PlugApplication-debug.apk");
        Log.e(TAG, file.getAbsolutePath());
        InputStream open = getAssets().open("PlugApplication-debug.apk");
        writeBytesToFile(open, file);

        if (file.exists() && file.length() > 0) {
            PlugManager.getInstance().initApk(file.getAbsolutePath());

            Intent intent = new Intent(this, ProxyActivity.class);
            intent.putExtra(ProxyActivity.ACTION_CLASS_NAME, PlugManager.getInstance().mEntryActivityName);
            startActivity(intent);
        } else {
            Toast.makeText(this, "插件APK不存在", Toast.LENGTH_SHORT).show();
        }

    }


    //写入SD卡
    public void writeBytesToFile(InputStream is, File file) {
        FileOutputStream fos = null;

        try {
            byte[] data = new byte[2048];
            try {
                int length;
                fos = new FileOutputStream(file);
                while ((length = is.read(data)) > -1) {
                    fos.write(data, 0, length);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

