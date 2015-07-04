package com.app.helper.vladburca.helperapp.presenter.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.app.helper.vladburca.helperapp.R;
import com.app.helper.vladburca.helperapp.controller.HomeActivityController;
import com.app.helper.vladburca.helperapp.model.PackageInfoRowItem;
import com.app.helper.vladburca.helperapp.presenter.adapter.HomeActivityAdapter;
import com.app.helper.vladburca.helperapp.view.HotFixRecycleView;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends Activity {

    private final String TAG = "HomeActivity";

    private HomeActivityController homeActivityController;
    private HomeActivityAdapter appListAdapter;
    private ArrayList<PackageInfoRowItem> items;

    private HotFixRecycleView appList;
    private EditText appsSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getActionBar().setIcon(R.drawable.remind_me_icon);
        this.appList = (HotFixRecycleView) findViewById(R.id.app_list);
        this.appsSearch = (EditText) findViewById(R.id.app_search);


        homeActivityController = HomeActivityController.getInstance(this);
        homeActivityController.getApplicationsList();
        setListOfApps();

        items = homeActivityController.getApps();
        this.appsSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Nothing to do
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<PackageInfoRowItem> searchItems = new ArrayList<PackageInfoRowItem>();
                for (PackageInfoRowItem packageInfoRowItem : items){
                    if(packageInfoRowItem.getName().contains(s)){
                        searchItems.add(packageInfoRowItem);
                    }
                }
                if(searchItems.size() > 0 || s.length() > 0){
                    appListAdapter.setItems(searchItems);
                    appListAdapter.notifyDataSetChanged();
                }else{
                    appListAdapter.setItems(items);
                    appListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Nothing to do
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ApplicationSettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setListOfApps(){
        this.appListAdapter = new HomeActivityAdapter(this, homeActivityController.getApplicationsList());
        HotFixRecycleView.LayoutManager pinListLayoutManager = new LinearLayoutManager(this);
        this.appList.setLayoutManager(pinListLayoutManager);
        this.appList.setAdapter(appListAdapter);
    }
}
