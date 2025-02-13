package com.xapps.utility.xsigner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xapps.utility.xsigner.databinding.OfflineActivityBinding;

public class OfflineScreenActivity extends AppCompatActivity {

    private OfflineActivityBinding binding;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OfflineActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.retryButton.setOnClickListener(
                v -> {
                    Intent intent = new Intent(context, SplashScreenActivity.class);
                    startActivity(intent);
                    finish();
                });
    }
}
