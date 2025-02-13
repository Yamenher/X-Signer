package com.xapps.utility.xsigner;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.sun.security.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.view.inputmethod.EditorInfo;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.*;
import androidx.appcompat.resources.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.*;
import androidx.core.ktx.*;
import androidx.core.splashscreen.*;
import androidx.core.widget.NestedScrollView;
import androidx.emoji2.*;
import androidx.emoji2.viewsintegration.*;
import androidx.lifecycle.livedata.core.*;
import androidx.lifecycle.process.*;
import androidx.lifecycle.runtime.*;
import androidx.lifecycle.viewmodel.*;
import androidx.lifecycle.viewmodel.savedstate.*;
import androidx.profileinstaller.*;
import androidx.savedstate.*;
import androidx.startup.*;
import androidx.transition.*;

import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.*;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.textfield.*;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import com.mursaat.extendedtextview.*;

import org.json.*;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.appbar.MaterialToolbar;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import androidx.core.app.ActivityOptionsCompat;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import android.app.ActivityOptions;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import com.google.android.material.transition.platform.MaterialArcMotion;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.android.material.internal.EdgeToEdgeUtils;
import android.animation.ValueAnimator;
import com.google.android.material.internal.EdgeToEdgeUtils;
import android.animation.ArgbEvaluator;
import android.provider.Settings;
import android.Manifest;
import android.database.Cursor;
import android.provider.OpenableColumns;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import java.io.File;
import androidx.core.view.ViewCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.EditorInfo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.xapps.utility.xsigner.databinding.KeyCreatingBinding;



public class KeyCreatingActivity extends BaseActivity {

	private KeyCreatingBinding binding;
	private Timer _timer = new Timer();
    private int navigationBarHeight = 0;
    private int statusBarHeight= 0;

	private boolean SkipClick = false;
	private boolean IsFocused = false;
	private boolean PermitDropDown = false;
	private boolean isLifted = false;
	private boolean IsDetectedEmpty = false;
	private String extension = "";
	private double ValidityYears = 0;
	private double KeySizeBits = 0;
	private boolean IsCreated = false;
	private String OutputPath = "";
	
	private ArrayList<String> SupportedKeyTypes = new ArrayList<>();
	
