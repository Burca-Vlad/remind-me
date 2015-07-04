package com.app.helper.vladburca.helperapp.presenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.app.helper.vladburca.helperapp.R;
import com.app.helper.vladburca.helperapp.controller.ApplicationSettingsActivityController;

public class ApplicationSettingsActivity extends Activity {

    private final String TAG = "ApplicationSettingsActivity";
    private static final String appNameKey = "appNameKey";

    private String appName;
    private ApplicationSettingsActivityController applicationSettingsActivityController;

    private ImageView appIcon;
    private Switch appMonitor;
    private Button ignoreApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_settings);

        this.appName = getIntent().getStringExtra(appNameKey);
        getActionBar().setTitle(appName + " " + getString(R.string.application_settings_title_suffix));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        this.applicationSettingsActivityController = new ApplicationSettingsActivityController(this, appName);
        this.appIcon = (ImageView) findViewById(R.id.app_icon_large);
        this.appMonitor = (Switch) findViewById(R.id.switch_monitor);
        this.ignoreApp = (Button) findViewById(R.id.ignore_button);

        applicationSettingsActivityController.preloadData(appIcon, appMonitor);

        this.appMonitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                applicationSettingsActivityController.updateAppMonitorisation(b);
            }
        });

        this.ignoreApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applicationSettingsActivityController.ignoreApplication();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_application_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
