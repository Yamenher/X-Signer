package com.xapps.utility.xsigner;

import android.animation.*;
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
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
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
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.FirebaseApp;
import com.mursaat.extendedtextview.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialArcMotion;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import androidx.core.app.ActivityOptionsCompat;
import com.google.android.material.appbar.MaterialToolbar;

public class InfoActivity extends AppCompatActivity {
	
	private MaterialToolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String versionName = "";
	private String versionCode = "";
	
	private CollapsingToolbarLayout collapsingtoolbar;
	private NestedScrollView Scroller;
	private LinearLayout BG;
	private LinearLayout VersionsLinear;
	private LinearLayout XEngineVersionLinear;
	private LinearLayout ThanksLinear;
	private LinearLayout VersionLinear;
	private LinearLayout Dv1;
	private LinearLayout VersionCodeLinear;
	private LinearLayout Dv2;
	private LinearLayout ProductionPhaseLinear;
	private LinearLayout UpdateLinear;
	private TextView VersionNameText;
	private TextView VersionNameDetails;
	private TextView VersionCodeText;
	private TextView VersionCodeDetails;
	private TextView ProductioPhaseText;
	private TextView ProductionPhaseDetails;
	private TextView UpdateText;
	private ImageView UpdateIcon;
	private LinearLayout EngineNameLinear;
	private LinearLayout Dv3;
	private LinearLayout XEngineContainer;
	private TextView PoweredByTitle;
	private AnimatedGradientTextView XEngineLogoAnimated;
	private TextView XEngineVersionTitle;
	private TextView XEngineVersionDetail;
	private TextView SpecialThanksTitle;
	private LinearLayout User1;
	private LinearLayout Dv4;
	private LinearLayout User2;
	private LinearLayout Dv5;
	private LinearLayout User4;
	private LinearLayout Dv6;
	private LinearLayout User3;
	private CircleImageView Pic1;
	private LinearLayout NameAndWhy1;
	private TextView Username1;
	private TextView Role1;
	private CircleImageView Pic2;
	private LinearLayout NameAndWhy2;
	private TextView Username2;
	private TextView Role2;
	private CircleImageView Pic4;
	private LinearLayout NameAndWhy4;
	private TextView Username4;
	private TextView Role4;
	private CircleImageView Pic3;
	private LinearLayout NameAndWhy3;
	private TextView Username3;
	private TextView Role3;
	
	private Intent UserViewIntent = new Intent();
	
