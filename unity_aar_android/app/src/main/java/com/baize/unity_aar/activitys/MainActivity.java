package com.baize.unity_aar.activitys;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.baize.unity_aar.AppContext;
import com.baize.unity_aar.R;
import com.baize.unity_aar.utils.CustomActivityManager;
import com.baize.unity_aar.utils.PageSwitchUtils;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.getInstance().bgView=new ImageView(this);
        AppContext.getInstance().bgView.setBackgroundResource(R.mipmap.launcher_pic);
        mUnityPlayer.addView(AppContext.getInstance().bgView);
        AppContext.getInstance().mUnityPlayer = mUnityPlayer;
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomActivityManager.getInstance().setTopActivity(this);
        if (CustomActivityManager.getInstance().getRecoverActivity() != null && CustomActivityManager.getInstance().getRecoverActivity() != this){
            PageSwitchUtils.GoActivityNoParams(MainActivity.this, CustomActivityManager.getInstance().getRecoverActivity().getClass());
            CustomActivityManager.getInstance().setRecoverActivity(null);
        }
    }

    public void toAndroidActivity(){
        PageSwitchUtils.GoActivityNoParams(MainActivity.this, AndroidActivity.class);
    }

    // 显示Toast消息
    public void ShowToast(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    // 返回一个字符串（静态方法）
    public static String GetInformation()
    {
        return "This is a Plugin's content!";
    }
}