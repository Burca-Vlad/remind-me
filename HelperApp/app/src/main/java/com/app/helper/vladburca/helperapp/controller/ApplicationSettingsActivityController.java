package com.app.helper.vladburca.helperapp.controller;

import android.app.Activity;
import android.content.pm.ResolveInfo;
import android.widget.ImageView;
import android.widget.Switch;

import com.app.helper.vladburca.helperapp.Utils.ApplicationUtils;
import com.app.helper.vladburca.helperapp.Utils.PreferenceUtils;

/**
 * Created by Vlad on 7/4/2015.
 */
public class ApplicationSettingsActivityController {

    public static ApplicationSettingsActivityController instance;

    private Activity activity;
    private PreferenceUtils preferenceUtils;
    private ApplicationUtils applicationUtils;
    private ResolveInfo currentApplicationInfo;
    private String appName;

    private final String TAG = "SettingsActivityController";


    public ApplicationSettingsActivityController(Activity activity, String appName){
        this.activity = activity;
        this.preferenceUtils = new PreferenceUtils(activity);
        this.applicationUtils = new ApplicationUtils();
        this.appName = appName;
        getApplicationData(appName);
    }

    public static ApplicationSettingsActivityController getInstance(Activity activity, String appName) {
        instance = new ApplicationSettingsActivityController(activity, appName);
        return instance;
    }

    private void getApplicationData(String appName){
        this.currentApplicationInfo = applicationUtils.getApplicationByName(appName, activity);
    }

    public void preloadData(ImageView appIcon, Switch appMonitor){
        setAppIcon(appIcon);
        appMonitor.setChecked(preferenceUtils.isApplicationMonitored(appName));
    }

    private void setAppIcon(ImageView appIcon){
        appIcon.setImageDrawable(currentApplicationInfo.activityInfo.loadIcon(activity.getPackageManager()));
    }

    public void updateAppMonitorisation(boolean isMonitored){
        preferenceUtils.setApplicationSelected(appName, isMonitored);
    }

    public void ignoreApplication(){
        preferenceUtils.setShouldAppBeIgnored(appName, true);
    }
}
