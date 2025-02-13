package com.xapps.utility.xsigner;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.graphics.RenderEffect;
import android.widget.FrameLayout;
import android.view.Window;
import android.graphics.Shader.TileMode;
import kotlinx.coroutines.channels.TickerMode;

public class BlurUtils {

    // Method to blur content behind the dialog
    public static void applyBlurBehindDialog(Context context, AlertDialog dialog, float blurRadius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Use RenderEffect for Android 12+ (API 31+)
            applyBlurFor12Plus(context, dialog, blurRadius);
        } else {
            // Use RenderScript for Android below 12
            applyBlurForBelow12(context, dialog, blurRadius);
        }
    }

    // Method to apply blur effect behind the dialog (Android 12+)
    private static void applyBlurFor12Plus(Context context, AlertDialog dialog, float blurRadius) {
        // Get the root view of the activity behind the dialog
        View rootView = ((Window) dialog.getWindow()).getDecorView().getRootView();
        
        // Capture the root view into a Bitmap
        Bitmap bitmap = captureViewToBitmap(rootView);
        
        // Apply RenderEffect (blur) to the bitmap
        Bitmap blurredBitmap = applyRenderEffect(bitmap, blurRadius);
        
        // Create a transparent overlay to blur behind the dialog
        FrameLayout overlay = new FrameLayout(context);
        overlay.setBackground(new BitmapDrawable(context.getResources(), blurredBitmap));
        
        // Add the overlay behind the dialog window
        dialog.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable(context.getResources(), blurredBitmap));
        
        // Apply RenderEffect to the overlay
        overlay.setRenderEffect(RenderEffect.createBlurEffect(blurRadius, blurRadius, TileMode.DECAL));
    }

    // Method to apply blur effect behind the dialog (Android below 12)
    private static void applyBlurForBelow12(Context context, AlertDialog dialog, float blurRadius) {
        // Get the root view of the activity behind the dialog
        View rootView = ((Window) dialog.getWindow()).getDecorView().getRootView();
        
        // Capture the root view into a Bitmap
        Bitmap bitmap = captureViewToBitmap(rootView);
        
        // Apply RenderScript blur to the bitmap
        Bitmap blurredBitmap = applyRenderScriptBlur(context, bitmap, blurRadius);
        
        // Set the blurred bitmap as the background for the dialog window
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable(context.getResources(), blurredBitmap));
    }

    // Capture the view into a Bitmap
    private static Bitmap captureViewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    // Apply RenderEffect for Android 12+ (API 31+)
    private static Bitmap applyRenderEffect(Bitmap bitmap, float blurRadius) {
        Bitmap blurredBitmap = bitmap.copy(bitmap.getConfig(), true);
        RenderEffect renderEffect = RenderEffect.createBlurEffect(blurRadius, blurRadius, TileMode.CLAMP);
        return Bitmap.createBitmap(blurredBitmap);
    }

    // Apply RenderScript-based blur for Android below 12
    private static Bitmap applyRenderScriptBlur(Context context, Bitmap image, float blurRadius) {
        RenderScript renderScript = RenderScript.create(context);
        Bitmap outputBitmap = Bitmap.createBitmap(image);
        Allocation input = Allocation.createFromBitmap(renderScript, image);
        Allocation output = Allocation.createFromBitmap(renderScript, outputBitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        blur.setRadius(blurRadius);
        blur.setInput(input);
        blur.forEach(output);
        output.copyTo(outputBitmap);

        renderScript.destroy();
        return outputBitmap;
    }
}