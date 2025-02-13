package com.xapps.utility.xsigner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.view.*;
import android.widget.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import eightbitlab.com.blurview.BlurView;
import com.xapps.utility.xsigner.BlurUtils;
import androidx.transition.TransitionManager;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.Context;

public class PrefsActivity extends AppCompatActivity {

    private static SharedPreferences sharedPreferences;
    private static boolean isAuto;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("XSignerAppPrefs", Context.MODE_PRIVATE);
        setThemeMode(getThemeMode());
        isAuto = isAutoModeEnabled(this);
    }

    public String getThemeMode() {
        String mode = sharedPreferences.getString("ThemeMode", "auto");
        return mode;
    }
    
    public void setThemeMode(String theme) {
        sharedPreferences.edit().putString("ThemeMode", theme).apply();
        switch (theme) {
            case "light" :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "dark" :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "auto" :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }
                
    }

    public String getSystemThemeMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                return "dark";
            case Configuration.UI_MODE_NIGHT_NO:
                return "light";
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return "auto";
        }
        return "";
        
    }

    public String getSystemDesiredMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                return "light";
            case Configuration.UI_MODE_NIGHT_NO:
                return "dark";
        }
        return "";
    }

    public String getSystemTargetMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                return "dark";
            case Configuration.UI_MODE_NIGHT_NO:
                return "light";
        }
        return "";
    }

    public static boolean isAutoModeEnabled(Context context) {
        return sharedPreferences.getString("ThemeMode", "auto").equals("auto");
    }

    public void setDynamicColorsOn(boolean z) {
        sharedPreferences.edit().putBoolean("isDynamicColorsEnabled", z).apply();
    }

    public boolean getDynamicColorsState() {
        return sharedPreferences.getBoolean("isDynamicColorsEnabled", false);
    }
}
