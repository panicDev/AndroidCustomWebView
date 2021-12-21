package com.example.administrator.myapplication;

import android.app.Application;

import com.paniclabs.customwebview.InitSystemWebViewUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InitSystemWebViewUtils.webviewSetPath(getApplicationContext());
    }
}
