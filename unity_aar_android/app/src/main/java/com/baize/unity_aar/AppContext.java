package com.baize.unity_aar;

import android.app.Application;

/**
 * Created by bz on 2017/11/23.
 */

public class AppContext extends Application {
    private static AppContext mAppcontext;

    public static AppContext getInstance() {
        return mAppcontext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppcontext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
