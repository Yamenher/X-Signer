package com.xapps.utility.xsigner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.transition.platform.*;
import com.xapps.utility.xsigner.databinding.ActivityDevInfoBinding;
import androidx.activity.EdgeToEdge;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.bumptech.glide.Glide;

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
        GlideL();
        SocialYamen();
        SocialSyntax();
    }
    
    public void InitUi() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.toolbar.setTitleCentered(true);
        setTitle("About the developer");
        XUtil.ApplyMarginToView(binding.toolbar, true);
    }
    public void GlideL(){
        Glide.with(getApplicationContext()).load(Uri.parse("https://github.com/yamenher.png")).into(binding.Yamenpic);
         Glide.with(getApplicationContext()).load(Uri.parse("https://github.com/syntaxspins.png")).into(binding.syntaxpic);
        
    }
    public void SocialYamen(){
        binding.github.setOnClickListener(v->{
            Uri uri = Uri.parse("http://github.com/yamenher");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        binding.discord.setOnClickListener(v->{
            Uri uri2 = Uri.parse("https://discord.gg/vUZAGfeV");
            Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
            startActivity(intent2);
            
        });
        binding.instagram.setOnClickListener(v->{
            Uri uri3 = Uri.parse("https://www.instagram.com/hermassi_yamen/");
            Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
            startActivity(intent3);
            
        });
        binding.telegram.setOnClickListener(v->{
            Uri uri4 = Uri.parse("http://t.me/heyyamen");
            Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
            startActivity(intent4);
            
        });
    }
    public void SocialSyntax(){
        binding.Sgithub.setOnClickListener(v->{
            Uri uri5 = Uri.parse("http://github.com/syntaxspins");
            Intent intent5 = new Intent(Intent.ACTION_VIEW, uri5);
            startActivity(intent5);
        });
        binding.bsky.setOnClickListener(v->{
            Uri uri6 = Uri.parse("https://bsky.app/profile/syntaxspin.bsky.social");
            Intent intent6 = new Intent(Intent.ACTION_VIEW, uri6);
            startActivity(intent6);
            
        });
        
        binding.mastodon.setOnClickListener(v->{
            Uri uri7 = Uri.parse("https://mastodon.social/@SyntaxSpin");
            Intent intent7 = new Intent(Intent.ACTION_VIEW, uri7);
            startActivity(intent7);
            
        });
    }
}
