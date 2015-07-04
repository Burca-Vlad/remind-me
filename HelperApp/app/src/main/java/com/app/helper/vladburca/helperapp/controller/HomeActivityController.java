package com.app.helper.vladburca.helperapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.app.helper.vladburca.helperapp.Utils.ApplicationUtils;
import com.app.helper.vladburca.helperapp.Utils.PreferenceUtils;
import com.app.helper.vladburca.helperapp.model.PackageInfoRowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad.burca on 1/22/2015.
 */
public class HomeActivityController {

    public static HomeActivityController instance;

    private Activity activity;
    private ArrayList<PackageInfoRowItem> apps;

    public ArrayList<PackageInfoRowItem> getApps() {
        return apps;
    }

    private final String TAG = "HomeActivityController";


    public HomeActivityController(Activity activity){
        this.activity = activity;
    }

    public static HomeActivityController getInstance(Activity activity) {
        instance = new HomeActivityController(activity);
        return instance;
    }

    public ArrayList<PackageInfoRowItem> getApplicationsList(){
        //get a list of installed apps.
        apps = new ArrayList<PackageInfoRowItem>();

        final PackageManager pms = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PreferenceUtils preferenceUtils = new PreferenceUtils(activity);
        ApplicationUtils applicationUtils = new ApplicationUtils();
        List<ResolveInfo> appsa = applicationUtils.getUserApplications(activity);
        for (ResolveInfo resolveInfo : appsa){
            Log.i(TAG, resolveInfo.toString());
            if(resolveInfo.activityInfo.loadLabel(activity.getPackageManager()).toString() != null && resolveInfo.loadIcon(activity.getPackageManager()) != null){
                Log.i(TAG, resolveInfo.activityInfo.packageName);
                String appName = resolveInfo.activityInfo.loadLabel(activity.getPackageManager()).toString();
                PackageInfoRowItem item = new PackageInfoRowItem();
                item.setIcon(resolveInfo.activityInfo.loadIcon(activity.getPackageManager()));
                item.setName(appName);
                Log.i(TAG,"value " + preferenceUtils.isApplicationMontitored(appName));
                item.setIsMonitored(preferenceUtils.isApplicationMontitored(appName));
                item.setActivityInfo(resolveInfo.activityInfo);
                apps.add(item);
            }
        }
        return apps;
    }
}
