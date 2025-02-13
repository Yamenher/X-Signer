package com.xapps.utility.xsigner;

import android.content.Context;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;

import androidx.activity.*;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.pm.PackageInfoCompat;

import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.shape.*;
import com.google.android.material.transition.platform.*;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.xapps.utility.xsigner.databinding.InfoBinding;

public class InfoActivity extends AppCompatActivity {

    private InfoBinding binding;
    private final Context context = this;
    private String versionName = "";
    private String versionCode = "";
    private MaterialSharedAxis enterTransition;
    private MaterialSharedAxis returnTransition;

    private Intent UserViewIntent = new Intent();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        binding = InfoBinding.inflate(getLayoutInflater());
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setAllowEnterTransitionOverlap(true);
        super.onCreate(_savedInstanceState);
        setContentView(binding.getRoot());
        initialize(_savedInstanceState);
        initializeLogic();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(
                androidx.core.content.ContextCompat.getColor(context, R.drawable.color_surface));
        binding.Coordinator.setBackground(drawable);
        OnBackPressedCallback callback =
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackStarted(BackEventCompat backEvent) {}

                    @Override
                    public void handleOnBackProgressed(BackEventCompat backEvent) {
                        binding.Coordinator.setScaleY(1f - 0.15f * backEvent.getProgress());
                        binding.Coordinator.setScaleX(1f - 0.15f * backEvent.getProgress());
                        GradientDrawable drawable = new GradientDrawable();
                        drawable.setShape(GradientDrawable.RECTANGLE);
                        drawable.setColor(
                                androidx.core.content.ContextCompat.getColor(
                                        context, R.drawable.color_surface));
                        drawable.setCornerRadius(65f * backEvent.getProgress());
                        binding.Coordinator.setBackground(drawable);
                        GradientDrawable drawable2 = new GradientDrawable();
                        drawable2.setShape(GradientDrawable.RECTANGLE);
                        drawable2.setColor(XUtil.extractColorFromView(context, binding.AppBar));
                        float topCornerRadius = 65f * backEvent.getProgress();
                        drawable2.setCornerRadii(
                                new float[] {
                                    topCornerRadius,
                                    topCornerRadius,
                                    topCornerRadius,
                                    topCornerRadius,
                                    0f,
                                    0f,
                                    0f,
                                    0f
                                });
                        binding.AppBar.setBackground(drawable2);
                    }

                    @Override
                    public void handleOnBackPressed() {
                        finish();
                        overridePendingTransition(0, R.anim.fade);
                    }

                    public void handleOnBackCancelled() {}
                };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void initialize(Bundle _savedInstanceState) {

        setSupportActionBar(binding.Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.Toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _v) {
                        getOnBackPressedDispatcher().onBackPressed();
                    }
                });
    }

    private void initializeLogic() {
        EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
        try {
            android.content.pm.PackageInfo packageInfo =
                    InfoActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (Build.VERSION.SDK_INT >= 28) {
                versionCode = Long.toString(PackageInfoCompat.getLongVersionCode(packageInfo));
            } else {
                versionCode = Integer.toString(packageInfo.versionCode);
            }
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.versionText.setText("v" + versionName + " (" + versionCode + ")");
        setTitle("About this app");

        int navigationBarHeight = 0;
        int statusBarHeight = 0;
        int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (r1 > 0) {
            navigationBarHeight = getResources().getDimensionPixelSize(r1);
        }
        int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (r2 > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(r2);
        }
        _SetMargins(binding.Toolbar, 0, statusBarHeight, 0, 0);
        _SetMargins(binding.Scroller, 0, 0, 0, navigationBarHeight);

        binding.linearTelegram.setOnClickListener(v -> openLink("https://t.me/xsignercommunity"));
        binding.linearGithub.setOnClickListener(
                v -> openLink("https://github.com/Yamenher/X-Signer"));
        binding.linearSupport.setOnClickListener(v -> sendMail());
        binding.linearPeople.setOnClickListener(v -> DevInfoPage());
        binding.linearPolicy.setOnClickListener(v -> android.util.Log.i("wlw", "idk"));
        binding.Toolbar.setTitleCentered(true);
    }

    public void _SetMargins(
            final View _layout,
            final int _leftMargin,
            final int _TopMargin,
            final int _RightMargin,
            final int _BottomMargin) {
        ViewGroup.MarginLayoutParams layoutParams =
                (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
        layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
        _layout.setLayoutParams(layoutParams);
    }

    public double _DpToPx(final double _dp) {
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (double) Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void _MakeRipple(
            final View _view,
            final double _shadow,
            final double _radius,
            final int _color,
            final int _ripple) {
        android.graphics.drawable.GradientDrawable gd =
                new android.graphics.drawable.GradientDrawable();
        gd.setColor(_color);
        gd.setCornerRadius((int) _radius);
        _view.setElevation((int) _shadow);
        android.content.res.ColorStateList clrb =
                new android.content.res.ColorStateList(
                        new int[][] {new int[] {}}, new int[] {_ripple});
        android.graphics.drawable.RippleDrawable ripdrb =
                new android.graphics.drawable.RippleDrawable(clrb, gd, null);
        _view.setClickable(true);
        _view.setBackground(ripdrb);
    }

    public void _MakeTransition() {
        /*Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("version", versionName);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, UpdateLinear, "transition2");
        startActivity(intent, options.toBundle());*/
    }

    public void DevInfoPage() {
        Intent intent = new Intent(this, DevInfoActivity.class);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, binding.linearTransition, "transition_dev");
        startActivity(intent, options.toBundle());
    }

    public void openLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    public void sendMail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:xapps.feedback365@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback on X-Signer");
        emailIntent.putExtra(
                Intent.EXTRA_TEXT, "Hello, I wanted to share my feedback regarding X-Signer...");
        try {
            startActivity(emailIntent);
        } catch (Exception e) {
            XUtil.showMessage(getApplicationContext(), "No mailing app was found");
        }
    }
}
