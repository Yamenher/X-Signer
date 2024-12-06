package com.xapps.utility.xsigner;

import android.os.Debug;
import android.view.View;
import android.content.res.Resources;
import android.view.ViewGroup;
import android.graphics.drawable.GradientDrawable;
import android.view.WindowInsets;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import android.animation.ValueAnimator;
import com.google.android.material.internal.EdgeToEdgeUtils;
import android.animation.ArgbEvaluator;
import android.util.DisplayMetrics;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.appbar.MaterialToolbar;
import com.xapps.utility.xsigner.databinding.DebugBinding;
import com.xapps.utility.xsigner.XUtil;



public class DebugActivity extends AppCompatActivity {
	
	private int statusBarHeight;
	private int navigationBarHeight;
	private boolean isLifted = false;
    
    private DebugBinding binding;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
        binding = DebugBinding.inflate(DebugActivity.this.getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		binding.Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				getOnBackPressedDispatcher().onBackPressed();
			}
		});
		
		binding.EndButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
	}
	
	private void initializeLogic() {
		binding.Toolbar.setTitle("X-Signer Crashed");
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		binding.ErrorText.setTextIsSelectable(true);
		binding.Toolbar.setTitleCentered(true);
		binding.ErrorText.setText(getIntent().getStringExtra("error"));
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
        
		binding.EndButton.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
			@Override
		    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
			    navigationBarHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.EndButton.getLayoutParams();
		        params.bottomMargin = navigationBarHeight;
				binding.EndButton.setLayoutParams(params);
				return insets;
			}
		});
		
		binding.Toolbar.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
		    @Override
			public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
				statusBarHeight = insets.getInsets(WindowInsets.Type.statusBars()).top;
				ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) binding.Toolbar.getLayoutParams();
				params2.topMargin = statusBarHeight;
				binding.Toolbar.setLayoutParams(params2);
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
}
