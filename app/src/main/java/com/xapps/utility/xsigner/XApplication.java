package com.xapps.utility.xsigner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.util.Log;
import com.xapps.utility.xsigner.XLogger;
import com.google.android.material.color.DynamicColors;

public class XApplication extends Application {

    private static Context mApplicationContext;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private static SharedPreferences prefs;

    public static Context getContext() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        prefs = getSharedPreferences("XSignerAppPrefs", Context.MODE_PRIVATE);
        mApplicationContext = getApplicationContext();
        if (prefs.getBoolean("isDynamicColorsEnabled", false) == true) {
            DynamicColors.applyToActivitiesIfAvailable(this);
        }
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(
            new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable throwable) {
                    Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("error", Log.getStackTraceString(throwable));
                    getApplicationContext().startActivity(intent);
                    XLogger.broadcastLog(Log.getStackTraceString(throwable));
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    uncaughtExceptionHandler.uncaughtException(thread, throwable);
                }
            });
        XLogger.startLogging();
        super.onCreate();
    }
}