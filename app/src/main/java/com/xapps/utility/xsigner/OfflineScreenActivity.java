package com.xapps.utility.xsigner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.xapps.utility.xsigner.databinding.OfflineActivityBinding;

public class OfflineScreenActivity extends AppCompatActivity {
    
    private OfflineActivityBinding binding;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OfflineActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