	private TimerTask ClickSkipTimer;
	private TimerTask FinishTimer;
	private AlertDialog FinishDialog;
	private ObjectAnimator SildeOut = new ObjectAnimator();
	private ObjectAnimator FadeIn = new ObjectAnimator();
	private ObjectAnimator FadeOut = new ObjectAnimator();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
        binding = KeyCreatingBinding.inflate(getLayoutInflater());
        getWindow().setAllowEnterTransitionOverlap(true);
		super.onCreate(_savedInstanceState);
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		setSupportActionBar(binding.Toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				getOnBackPressedDispatcher().onBackPressed();
			}
		});
		
		binding.CreateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				IsDetectedEmpty = false;
				if (!IsDetectedEmpty) {
					if (binding.AliasE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						binding.AliasE.requestFocus();
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.AliasPassE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						binding.AliasPassE.requestFocus();
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.KeyStorePassE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						binding.KeyStorePassE.requestFocus();
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.KeyTypeE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						binding.KeyTypeE.performClick();
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.KeySizeE.getText().toString().trim().isEmpty()) {
						binding.KeySizeE.performClick();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.KeyValidityE.getText().toString().trim().isEmpty() || (binding.KeyValidityE.getText().toString().trim().length() > 3)) {
						binding.KeyValidityE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.NameE.getText().toString().trim().isEmpty()) {
						binding.NameE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.OrUnitE.getText().toString().trim().isEmpty()) {
						binding.OrUnitE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.OrNameE.getText().toString().trim().isEmpty()) {
						binding.OrNameE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.CityE.getText().toString().trim().isEmpty()) {
						binding.CityE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.StateE.getText().toString().trim().isEmpty()) {
						binding.StateE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.CountryE.getText().toString().trim().isEmpty() || (binding.CountryE.getText().toString().trim().length() > 2)) {
						binding.CountryE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (binding.KeyTypeE.getText().toString().equals("JKS")) {
						extension = "jks";
					}
					if (binding.KeyTypeE.getText().toString().equals("BKS")) {
						extension = "bks";
					}
					if (binding.KeyTypeE.getText().toString().equals("PKCS12")) {
						extension = "pkcs12";
					}
					OutputPath = "/storage/emulated/0/X-Signer/Keys/".concat(binding.NameE.getText().toString().trim().replace(" ", "_").concat(".".concat(extension)));
					ValidityYears = Double.parseDouble(binding.KeyValidityE.getText().toString());
					KeySizeBits = Double.parseDouble(binding.KeySizeE.getText().toString());
					SildeOut.setTarget(binding.CreateButton);
					SildeOut.setPropertyName("translationY");
					SildeOut.setFloatValues((float)(400));
					SildeOut.setDuration((int)(500));
					SildeOut.start();
					new Handler(Looper.getMainLooper()).post(() -> {
                        new Thread(() -> {
                            _CreateSigningKey(binding.KeyTypeE.getText().toString(), OutputPath, binding.KeyStorePassE.getText().toString().trim(), binding.AliasE.getText().toString().trim(), binding.AliasPassE.getText().toString().trim(),"CN=".concat(binding.AliasE.getText().toString().trim().concat(", ").concat("OU=".concat(binding.OrUnitE.getText().toString().trim().concat(", ").concat("O=".concat(binding.OrNameE.getText().toString().trim().concat(", ").concat("L=".concat(binding.CityE.getText().toString().trim().concat(", ").concat("ST=".concat(binding.StateE.getText().toString().trim().concat(", ").concat("C=".concat(binding.CountryE.getText().toString().trim()))))))))))), (int) ValidityYears, (int) KeySizeBits);
                            runOnUiThread(() -> {
                                binding.blurLayout.animate().alpha(1f).setDuration(100L).start();
                                try {
                                    ShowSingleButtonDialog(KeyCreatingActivity.this, "Keystore created!", "Your".concat(extension.toUpperCase().concat(" keystore was successfully created and saved in path :\n".concat(OutputPath))), "Finish", 1);
                                } catch (Exception e) {}
                            });
                        }).start();
                    });
				}
			}
		});
		
		binding.KeyValidityE.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (binding.KeyValidityE.getText().toString().length() > 3) {
					binding.KeyValidityTIP.setError("Too big value entered (I mean why?)");
					binding.KeyValidityTIP.setErrorEnabled(true);
				}
				else {
					binding.KeyValidityTIP.setErrorEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		binding.CountryE.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (binding.CountryE.getText().toString().length() > 2) {
					binding.CountryTIP.setError("The country code must be formed from only 2 characters");
					binding.CountryTIP.setErrorEnabled(true);
				} else {
					binding.CountryTIP.setErrorEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
    
  private void initializeLogic() {
        setTitle("Create a new key");
        _SetupUI();
        binding.KeyTypeE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                binding.KeyTypeE.showDropDown();
            }
        });
        binding.KeyTypeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    IsFocused = true;
                    binding.KeyTypeE.showDropDown();
                } else {
                    IsFocused = false;
                }
            }
        });
        binding.KeySizeE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                binding.KeySizeE.showDropDown();
            }
        });
        binding.KeySizeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    IsFocused = true;
                    binding.KeySizeE.showDropDown();
                } else {
                    IsFocused = false;
                }
            }
        });
        binding.KeyValidityTIP.setCounterMaxLength(3);
        binding.KeyValidityTIP.setCounterEnabled(true);
        binding.CountryTIP.setCounterMaxLength(2);
        binding.CountryTIP.setCounterEnabled(true);
        binding.AliasE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.AliasPassE.requestFocus();
                }
                return true;
            }
        });
        binding.AliasPassE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.KeyStorePassE.requestFocus();
                }
                return true;
            }
        });
        binding.KeyStorePassE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.KeyTypeE.requestFocus();
                    binding.KeyTypeE.performClick();
                }
                return true;
            }
        });
        binding.KeyTypeE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.KeySizeE.requestFocus();
                    binding.KeySizeE.performClick();
                }
                return true;
            }
        });
        binding.KeySizeE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    XUtil.hideKeyboard(KeyCreatingActivity.this);
                }
                return true;
            }
        });
        binding.KeyValidityE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.NameE.requestFocus();
                }
                return true;
            }
        });
        binding.NameE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.OrUnitE.requestFocus();
                }
                return true;
            }
        });
        binding.OrUnitE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.OrNameE.requestFocus();
                }
                return true;
            }
        });
        binding.OrNameE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.CityE.requestFocus();
                }
                return true;
            }
        });
        binding.CityE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.StateE.requestFocus();
                }
                return true;
            }
        });
        binding.StateE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    binding.CountryE.requestFocus();
                }
                return true;
            }
        });
        binding.CountryE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    XUtil.hideKeyboard(KeyCreatingActivity.this);
                }
                return true;
            }    
        });
  }

	public void _SetupUI() {
        Handler handler = new Handler(Looper.getMainLooper());  
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		binding.CreateButton.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
			public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                int navigationBarHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.CreateButton.getLayoutParams();
				params.bottomMargin = navigationBarHeight;
				binding.CreateButton.setLayoutParams(params);
			    return insets;
		    }
		});
		int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (r1 > 0) {
			    navigationBarHeight = getResources().getDimensionPixelSize(r1);
		}
		int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (r2 > 0) {
			    statusBarHeight = getResources().getDimensionPixelSize(r2);
		}
		_SetMargins(binding.Toolbar, 0, statusBarHeight, 0, 0);
        _SetMargins(binding.TopTitle, 0, statusBarHeight, 0, 0);
        Runnable runnable = new Runnable() {  
            @Override  
            public void run() {  
                if (binding.CreateButton.getHeight() != 0) {
                    _SetMargins(binding.CountryTIP, 0, 0, 0, binding.CreateButton.getHeight() + navigationBarHeight);
                } else {
                    handler.postDelayed(this, 50);  
                }
            }  
        };  
        handler.post(runnable);
	}
	
	
	public void _SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
		layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
		_layout.setLayoutParams(layoutParams);
	}
	
	public void _CreateSigningKey(final String _type, final String _path, final String _keystorepass, final String _alias, final String _aliaspass, final String _dn, final int _validity, final int _keysize) {
		try {
			KeyUtils.createKeyStore(_type, _path, _keystorepass, _alias, _aliaspass, _dn, _validity, _keysize);
		} catch (Exception e) {
			Log.e("FUCKING ERROR", e.toString());
			FileUtil.writeFile("/sdcard/e.txt", e.toString());
		}
	}
	
	
	public double _DpToPx(final double _dp) {
		  Resources resources = this.getResources();
		  DisplayMetrics metrics = resources.getDisplayMetrics();
		  return (double)Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}
    
    @Override
    public void singleClickAction(AlertDialog dialog, int eventId) {
        switch (eventId) {
            case 1 :
                dialog.dismiss();
                binding.blurLayout.animate().alpha(0f).setDuration(300L).start();
                finish();
            break;
            default :
                dialog.dismiss();
        }
    }
}
