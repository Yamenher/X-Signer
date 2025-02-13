package com.xapps.utility.xsigner;

import android.animation.*;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.window.*;

import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.shape.*;
import com.google.android.material.transition.platform.*;
import com.xapps.utility.xsigner.databinding.FaqBinding;

public class FaqActivity extends AppCompatActivity {

    private final Context context = this;
    private boolean isLifted = false;
    private FaqBinding binding;
    int navigationBarHeight = 0;
    int statusBarHeight = 0;
    private Drawable bg;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        binding = FaqBinding.inflate(getLayoutInflater());
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        super.onCreate(_savedInstanceState);
        setContentView(binding.getRoot());
        initialize(_savedInstanceState);
        initializeLogic();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(
                androidx.core.content.ContextCompat.getColor(context, R.drawable.color_surface));
        binding.Coordinator.setBackground(drawable);
        binding.AppBar.setOutlineProvider(android.view.ViewOutlineProvider.BACKGROUND);
        binding.AppBar.setClipToOutline(true);
        OnBackPressedCallback callback =
                new OnBackPressedCallback(true) {

                    @Override
                    public void handleOnBackStarted(BackEventCompat backEvent) {
                        bg = binding.AppBar.getBackground();
                    }

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

                    public void handleOnBackCancelled() {
                        binding.AppBar.setBackground(bg);
                    }
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
        binding.Toolbar.setTitle("FAQ");
        EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
        int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (r1 > 0) {
            navigationBarHeight = getResources().getDimensionPixelSize(r1);
        }
        int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (r2 > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(r2);
        }
        _SetMargins(binding.Toolbar, 0, statusBarHeight, 0, 0);
        String keysInfo =
                "Q : What's the differnce between JKS, BKS, and PKCS12 keys? \n\n"
                    + "A : In the realm of cryptographic key management, three prominent keystore"
                    + " formats are commonly utilized: JKS (Java KeyStore), BKS (Bouncy Castle"
                    + " KeyStore), and PKCS12 (Public-Key Cryptography Standards #12). Each of"
                    + " these formats serves unique purposes and offers different features tailored"
                    + " to specific application needs.\n\n"
                    + "JKS (Java KeyStore) is the default keystore format in Java, primarily used"
                    + " for storing private keys, public keys, and certificates. While it is"
                    + " straightforward and convenient for Java applications, it is less secure due"
                    + " to its proprietary format and limited encryption support, making it"
                    + " suitable for simple applications where high security is not a primary"
                    + " concern.\n\n"
                    + "BKS (Bouncy Castle KeyStore) is part of the Bouncy Castle library and is"
                    + " designed for enhanced security, supporting various encryption algorithms."
                    + " This makes it an excellent choice for mobile applications, especially on"
                    + " Android, where robust security measures are essential.\n\n"
                    + "PKCS12 (Public-Key Cryptography Standards #12) is a widely recognized"
                    + " standard that provides strong encryption and password protection, making it"
                    + " ideal for cross-platform compatibility. It is suitable for both personal"
                    + " and enterprise use, especially in scenarios where secure key management and"
                    + " interoperability between different systems are crucial.\n\n"
                    + "Each keystore type serves specific needs, so choosing the right one depends"
                    + " on the application's requirements and security considerations.";
        SpannableStringBuilder firstInfo =
                TextFormatter.formatText(
                        keysInfo,
                        "Q : What's the differnce between JKS, BKS, and PKCS12 keys?",
                        "BC",
                        getColor(R.color.primary_color));
        binding.FaqText.setText(firstInfo);
        XUtil.ApplyMarginToView(binding.FaqText, false);
    }

    public void _MakeRipple(
            final View _view,
            final double _shadow,
            final double _radius,
            final String _color,
            final String _ripple) {
        GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setColor(Color.parseColor(_color));
        gd.setCornerRadius((int) _radius);
        _view.setElevation((int) _shadow);
        android.content.res.ColorStateList clrb =
                new android.content.res.ColorStateList(
                        new int[][] {new int[] {}}, new int[] {Color.parseColor(_ripple)});
        android.graphics.drawable.RippleDrawable ripdrb =
                new android.graphics.drawable.RippleDrawable(clrb, gd, null);
        _view.setClickable(true);
        _view.setBackground(ripdrb);
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
}
