package com.app.helper.vladburca.helperapp.presenter.adapter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.helper.vladburca.helperapp.R;
import com.app.helper.vladburca.helperapp.model.PackageInfoRowItem;
import com.app.helper.vladburca.helperapp.view.HotFixRecycleView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by vlad.burca on 1/22/2015.
 */
public class HomeActivityAdapter extends HotFixRecycleView.Adapter<PackageInfoRowItem.ViewHolder> {

    private final String TAG = "HomeActivityAdapter";

    private ArrayList<PackageInfoRowItem> items;
    private Activity activity;

    public HomeActivityAdapter(Activity activity, ArrayList<PackageInfoRowItem> items){
        this.activity = activity;
        this.items = items;
    }

    public void setItems(ArrayList<PackageInfoRowItem> items) {
        this.items = items;
    }

    @Override
    public PackageInfoRowItem.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.i(TAG, "creating a list element");
        View view = LayoutInflater.from(activity).inflate(R.layout.package_info_row_item, viewGroup, false);

        PackageInfoRowItem.ViewHolder holder = (PackageInfoRowItem.ViewHolder) view.getTag();
        if (holder == null) {
            holder = new PackageInfoRowItem.ViewHolder(view);
            view.setTag(holder);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(PackageInfoRowItem.ViewHolder viewHolder, int i) {
        final PackageInfoRowItem packageInfoRowItem = items.get(i);
        viewHolder.setItem(packageInfoRowItem);
        viewHolder.setContext(activity);

        if(packageInfoRowItem.getIcon() != null){
            viewHolder.icon.setImageDrawable(packageInfoRowItem.getIcon());
        }else{
            viewHolder.icon.setImageResource(R.drawable.ic_launcher);
        }

        if(packageInfoRowItem.getName() != null){
            viewHolder.appName.setText(packageInfoRowItem.getName());
        }else{
            viewHolder.appName.setText(activity.getString(R.string.home_activity_app_name_placeholder));
        }
        if(packageInfoRowItem.isMonitored()){
            viewHolder.appPriority.setText(activity.getString(R.string.home_activity_app_is_monitored));
            viewHolder.appPriority.setTextColor(activity.getResources().getColor(R.color.monitored_green));
        }else{
            viewHolder.appPriority.setText(activity.getString(R.string.home_activity_app_is_not_monitored));
            viewHolder.appPriority.setTextColor(activity.getResources().getColor(R.color.not_monitored_red));
        }

        if (packageInfoRowItem.getActivityInfo() != null){
            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   startActivity(packageInfoRowItem.getActivityInfo());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void startActivity(ActivityInfo activityInfo){
        ComponentName name=new ComponentName(activityInfo.applicationInfo.packageName,
                activityInfo.name);
        Intent i=new Intent(Intent.ACTION_MAIN);

        i.addCategory(Intent.CATEGORY_LAUNCHER);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        i.setComponent(name);

        activity.startActivity(i);
    }
}
