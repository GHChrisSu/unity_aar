package com.baize.unity_aar.activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.baize.unity_aar.AppContext;
import com.baize.unity_aar.utils.CustomActivityManager;
import com.baize.unity_aar.utils.PageSwitchUtils;
import com.baize.unity_aar.R;

/**
 * Created by bz on 2017/11/22.
 */

public class AndroidActivity extends Activity {

    private InnerRecevier innerReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_activity);
        //创建广播
        innerReceiver = new InnerRecevier();
        //动态注册广播
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        //启动广播
        registerReceiver(innerReceiver, intentFilter);
        //close the start splash
        AppContext.getInstance().HideSplash();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomActivityManager.getInstance().setTopActivity(this);
    }

    public void toUnityActivity(View view){
        PageSwitchUtils.GoActivityNoParams(AndroidActivity.this, MainActivity.class);
    }

    class InnerRecevier extends BroadcastReceiver {

        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null) {
                    if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                        Log.d("InnerRecevier", "onReceive: " + CustomActivityManager.getInstance().getTopActivity().getComponentName());
                        CustomActivityManager.getInstance().setRecoverActivity(CustomActivityManager.getInstance().getTopActivity());
                    }
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            CustomActivityManager.getInstance().exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