    	@Override
	protected void onCreate(Bundle _savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setAllowEnterTransitionOverlap(true);
        findViewById(android.R.id.content).setTransitionName("transition");
        MaterialSharedAxis enterTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, true);
        enterTransition.addTarget(android.R.id.content);
        getWindow().setEnterTransition(enterTransition);
        MaterialSharedAxis returnTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, false);
        returnTransition.addTarget(android.R.id.content);
        getWindow().setReturnTransition(returnTransition);
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.info);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		collapsingtoolbar = findViewById(R.id.collapsingtoolbar);
		Scroller = findViewById(R.id.Scroller);
		BG = findViewById(R.id.BG);
		VersionsLinear = findViewById(R.id.VersionsLinear);
		XEngineVersionLinear = findViewById(R.id.XEngineVersionLinear);
		ThanksLinear = findViewById(R.id.ThanksLinear);
		VersionLinear = findViewById(R.id.VersionLinear);
		Dv1 = findViewById(R.id.Dv1);
		VersionCodeLinear = findViewById(R.id.VersionCodeLinear);
		Dv2 = findViewById(R.id.Dv2);
		ProductionPhaseLinear = findViewById(R.id.ProductionPhaseLinear);
		UpdateLinear = findViewById(R.id.UpdateLinear);
		VersionNameText = findViewById(R.id.VersionNameText);
		VersionNameDetails = findViewById(R.id.VersionNameDetails);
		VersionCodeText = findViewById(R.id.VersionCodeText);
		VersionCodeDetails = findViewById(R.id.VersionCodeDetails);
		ProductioPhaseText = findViewById(R.id.ProductioPhaseText);
		ProductionPhaseDetails = findViewById(R.id.ProductionPhaseDetails);
		UpdateText = findViewById(R.id.UpdateText);
		UpdateIcon = findViewById(R.id.UpdateIcon);
		EngineNameLinear = findViewById(R.id.EngineNameLinear);
		Dv3 = findViewById(R.id.Dv3);
		XEngineContainer = findViewById(R.id.XEngineContainer);
		PoweredByTitle = findViewById(R.id.PoweredByTitle);
		XEngineLogoAnimated = findViewById(R.id.XEngineLogoAnimated);
		XEngineVersionTitle = findViewById(R.id.XEngineVersionTitle);
		XEngineVersionDetail = findViewById(R.id.XEngineVersionDetail);
		SpecialThanksTitle = findViewById(R.id.SpecialThanksTitle);
		User1 = findViewById(R.id.User1);
		Dv4 = findViewById(R.id.Dv4);
		User2 = findViewById(R.id.User2);
		Dv5 = findViewById(R.id.Dv5);
		User4 = findViewById(R.id.User4);
		Dv6 = findViewById(R.id.Dv6);
		User3 = findViewById(R.id.User3);
		Pic1 = findViewById(R.id.Pic1);
		NameAndWhy1 = findViewById(R.id.NameAndWhy1);
		Username1 = findViewById(R.id.Username1);
		Role1 = findViewById(R.id.Role1);
		Pic2 = findViewById(R.id.Pic2);
		NameAndWhy2 = findViewById(R.id.NameAndWhy2);
		Username2 = findViewById(R.id.Username2);
		Role2 = findViewById(R.id.Role2);
		Pic4 = findViewById(R.id.Pic4);
		NameAndWhy4 = findViewById(R.id.NameAndWhy4);
		Username4 = findViewById(R.id.Username4);
		Role4 = findViewById(R.id.Role4);
		Pic3 = findViewById(R.id.Pic3);
		NameAndWhy3 = findViewById(R.id.NameAndWhy3);
		Username3 = findViewById(R.id.Username3);
		Role3 = findViewById(R.id.Role3);
		
		UpdateLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (false) {
					_MakeTransition();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "It's a nightly bruh, don't expect everything in one update");
				}
			}
		});
	}
	
	private void initializeLogic() {
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		
		try {
			android.content.pm.PackageInfo packageInfo = InfoActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
			versionName = packageInfo.versionName;
			versionCode = Integer.toString(packageInfo.versionCode);
		} catch (android.content.pm.PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		
		_toolbar.getNavigationIcon().setColorFilter(getColor(R.color.colorTextMain), PorterDuff.Mode.SRC_IN);
		UpdateIcon.setColorFilter(getColor(R.color.colorTextMain), PorterDuff.Mode.SRC_IN);
		
		_toolbar.setTitleCentered(true);
		collapsingtoolbar.setTitle("About X-Signer");
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/product_sans.ttf"); 
		collapsingtoolbar.setCollapsedTitleTypeface(tf); 
		collapsingtoolbar.setExpandedTitleTypeface(tf); 
		int nightModeMask = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		if (nightModeMask == android.content.res.Configuration.UI_MODE_NIGHT_YES) {		
					collapsingtoolbar.setCollapsedTitleTextColor(0xFFFFFFFF);
			collapsingtoolbar.setExpandedTitleColor(0xFFFFFFFF);
			_MakeRipple(VersionCodeLinear, 0, 0, "#1F2D38", "#3D4952");
			_MakeRipple(ProductionPhaseLinear, 0, 0, "#1F2D38", "#3D4952");
			_MakeRipple(User1, 0, 0, "#1F2D38", "#3D4952");
			_MakeRipple(User2, 0, 0, "#1F2D38", "#3D4952");
			_MakeRipple(User4, 0, 0, "#1F2D38", "#3D4952");
		} else {
					collapsingtoolbar.setCollapsedTitleTextColor(0xFF000000);
			collapsingtoolbar.setExpandedTitleColor(0xFF000000);
			_MakeRipple(VersionCodeLinear, 0, 0, "#E9F2FB", "#CFD7DA");
			_MakeRipple(ProductionPhaseLinear, 0, 0, "#E9F2FB", "#CFD7DA");
			_MakeRipple(User1, 0, 0, "#E9F2FB", "#CFD7DA");
			_MakeRipple(User2, 0, 0, "#E9F2FB", "#CFD7DA");
			_MakeRipple(User4, 0, 0, "#E9F2FB", "#CFD7DA");
		}
		_app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			            @Override
			            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
				                int totalScrollRange = appBarLayout.getTotalScrollRange();
				                float scrollPercentage = Math.abs(verticalOffset) / (float) totalScrollRange;
				
				ArgbEvaluator argbEvaluator = new ArgbEvaluator();                
				                int toolbarColor = (int) argbEvaluator.evaluate(scrollPercentage, getColor(R.color.colorPrimaryDark), getColor(R.color.colorToolbarLifted));                
				_toolbar.setBackgroundColor(toolbarColor);
				collapsingtoolbar.setBackgroundColor(toolbarColor);
				collapsingtoolbar.setContentScrimColor(toolbarColor);
				            }
			        });
		int navigationBarHeight = 0;
		int statusBarHeight= 0;
		int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (r1 > 0) {
			    navigationBarHeight = getResources().getDimensionPixelSize(r1);
		}
		int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (r2 > 0) {
			    statusBarHeight = getResources().getDimensionPixelSize(r2);
		}
		_SetMargins(_toolbar, 0, statusBarHeight, 0, 0);
		_SetMargins(Scroller, 0, 0, 0, navigationBarHeight);
		VersionNameDetails.setText(versionName);
		VersionCodeDetails.setText(versionCode);
		VersionLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		ProductionPhaseLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		VersionCodeLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		XEngineContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		XEngineVersionLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		User1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				UserViewIntent.setAction(Intent.ACTION_VIEW);
				UserViewIntent.setData(Uri.parse("https://t.me/paxsenix0"));
				startActivity(UserViewIntent);
			}
		});
		User2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				UserViewIntent.setAction(Intent.ACTION_VIEW);
				UserViewIntent.setData(Uri.parse("https://t.me/NEOAPPS_MUSIC"));
				startActivity(UserViewIntent);
			}
		});
		User3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				UserViewIntent.setAction(Intent.ACTION_VIEW);
				UserViewIntent.setData(Uri.parse("https://t.me/PrakharDoneria"));
				startActivity(UserViewIntent);
			}
		});
		User4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				UserViewIntent.setAction(Intent.ACTION_VIEW);
				UserViewIntent.setData(Uri.parse("https://t.me/trindadedev"));
				startActivity(UserViewIntent);
			}
		});
	}
	
	public void _CommandBlocks() {
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
	
	
	public void _MakeRipple(final View _view, final double _shadow, final double _radius, final String _color, final String _ripple) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_radius);
		_view.setElevation((int)_shadow);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_ripple)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_view.setClickable(true);
		_view.setBackground(ripdrb);
	}
	
	
	public void _MakeTransition() {
		Intent intent = new Intent(this, UpdateActivity.class);
		intent.putExtra("version", VersionNameDetails.getText().toString());
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
		    this, UpdateLinear, "transition2");
		startActivity(intent, options.toBundle());
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