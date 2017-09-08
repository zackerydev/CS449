package com.zgriesinger.umpirebuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "DataFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Switch audioSwitch = (Switch) findViewById(R.id.options_switch);
        SharedPreferences settingsPref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Boolean audioOpt = settingsPref.getBoolean("audioOpt", false);
        audioSwitch.setChecked(audioOpt);

        audioSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("audioOpt", audioSwitch.isChecked());
                    editor.apply();
            }
        });
    }


    Intent intent = getIntent();
}
