package com.pvanshah.sjsuquizapplication.student.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by avinash on 7/17/17.
 */

public class App extends MultiDexApplication {

    private static App instance;

    private static Context mContext;
    /**
     * set context to instance
     */
    public App() {
        instance = this;
    }

    /**
     * call from anywhere returns application context
     *
     * @return Application context
     */
    public static App get() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public  void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
