package com.xapps.utility.xsigner;

import android.animation.*;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.sun.security.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.core.*;
import androidx.core.ktx.*;
import androidx.core.splashscreen.*;
import androidx.emoji2.*;
import androidx.emoji2.viewsintegration.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.livedata.core.*;
import androidx.lifecycle.process.*;
import androidx.lifecycle.runtime.*;
import androidx.lifecycle.viewmodel.*;
import androidx.lifecycle.viewmodel.savedstate.*;
import androidx.profileinstaller.*;
import androidx.savedstate.*;
import androidx.startup.*;
import androidx.transition.*;
import com.google.android.material.*;
import com.google.android.material.button.*;
import com.google.android.material.snackbar.Snackbar;

import com.mursaat.extendedtextview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class WelcomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout _coordinator;
	private boolean IsInitialized = false;
	private boolean IsInternetWorking = false;
	private boolean isMetered = false;
	private boolean isSet = false;
	private boolean IsReady = false;
	private boolean IsAlreadyStarted = false;
	private boolean IsAlreadyStartedReverse = false;
	private String status = "";
	
	private TextView Title;
	private TextView TypeDesc;
	private LinearLayout MainContainer;
	private LinearProgressIndicator progress;
	private MaterialButton Button;
	private TextView Description;
	
	private Intent StartIntent = new Intent();
	
	private TimerTask SignInChecker;
	private ObjectAnimator FadeIn = new ObjectAnimator();
	private ObjectAnimator FadeOut = new ObjectAnimator();
	private com.google.android.material.snackbar.Snackbar SnackBar;
	private TimerTask InternetTimer;
	private TimerTask TimeOut;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.welcome);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_coordinator = findViewById(R.id._coordinator);
		Title = findViewById(R.id.Title);
		TypeDesc = findViewById(R.id.TypeDesc);
		MainContainer = findViewById(R.id.MainContainer);
		progress = findViewById(R.id.progress);
		Button = findViewById(R.id.Button);
		Description = findViewById(R.id.Description);
		
		Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				status = "";
				if (IsInitialized) {
					StartIntent.setClass(getApplicationContext(), MainActivity.class);
					startActivity(StartIntent);
					finish();
				}
				else {
					NetworkRequest networkRequest = new NetworkRequest.Builder()
					        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
					        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
					        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
					        .build();
					ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(ConnectivityManager.class);
					connectivityManager.requestNetwork(networkRequest, networkCallback);
					FadeOut.setTarget(Button);
					FadeOut.setPropertyName("alpha");
					FadeOut.setFloatValues((float)(0));
					FadeOut.setDuration((int)(300));
					FadeOut.start();
					TimeOut = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									try {
										SignInChecker.cancel();
									} catch (Exception e) {
										 
									}
									status = "FAIL";
									FadeOut.setTarget(progress);
									FadeOut.setPropertyName("alpha");
									FadeOut.setFloatValues((float)(0));
									FadeOut.setDuration((int)(300));
									FadeOut.start();
									View SnackBarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
									SnackBar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
									final LinearLayout SBG = (LinearLayout)
									SnackBarView.findViewById(R.id.BG);
									final TextView Message = (TextView)
									SnackBarView.findViewById(R.id.Message);
									final ImageView Icon = (ImageView)
									SnackBarView.findViewById(R.id.Icon);
									Icon.setImageResource(R.drawable.ic_error_white);
									Message.setText("Connection to the server timed out, please verify your internet and try again");
									SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
									Message.setTextColor(0xFFFFFFFF);
									SnackBar.getView().setBackgroundColor(Color.TRANSPARENT);
									Snackbar.SnackbarLayout SnackBarView2 = (Snackbar.SnackbarLayout) SnackBar.getView(); 
									SnackBarView2.addView(SnackBarView, 0);
									SnackBar.show();
								}
							});
						}
					};
					_timer.schedule(TimeOut, (int)(15000));
				}
			}
		});
		
		FadeIn.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationCancel(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator _param1) {
				
			}
		});
		
		FadeOut.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator _param1) {
				if (!IsInitialized) {
					if (status.equals("FAIL")) {
						FadeIn.setTarget(Button);
						progress.setVisibility(View.GONE);
						Button.setVisibility(View.VISIBLE);
						isSet = true;
					}
					else {
						FadeIn.setTarget(progress);
						progress.setVisibility(View.VISIBLE);
						Button.setVisibility(View.GONE);
						isSet = true;
					}
				}
				if (IsInitialized) {
					FadeIn.setTarget(Button);
					Button.setVisibility(View.VISIBLE);
					progress.setVisibility(View.GONE);
					isSet = true;
				}
				FadeIn.setPropertyName("alpha");
				FadeIn.setFloatValues((float)(1));
				FadeIn.setDuration((int)(300));
				FadeIn.start();
			}
			
			@Override
			public void onAnimationCancel(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		int color = getColor(R.color.colorPrimaryDark);
		_coordinator.setBackgroundColor(color);
		getWindow().setNavigationBarColor(color);
	}
	
	private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
		    @Override
		    public void onAvailable(@NonNull Network network) {
			        super.onAvailable(network);
			IsReady = true;
			IsInternetWorking= true;
			    }
		
		    @Override
		    public void onLost(@NonNull Network network) {
			        super.onLost(network);
			IsReady = true;
			IsInternetWorking = false;
			    }
		
		    @Override
		    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
			        super.onCapabilitiesChanged(network, networkCapabilities);
			        final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
			if (unmetered) {
				isMetered = false;
			} else {
				isMetered = true;
			}
			    }
	};
	
	{
		int nightModeMask = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		if (nightModeMask == android.content.res.Configuration.UI_MODE_NIGHT_YES) {		
					 
		} else {
					getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			InternetTimer.cancel();
		} catch (Exception e) {
			 
		}
	}
	public void _CommandBlocks() {
		
		
	}
	
	
	public double _DpToPx(final double _dp) {
		  Resources resources = this.getResources();
		    DisplayMetrics metrics = resources.getDisplayMetrics();
		    return (double)Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}