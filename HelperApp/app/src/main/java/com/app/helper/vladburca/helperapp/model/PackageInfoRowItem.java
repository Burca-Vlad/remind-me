package com.app.helper.vladburca.helperapp.model;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.helper.vladburca.helperapp.R;
import com.app.helper.vladburca.helperapp.view.HotFixRecycleView;

/**
 * Created by vlad.burca on 1/22/2015.
 */
public class PackageInfoRowItem {

    private String name;
    private Drawable icon;;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public static class ViewHolder extends HotFixRecycleView.ViewHolder implements View.OnClickListener{

        private final String TAG = "PackageInfoRowItem.ViewHolder";


        public PackageInfoRowItem item;
        public Activity activity;

        public ImageView icon;
        public TextView appName;
        public TextView appPriority;

        public void setItem(PackageInfoRowItem item) {
            this.item = item;
        }

        public void setContext(Activity activity) {
            this.activity = activity;
        }

        public ViewHolder(View row) {
            super(row);
            row.setOnClickListener(this);
            this.icon = (ImageView) row.findViewById(R.id.app_icon);
            this.appName = (TextView) row.findViewById(R.id.app_name);
            this.appPriority = (TextView) row.findViewById(R.id.app_priority);
        }


        @Override
        public void onClick(View v) {
            //Doo shit here
        }
    }
}
