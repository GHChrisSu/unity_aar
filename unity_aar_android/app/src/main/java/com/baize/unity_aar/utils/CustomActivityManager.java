package com.baize.unity_aar.utils;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by bz on 2017/11/23.
 */

public class CustomActivityManager {

    private static CustomActivityManager customActivityManager = new CustomActivityManager();
    private WeakReference<Activity> topActivity;
    private WeakReference<Activity> recoverActivity;
    private ArrayList<Activity> activities;

    private CustomActivityManager() {
        activities = new ArrayList<>();
    }

    public static CustomActivityManager getInstance(){
        return customActivityManager;
    }

    public Activity getTopActivity() {
        if (topActivity!=null){
            return topActivity.get();
        }
        return null;
    }

    public void setTopActivity(Activity topActivity) {
        this.topActivity = new WeakReference<>(topActivity);
        if(!activities.contains(topActivity)) activities.add(topActivity);
    }

    public Activity getRecoverActivity() {
        if (recoverActivity!=null){
            return recoverActivity.get();
        }
        return null;
    }

    public void setRecoverActivity(Activity recoverActivity) {
        this.recoverActivity = new WeakReference<>(recoverActivity);
    }

    public void exit(){
        for (Activity a : activities){
            a.finish();
        }
    }
}