package com.baize.unity_aar;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.unity3d.player.UnityPlayer;

/**
 * Created by bz on 2017/11/23.
 */

public class AppContext extends Application {
    private static AppContext mAppcontext;

    public static AppContext getInstance() {
        return mAppcontext;
    }

    public ImageView bgView = null;
    public UnityPlayer mUnityPlayer = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppcontext = this;
    }

    public void HideSplash(){
        Log.d("AppContext", "HideSplash");
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
            @Override
            public void run()
            {
                if(mUnityPlayer != null) mUnityPlayer.removeView(bgView);
                bgView=null;
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
