package com.xapps.utility.xsigner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import com.xapps.utility.xsigner.databinding.SettingsActivityBinding;
import android.content.res.Resources;
import android.widget.*;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.*;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.window.*;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import android.animation.*;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.transition.platform.*;
import com.google.android.material.shape.*;
import androidx.core.app.ActivityOptionsCompat;
import com.google.android.material.appbar.MaterialToolbar;
import android.window.OnBackInvokedCallback;
import com.xapps.utility.xsigner.databinding.FaqBinding;
import android.os.*;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.button.MaterialButton;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.graphics.drawable.Drawable;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.processphoenix.ProcessPhoenix;
import android.content.Intent;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.BackEventCompat;
import android.content.Context;
import com.xapps.utility.xsigner.XUtil;

public class SettingsActivity extends PrefsActivity {
    
    private SettingsActivityBinding binding;
    
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    private static final boolean ATLEAST_S = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S;
    
    private int statusBarHeight = 0;
    private Drawable bg;
    private Drawable bg2;
    private int navigationBarHeight = 0;
    private final Context context = this;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SettingsActivityBinding.inflate(getLayoutInflater());
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(binding.getRoot());
        setupEnterTransition();
        setSupportActionBar(binding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				getOnBackPressedDispatcher().onBackPressed();
			}
		});
        setTitle("Settings");
        
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(androidx.core.content.ContextCompat.getColor(context, R.drawable.color_surface));
        binding.coordinator.setBackground(drawable);
        binding.appbar.setOutlineProvider(android.view.ViewOutlineProvider.BACKGROUND);
        binding.appbar.setClipToOutline(true);
        OnBackPressedCallback callback = new OnBackPressedCallback(true)  {
            
            @Override
            public void handleOnBackStarted(BackEventCompat backEvent) {
                bg = binding.appbar.getBackground();
                bg2 = binding.toolbar.getBackground();
            }
            
            @Override
            public void handleOnBackProgressed(BackEventCompat backEvent) {
                    binding.coordinator.setScaleY(1f-0.15f*backEvent.getProgress());
                    binding.coordinator.setScaleX(1f-0.15f*backEvent.getProgress());
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setShape(GradientDrawable.RECTANGLE);
                    drawable.setColor(androidx.core.content.ContextCompat.getColor(context, R.drawable.color_surface));
                    drawable.setCornerRadius(65f*backEvent.getProgress());
                    binding.coordinator.setBackground(drawable);
                    GradientDrawable drawable2 = new GradientDrawable();
                    drawable2.setShape(GradientDrawable.RECTANGLE);
                    drawable2.setColor(XUtil.extractColorFromView(context, binding.appbar));
                    float topCornerRadius = 65f * backEvent.getProgress();
                    drawable2.setCornerRadii(new float[]{ topCornerRadius, topCornerRadius, topCornerRadius, topCornerRadius, 0f, 0f, 0f, 0f});
                    binding.appbar.setBackground(drawable2);
                    GradientDrawable drawable3 = new GradientDrawable();
                    drawable3.setShape(GradientDrawable.RECTANGLE);
                    drawable3.setColor(XUtil.extractColorFromView(context, binding.toolbar));
                    drawable3.setCornerRadii(new float[]{ topCornerRadius, topCornerRadius, topCornerRadius, topCornerRadius, 0f, 0f, 0f, 0f});
                    binding.toolbar.setBackground(drawable2);
            }

            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(0, R.anim.fade);
            }

            public void handleOnBackCancelled() {
               binding.appbar.setBackground(bg);
               binding.toolbar.setBackground(bg2);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
        
        EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (r1 > 0) {
			    navigationBarHeight = getResources().getDimensionPixelSize(r1);
		}
		int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (r2 > 0) {
			    statusBarHeight = getResources().getDimensionPixelSize(r2);
		}
		
    // disable dynamic colors pref if not android 12
    if (!ATLEAST_S) {
        binding.dynamicColorsSwitch.setEnabled(false);
        binding.dynamicColorsLinear.setEnabled(false);
        binding.dynamicColorsTitle.setEnabled(false);
        binding.dynamicColorsDesc.setEnabled(false);
    }
		SetMargins(binding.toolbar, 0, statusBarHeight, 0, 0);
        binding.themeChoiceLinear.setOnClickListener(v -> {
            showThemeBottomSheet();
        });
        binding.dynamicColorsSwitch.setChecked(getDynamicColorsState());
        binding.dynamicColorsLinear.setOnClickListener(v -> {
            binding.dynamicColorsSwitch.setChecked(!binding.dynamicColorsSwitch.isChecked());
            setDynamicColorsOn(binding.dynamicColorsSwitch.isChecked());
            View snackView = getLayoutInflater().inflate(R.layout.action_snackbar, null);
            Snackbar restartSnack = Snackbar.make(binding.coordinator, "", Snackbar.LENGTH_LONG);
            final LinearLayout SBG = (LinearLayout)
            snackView.findViewById(R.id.BG);
            final TextView Message = (TextView)
            snackView.findViewById(R.id.Message);
            final ImageView Icon = (ImageView)
            snackView.findViewById(R.id.Icon);
            final MaterialButton button = snackView.findViewById(R.id.button);
            button.setOnClickListener(v2 -> {
                //ProcessPhoenix.triggerRebirth(getApplicationContext());
                restart(this);
            });
            Icon.setImageResource(R.drawable.ic_error_white);
            Message.setText("Restart to apply changes");
            restartSnack.getView().setBackgroundColor(Color.TRANSPARENT);
            Snackbar.SnackbarLayout snack = (Snackbar.SnackbarLayout) restartSnack.getView();
            snack.addView(snackView, 0);
            restartSnack.show();
        });
    }

    public void setupEnterTransition() {
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
        getWindow().setSharedElementsUseOverlay(false);
    }

    public void SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
	    layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
		_layout.setLayoutParams(layoutParams);
	}

    public double DpToPx(final double _dp) {
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (double) Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void showThemeBottomSheet() {
        BottomSheetDialog bs = new BottomSheetDialog(this);
            bs.setContentView(R.layout.theme_preview);
            MaterialSwitch themeSwitch = bs.findViewById(R.id.auto_switch);
            MaterialCardView card1 = bs.findViewById(R.id.card);
            MaterialCardView card2 = bs.findViewById(R.id.card2);
            MaterialButton button = bs.findViewById(R.id.button);
            TextView text1 = bs.findViewById(R.id.text1);
            TextView text2 = bs.findViewById(R.id.text2);
            card1.setCheckable(true);
            card2.setCheckable(true);
            themeSwitch.setChecked(isAutoModeEnabled(this));
            card1.setAlpha(themeSwitch.isChecked()? 0.4f : 1f);
            card2.setAlpha(themeSwitch.isChecked()? 0.4f : 1f);
            card1.setEnabled(!themeSwitch.isChecked());
            card2.setEnabled(!themeSwitch.isChecked());
            if (!isAutoModeEnabled(this)) {
                card1.setChecked(true);
            }
            if (getSystemThemeMode(this) == "light") {
                text1.setText("Light theme");
                text2.setText("Dark theme");
            }
            bs.findViewById(R.id.auto_mode_linear).setOnClickListener(v2 -> {
                themeSwitch.setChecked(!themeSwitch.isChecked());
            });
            card1.setOnClickListener(v3 -> {
                card1.setChecked(true);
                card2.setChecked(false);
            });
            card2.setOnClickListener(v4 -> {
                card1.setChecked(!true);
                card2.setChecked(!false);
            });
            themeSwitch.setOnCheckedChangeListener((toggle, isChecked) -> {
                card1.setAlpha(isChecked? 0.4f : 1f);
                card2.setAlpha(isChecked? 0.4f : 1f);
                card1.setEnabled(!isChecked);
                card2.setEnabled(!isChecked);
                if (!card1.isChecked() && !card2.isChecked()) {
                    card1.setChecked(true);
                }
            });
            button.setOnClickListener(v5 -> {
                bs.dismiss();
                if (themeSwitch.isChecked()) {
                    setThemeMode("auto");
                } else {
                    if (card1.isChecked()) {
                        setThemeMode(getSystemTargetMode(this));
                    } else if (card2.isChecked()) {
                        setThemeMode(getSystemDesiredMode(this));
                    }
                }
            });
            bs.show();
    }

    public void restart(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);  
        if (context instanceof Activity) {
            context.finish();
        }
        Runtime.getRuntime().exit(0);
    }

}
