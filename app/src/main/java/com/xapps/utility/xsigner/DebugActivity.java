package com.xapps.utility.xsigner;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.sun.security.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.*;
import com.google.firebase.FirebaseApp;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.animation.ValueAnimator;
import com.google.android.material.internal.EdgeToEdgeUtils;
import android.animation.ArgbEvaluator;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.appbar.MaterialToolbar;

public class DebugActivity extends AppCompatActivity {
	
	private MaterialToolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private int statusBarHeight;
	private int navigationBarHeight;
	private boolean isLifted = false;
	
	private NestedScrollView Scroller;
	private LinearLayout linear1;
	private TextView ErrorText;
	private MaterialButton EndButton;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.debug);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		Scroller = findViewById(R.id.Scroller);
		linear1 = findViewById(R.id.linear1);
		ErrorText = findViewById(R.id.ErrorText);
		EndButton = findViewById(R.id.EndButton);
		
		//ScrollChange2
		Scroller.setOnScrollChangeListener(new ScrollView.OnScrollChangeListener() {
			@Override
			public void onScrollChange(View v, int _scrollX, int _scrollY, int _oldScrollX, int _oldScrollY) {
				if (_scrollY == 0) {
					if (isLifted) {
						animateColorChange(getColor(R.color.colorToolbarLifted), getColor(R.color.colorPrimaryDark));
						isLifted = false;
					}
				}
				else {
					if (!isLifted) {
						animateColorChange(getColor(R.color.colorPrimaryDark), getColor(R.color.colorToolbarLifted));
						isLifted = true;
					}
				}
			}
		});
		
		EndButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("X-Signer Crashed");
		Scroller.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(20), getColor(R.color.colorToolbarLifted)));
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		ErrorText.setTextIsSelectable(true);
		_toolbar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_app_bar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_toolbar.setTitleTextColor(getColor(R.color.colorTextMain));
		
		_toolbar.setTitleCentered(true);
		ErrorText.setText(getIntent().getStringExtra("error"));
		navigationBarHeight = 0;
		statusBarHeight= 0;
		int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (r1 > 0) {
			    navigationBarHeight = getResources().getDimensionPixelSize(r1);
		}
		int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (r2 > 0) {
			    statusBarHeight = getResources().getDimensionPixelSize(r2);
		}
		EndButton.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
			            @Override
			            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
				                navigationBarHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
				
				                 ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) EndButton.getLayoutParams();
				                params.bottomMargin = navigationBarHeight;
				                EndButton.setLayoutParams(params);
				
				                
				                return insets;
				            }
			        });
		
		_toolbar.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
			            @Override
			            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
				
				statusBarHeight = insets.getInsets(WindowInsets.Type.statusBars()).top;
				
				                 ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) _toolbar.getLayoutParams();
				                params2.topMargin = statusBarHeight;
				                _toolbar.setLayoutParams(params2);
				
				                
				                return insets;
				            }
			        });
	}
	
	public void _SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
		
		   layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
		_layout.setLayoutParams(layoutParams);
	}
	
	
	public double _DpToPx(final double _dp) {
		  Resources resources = this.getResources();
		    DisplayMetrics metrics = resources.getDisplayMetrics();
		    return (double)Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}
	
	
	private void animateColorChange(int startColor, int endColor) {
		    ValueAnimator colorAnimation = ValueAnimator.ofArgb(startColor, endColor);
		    colorAnimation.setDuration(150);
		    colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			        @Override
			        public void onAnimationUpdate(ValueAnimator animator) {
				            _app_bar.setBackgroundColor((int)animator.getAnimatedValue());
				_toolbar.setBackgroundColor((int) animator.getAnimatedValue());
				        }
			    });
		    colorAnimation.start();
	}
	
	{
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