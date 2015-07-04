package com.app.helper.vladburca.helperapp.controller;

import android.app.Activity;
import android.util.Log;
import android.widget.Switch;

import com.app.helper.vladburca.helperapp.Utils.PreferenceUtils;

/**
 * Created by Vlad on 7/4/2015.
 */
public class SettingsActivityController {

    public static SettingsActivityController instance;

    private Activity activity;
    private PreferenceUtils preferenceUtils;

    private final String TAG = "SettingsActivityController";


    public SettingsActivityController(Activity activity){
        this.activity = activity;
        this.preferenceUtils = new PreferenceUtils(activity);
    }

    public static SettingsActivityController getInstance(Activity activity) {
        instance = new SettingsActivityController(activity);
        return instance;
    }

    public void preloadUserValues(Switch complexNotification, Switch persistentNotification){
        persistentNotification.setChecked(preferenceUtils.isPersistentSelected());
        complexNotification.setChecked(preferenceUtils.isComplexSelected());
    }

    public void setShowPersistentNotification(boolean showPersistentNotification){
        preferenceUtils.setPersistentValue(showPersistentNotification);
    }

    public void setShowComplexNotification(boolean showComplexNotification){
        preferenceUtils.setComplexValue(showComplexNotification);
    }

    public void resetNotificationsBehaviour(){
        preferenceUtils.setComplexValue(false);

    }
}
