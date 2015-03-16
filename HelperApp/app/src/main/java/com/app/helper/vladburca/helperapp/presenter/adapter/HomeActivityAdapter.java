package com.app.helper.vladburca.helperapp.presenter.adapter;

import android.app.Activity;
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
        PackageInfoRowItem packageInfoRowItem = items.get(i);
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

        viewHolder.appPriority.setText(activity.getString(R.string.home_activity_app_priority_placeholder));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
