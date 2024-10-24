package com.xapps.utility.xsigner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
	
	private boolean ShowSplash = true;
	private Intent StartIntent = new Intent();
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
        final SplashScreen  splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> ShowSplash );
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.splash_screen);
		Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
                @Override
            public void run() {
                ShowSplash = false;
                StartIntent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(StartIntent);
                finish();
            }
        }, 700);
	}
	
}