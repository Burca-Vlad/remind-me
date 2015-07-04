package com.app.helper.vladburca.helperapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vlad on 7/4/2015.
 */
public class PreferenceUtils {

    //Preference Keys
    private static String persistentKey = "persistentKey";
    private static String complexKey = "complexKey";

    private String prefKey = "com.app.helper.vladburca.helperapp";
    private SharedPreferences sharedPreferences;

    public PreferenceUtils(Activity activity){
        sharedPreferences = activity.getSharedPreferences(prefKey, Context.MODE_APPEND);
    }

    public void setPersistentValue(boolean persistentValue){
        sharedPreferences.edit().putBoolean(persistentKey, persistentValue).commit();
    }

    public boolean isPersistentSelected(){
        return sharedPreferences.getBoolean(persistentKey, false);
    }

    public void setComplexValue(boolean complexValue){
        sharedPreferences.edit().putBoolean(complexKey, complexValue).commit();
    }

    public boolean isComplexSelected(){
        return sharedPreferences.getBoolean(complexKey, false);
    }

    public boolean isApplicationMontitored(String appName){
        return sharedPreferences.getBoolean(appName, true);
    }

    public void setApplicationSelected(String appName, boolean appSelection){
        sharedPreferences.edit().putBoolean(appName, appSelection).commit();
    }
}
