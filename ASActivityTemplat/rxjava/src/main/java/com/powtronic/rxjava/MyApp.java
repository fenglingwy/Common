package com.powtronic.rxjava;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by pp on 2017/3/10.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.init(this);
    }
}
