package com.xapps.utility.xsigner;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.content.res.Resources;
import android.widget.*;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.*;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import android.animation.ValueAnimator;
import com.google.android.material.internal.EdgeToEdgeUtils;
import android.animation.ArgbEvaluator;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialArcMotion;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import androidx.core.app.ActivityOptionsCompat;
import com.google.android.material.appbar.MaterialToolbar;

public class FaqActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private boolean isLifted = false;
	private TextView FaqText;
	private NestedScrollView Scroller;
	private LinearLayout Container;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setAllowEnterTransitionOverlap(true);
        findViewById(android.R.id.content).setTransitionName("transition");
        MaterialSharedAxis enterTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, true);
        enterTransition.addTarget(android.R.id.content);
        enterTransition.setDuration(300L);
        getWindow().setEnterTransition(enterTransition);
        MaterialSharedAxis returnTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, false);
        returnTransition.addTarget(android.R.id.content);
        returnTransition.setDuration(300L);
        getWindow().setReturnTransition(returnTransition);
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.faq);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
        FaqText = findViewById(R.id.FaqText);
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
		Scroller = findViewById(R.id.Scroller);
		Container = findViewById(R.id.Container);
		
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
	}
	
	private void initializeLogic() {
		setTitle("FAQ");
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
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
		_coordinator.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_toolbar.getNavigationIcon().setColorFilter(getColor(R.color.colorTextMain), PorterDuff.Mode.SRC_IN);
		_toolbar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_app_bar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_toolbar.setTitleTextColor(getColor(R.color.colorTextMain));
        String keysInfo = "Q : What's the differnce between JKS, BKS, and PKCS12 keys? \n\nA : In the realm of cryptographic key management, three prominent keystore formats are commonly utilized: JKS (Java KeyStore), BKS (Bouncy Castle KeyStore), and PKCS12 (Public-Key Cryptography Standards #12). Each of these formats serves unique purposes and offers different features tailored to specific application needs.\n\n" +
        "JKS (Java KeyStore) is the default keystore format in Java, primarily used for storing private keys, public keys, and certificates. While it is straightforward and convenient for Java applications, it is less secure due to its proprietary format and limited encryption support, making it suitable for simple applications where high security is not a primary concern.\n\n" +
        "BKS (Bouncy Castle KeyStore) is part of the Bouncy Castle library and is designed for enhanced security, supporting various encryption algorithms. This makes it an excellent choice for mobile applications, especially on Android, where robust security measures are essential.\n\n" +
        "PKCS12 (Public-Key Cryptography Standards #12) is a widely recognized standard that provides strong encryption and password protection, making it ideal for cross-platform compatibility. It is suitable for both personal and enterprise use, especially in scenarios where secure key management and interoperability between different systems are crucial.\n\n" +
        "Each keystore type serves specific needs, so choosing the right one depends on the application's requirements and security considerations.";
        SpannableStringBuilder firstInfo = TextFormatter.formatText(keysInfo, "Q : What's the differnce between JKS, BKS, and PKCS12 keys?", "BC", getColor(R.color.colorAccent));
        firstInfo = TextFormatter.formatText(firstInfo, "A :","BC", getColor(R.color.colorAccent));
        firstInfo = TextFormatter.formatText(firstInfo, "JKS (Java KeyStore)","bold", 0);
        firstInfo = TextFormatter.formatText(firstInfo, "BKS (Bouncy Castle KeyStore)","bold", 0);
        firstInfo = TextFormatter.formatText(firstInfo, "PKCS12 (Public-Key Cryptography Standards #12)","bold", 0);
        firstInfo = TextFormatter.formatText(firstInfo, "PKCS12 (Public-Key Cryptography Standards #12)","bold", 0);
        FaqText.setText(firstInfo);
	    
    }
	
	
	public void _MakeRipple(final View _view, final double _shadow, final double _radius, final String _color, final String _ripple) {
		GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_radius);
		_view.setElevation((int)_shadow);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_ripple)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_view.setClickable(true);
		_view.setBackground(ripdrb);
	}
	
	
	public void _SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
		
		   layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
		_layout.setLayoutParams(layoutParams);
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
	
}