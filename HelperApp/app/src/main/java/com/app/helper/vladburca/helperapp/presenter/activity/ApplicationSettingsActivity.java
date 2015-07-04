package com.app.helper.vladburca.helperapp.presenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.helper.vladburca.helperapp.R;
import com.app.helper.vladburca.helperapp.controller.SettingsActivityController;

public class ApplicationSettingsActivity extends Activity {

    private static String TAG = "ApplicationSettingsActivity";

    private SettingsActivityController settingsActivityController;

    private Switch persistentNotificationSwitch;
    private Switch complexNotificationSwitch;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_settings);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        this.settingsActivityController = SettingsActivityController.getInstance(this);
        this.persistentNotificationSwitch = (Switch) findViewById(R.id.switch_persistent);
        this.complexNotificationSwitch = (Switch) findViewById(R.id.switch_complex);
        this.resetButton = (Button) findViewById(R.id.button_reset);

        this.settingsActivityController.preloadUserValues(complexNotificationSwitch, persistentNotificationSwitch);
        this.persistentNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i(TAG, "Value: " + b);
                settingsActivityController.setShowPersistentNotification(b);
            }
        });

        this.complexNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsActivityController.setShowComplexNotification(b);
            }
        });

        this.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsActivityController.resetNotificationsBehaviour();
                settingsActivityController.preloadUserValues(complexNotificationSwitch, persistentNotificationSwitch);
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
