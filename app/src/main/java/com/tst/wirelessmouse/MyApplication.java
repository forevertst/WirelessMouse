package com.tst.wirelessmouse;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 在这里进行应用程序的初始化和配置
        // 例如，初始化全局变量
//        GlobalSettings.init(this);

        // 设置全局异常处理程序
//        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
