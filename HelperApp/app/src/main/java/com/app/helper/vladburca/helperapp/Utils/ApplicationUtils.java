package com.app.helper.vladburca.helperapp.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * Created by Vlad on 7/4/2015.
 */
public class ApplicationUtils {

    public List<ResolveInfo> getUserApplications(Activity activity){
        final PackageManager pms = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return pms.queryIntentActivities(intent, PackageManager.GET_META_DATA);
    }

    public ResolveInfo getApplicationByName(String applicationName, Activity activity){
        final PackageManager pms = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> userApplications =  pms.queryIntentActivities(intent, PackageManager.GET_META_DATA);
        for(ResolveInfo resolveInfo : userApplications){
            if(resolveInfo.activityInfo.loadLabel(activity.getPackageManager()) != null && resolveInfo.loadIcon(activity.getPackageManager()) != null){
                String appName = resolveInfo.activityInfo.loadLabel(activity.getPackageManager()).toString();
                if(appName.equals(applicationName)){
                    return resolveInfo;
                }
            }
        }
        return null;
    }
}
