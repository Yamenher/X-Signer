package com.xapps.utility.xsigner;

import android.os.Bundle;
import android.view.*;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.transition.platform.*;
import com.xapps.utility.xsigner.databinding.ActivityDevInfoBinding;
import androidx.activity.EdgeToEdge;
import com.google.android.material.internal.EdgeToEdgeUtils;

public class DevInfoActivity extends AppCompatActivity {
    
    private ActivityDevInfoBinding binding;
    private MaterialContainerTransform enterAnim;
    private MaterialContainerTransform returnAnim;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        binding = ActivityDevInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        findViewById(android.R.id.content).setTransitionName("transition_dev");
        enterAnim = new MaterialContainerTransform();
        enterAnim.addTarget(android.R.id.content);
        enterAnim.setEndContainerColor(getColor(R.drawable.color_surface));
        enterAnim.setDuration(400L);
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementEnterTransition(enterAnim);
        returnAnim = new MaterialContainerTransform();
        returnAnim.addTarget(android.R.id.content);
        returnAnim.setEndContainerColor(getColor(R.drawable.color_surface));
        returnAnim.setDuration(250L);
        getWindow().setSharedElementExitTransition(returnAnim);
        InitUi();
    }
    
    public void InitUi() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.toolbar.setTitleCentered(true);
        setTitle("About the developer");
        XUtil.ApplyMarginToView(binding.toolbar, true);
    }
}
