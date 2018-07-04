package com.hbm.code.plugapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hbm.code.pluglibrary.BasePlugActivity;


public class MainActivity extends BasePlugActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_to_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击跳转", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), Main2Activity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
