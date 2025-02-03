package com.xapps.utility.xsigner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.FirebaseApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import java.util.Timer;
import java.util.TimerTask;
import android.os.HandlerThread;
import androidx.annotation.NonNull;

public class SplashScreenActivity extends AppCompatActivity {
	
	private boolean ShowSplash = true;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
	private Intent StartIntent = new Intent();
    private boolean isLoggingIn = false;
    private Runnable runnable;
    private HandlerThread handlerThread;
    private Handler handler;
    private FirebaseUser user;
    private final Context context = this;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
        final SplashScreen  splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> ShowSplash );
		super.onCreate(_savedInstanceState);
        FirebaseApp.initializeApp(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        handlerThread = new HandlerThread("SignInThread");
        handlerThread.start();
		handler = new Handler(handlerThread.getLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    ShowSplash = false;
                    handler.removeCallbacks(runnable);
                    StartIntent.setClass(SplashScreenActivity.this, MainActivity.class);
                    startActivity(StartIntent);
                    finish();
                } else {
                    if (!isLoggingIn) {
                        isLoggingIn = true;
                        auth.signInWithEmailAndPassword("xsignerapp@default.com", "E0D9FC51A786CD3BAD5225270258B566D7345F30")
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    handler.removeCallbacks(runnable);
                                    ShowSplash = false;
                                    user = auth.getCurrentUser();
                                    handlerThread.quitSafely();
                                    StartIntent.setClass(SplashScreenActivity.this, MainActivity.class);
                                    startActivity(StartIntent);
                                    finish();    
                                } else {
                                    isLoggingIn = false;
                                    handler.removeCallbacks(runnable);
                                    handlerThread.quitSafely();
                                }
                            }       
                        });
                    }
                    handler.postDelayed(this, 50);
                }
            }    
        };
        handler.post(runnable);
	}
}