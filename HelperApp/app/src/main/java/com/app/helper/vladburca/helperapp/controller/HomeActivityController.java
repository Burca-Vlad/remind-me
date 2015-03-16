package com.app.helper.vladburca.helperapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.app.helper.vladburca.helperapp.model.PackageInfoRowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad.burca on 1/22/2015.
 */
public class HomeActivityController {

    public volatile static HomeActivityController instance;

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
        if (instance == null) {
            synchronized (HomeActivityController.class) {
                instance = new HomeActivityController(activity);
            }
        }
        return instance;
    }

    public ArrayList<PackageInfoRowItem> getApplicationsList(){
        final PackageManager pm = activity.getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        apps = new ArrayList<PackageInfoRowItem>();

        final PackageManager pms = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appsa = pms.queryIntentActivities(intent, PackageManager.GET_META_DATA);
        for (ResolveInfo resolveInfo : appsa){
            Log.i(TAG, resolveInfo.toString());
            if(resolveInfo.activityInfo.loadLabel(activity.getPackageManager()).toString() != null && resolveInfo.loadIcon(activity.getPackageManager()) != null){
                PackageInfoRowItem item = new PackageInfoRowItem();
                item.setIcon(resolveInfo.activityInfo.loadIcon(activity.getPackageManager()));
                item.setName(resolveInfo.activityInfo.loadLabel(activity.getPackageManager()).toString());
                apps.add(item);
            }
        }
        //First version of app info, usefull for retrieving all apps and ifo on them
        /*for (ApplicationInfo packageInfo : packages) {
            if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1 && packageInfo.packageName != null){
                if(packageInfo.loadIcon(activity.getPackageManager()) != null){
                    PackageInfoRowItem item = new PackageInfoRowItem();
                    item.setIcon(packageInfo.loadIcon(activity.getPackageManager()));
                    if(packageInfo.loadLabel(activity.getPackageManager()).toString() != null && !packageInfo.loadLabel(activity.getPackageManager()).toString().contains("com.")){
                        item.setName(packageInfo.loadLabel(activity.getPackageManager()).toString());
                        apps.add(item);
                    }
                }
            }

        }*/
        Log.i(TAG, apps.size() + " " + appsa.size());
        return apps;
    }
}
