package com.xapps.utility.xsigner;

import android.Manifest;
import android.widget.ScrollView;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.ktx.*;
import androidx.core.splashscreen.*;
import androidx.emoji2.*;
import androidx.emoji2.viewsintegration.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.google.android.material.textfield.*;
import com.mursaat.extendedtextview.*;
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



public class KeyCreatingActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
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
	
	private NestedScrollView Scroller;
	private MaterialButton CreateButton;
	private LinearLayout BG;
	private TextView TopTitle;
	private TextInputLayout AliasTIP;
	private TextInputLayout AliasPassTIP;
	private TextInputLayout KeyStorePassTIP;
	private TextView MiddleTitle;
	private TextInputLayout KeyTypeTIP;
	private TextInputLayout KeySizeTIP;
	private TextInputLayout KeyValidityTIP;
	private TextView TypeTitle;
	private TextInputLayout NameTIP;
	private LinearLayout OrganizationContainer;
	private LinearLayout LocationContainer;
	private TextInputLayout CountryTIP;
	private TextInputEditText AliasE;
	private TextInputEditText AliasPassE;
	private TextInputEditText KeyStorePassE;
	private AutoCompleteTextView KeyTypeE;
	private AutoCompleteTextView KeySizeE;
	private TextInputEditText KeyValidityE;
	private TextInputEditText NameE;
	private TextInputLayout OrUnitTIP;
	private TextInputLayout OrNameTIP;
	private TextInputEditText OrUnitE;
	private TextInputEditText OrNameE;
	private TextInputLayout CityTIP;
	private TextInputLayout StateTIP;
	private TextInputEditText CityE;
	private TextInputEditText StateE;
	private TextInputEditText CountryE;
	
	private TimerTask ClickSkipTimer;
	private TimerTask FinishTimer;
	private AlertDialog FinishDialog;
	private ObjectAnimator SildeOut = new ObjectAnimator();
	private ObjectAnimator FadeIn = new ObjectAnimator();
	private ObjectAnimator FadeOut = new ObjectAnimator();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
        getWindow().setAllowEnterTransitionOverlap(true);
        MaterialSharedAxis enterTransition = new MaterialSharedAxis(MaterialSharedAxis.X, true);
        enterTransition.addTarget(R.id._coordinator);
        getWindow().setEnterTransition(enterTransition);
        MaterialSharedAxis returnTransition = new MaterialSharedAxis(MaterialSharedAxis.X, false);
        returnTransition.addTarget(R.id._coordinator);
        getWindow().setReturnTransition(returnTransition);
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.key_creating);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				getOnBackPressedDispatcher().onBackPressed();
			}
		});
		Scroller = findViewById(R.id.Scroller);
		CreateButton = findViewById(R.id.CreateButton);
		BG = findViewById(R.id.BG);
		TopTitle = findViewById(R.id.TopTitle);
		AliasTIP = findViewById(R.id.AliasTIP);
		AliasPassTIP = findViewById(R.id.AliasPassTIP);
		KeyStorePassTIP = findViewById(R.id.KeyStorePassTIP);
		MiddleTitle = findViewById(R.id.MiddleTitle);
		KeyTypeTIP = findViewById(R.id.KeyTypeTIP);
		KeySizeTIP = findViewById(R.id.KeySizeTIP);
		KeyValidityTIP = findViewById(R.id.KeyValidityTIP);
		TypeTitle = findViewById(R.id.TypeTitle);
		NameTIP = findViewById(R.id.NameTIP);
		OrganizationContainer = findViewById(R.id.OrganizationContainer);
		LocationContainer = findViewById(R.id.LocationContainer);
		CountryTIP = findViewById(R.id.CountryTIP);
		AliasE = findViewById(R.id.AliasE);
		AliasPassE = findViewById(R.id.AliasPassE);
		KeyStorePassE = findViewById(R.id.KeyStorePassE);
		KeyTypeE = findViewById(R.id.KeyTypeE);
		KeySizeE = findViewById(R.id.KeySizeE);
		KeyValidityE = findViewById(R.id.KeyValidityE);
		NameE = findViewById(R.id.NameE);
		OrUnitTIP = findViewById(R.id.OrUnitTIP);
		OrNameTIP = findViewById(R.id.OrNameTIP);
		OrUnitE = findViewById(R.id.OrUnitE);
		OrNameE = findViewById(R.id.OrNameE);
		CityTIP = findViewById(R.id.CityTIP);
		StateTIP = findViewById(R.id.StateTIP);
		CityE = findViewById(R.id.CityE);
		StateE = findViewById(R.id.StateE);
		CountryE = findViewById(R.id.CountryE);
		
		CreateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				IsDetectedEmpty = false;
				if (!IsDetectedEmpty) {
					if (AliasE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						AliasE.requestFocus();
					}
				}
				if (!IsDetectedEmpty) {
					if (AliasPassE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						AliasPassE.requestFocus();
					}
				}
				if (!IsDetectedEmpty) {
					if (KeyStorePassE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						KeyStorePassE.requestFocus();
					}
				}
				if (!IsDetectedEmpty) {
					if (KeyTypeE.getText().toString().trim().isEmpty()) {
						IsDetectedEmpty = true;
						KeyTypeE.performClick();
					}
				}
				if (!IsDetectedEmpty) {
					if (KeySizeE.getText().toString().trim().isEmpty()) {
						KeySizeE.performClick();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (KeyValidityE.getText().toString().trim().isEmpty() || (KeyValidityE.getText().toString().trim().length() > 3)) {
						KeyValidityE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (NameE.getText().toString().trim().isEmpty()) {
						NameE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (OrUnitE.getText().toString().trim().isEmpty()) {
						OrUnitE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (OrNameE.getText().toString().trim().isEmpty()) {
						OrNameE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (CityE.getText().toString().trim().isEmpty()) {
						CityE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (StateE.getText().toString().trim().isEmpty()) {
						StateE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (CountryE.getText().toString().trim().isEmpty() || (CountryE.getText().toString().trim().length() > 2)) {
						CountryE.requestFocus();
						IsDetectedEmpty = true;
					}
				}
				if (!IsDetectedEmpty) {
					if (KeyTypeE.getText().toString().equals("JKS")) {
						extension = "jks";
					}
					if (KeyTypeE.getText().toString().equals("BKS")) {
						extension = "bks";
					}
					if (KeyTypeE.getText().toString().equals("PKCS12")) {
						extension = "pkcs12";
					}
					OutputPath = "/storage/emulated/0/X-Signer/Keys/".concat(NameE.getText().toString().trim().replace(" ", "_").concat(".".concat(extension)));
					ValidityYears = Double.parseDouble(KeyValidityE.getText().toString());
					KeySizeBits = Double.parseDouble(KeySizeE.getText().toString());
					SildeOut.setTarget(CreateButton);
					SildeOut.setPropertyName("translationY");
					SildeOut.setFloatValues((float)(400));
					SildeOut.setDuration((int)(500));
					SildeOut.start();
					class WorkAsync extends AsyncTask<Void, Void, Void> { 
						@Override
						protected void onPreExecute() {
							FinishTimer = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (IsCreated) {
												FinishTimer.cancel();
												final AlertDialog FinishDialog = new AlertDialog.Builder(KeyCreatingActivity.this).create();
												LayoutInflater FinishDialogLI = getLayoutInflater();
												View FinishDialogCV = (View) FinishDialogLI.inflate(R.layout.material_dialog_layout_custom, null);
												FinishDialog.setView(FinishDialogCV);
												FinishDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
												final TextView DTitle = (TextView)
												FinishDialogCV.findViewById(R.id.Title);
												final TextView DMessage = (TextView)
												FinishDialogCV.findViewById(R.id.Message);
												final TextView RightText = (TextView)
												FinishDialogCV.findViewById(R.id.RightText);
												final LinearLayout LeftButton = (LinearLayout)
												FinishDialogCV.findViewById(R.id.LeftButton);
												final LinearLayout RightButton = (LinearLayout)
												FinishDialogCV.findViewById(R.id.RightButton);
												DTitle.setText("Keystore created!");
												DMessage.setText("Your".concat(extension.toUpperCase().concat(" keystore was successfully created and saved in path :\n".concat(OutputPath))));
												RightText.setText("Finish");
												LeftButton.setVisibility(View.GONE);
												RightButton.setOnClickListener(new View.OnClickListener() {
													@Override
													public void onClick(View _view) {
														FinishDialog.dismiss();
														finish();
													}
												});
												FinishDialog.setCancelable(false);
												FinishDialog.show();
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(FinishTimer, (int)(1000), (int)(100));
							return ;
						}


						protected Void doInBackground(Void... arg0) {
							
							_CreateSigningKey(KeyTypeE.getText().toString(), OutputPath, KeyStorePassE.getText().toString().trim(), AliasE.getText().toString().trim(), AliasPassE.getText().toString().trim(), "CN=".concat(AliasE.getText().toString().trim().concat(", ").concat("OU=".concat(OrUnitE.getText().toString().trim().concat(", ").concat("O=".concat(OrNameE.getText().toString().trim().concat(", ").concat("L=".concat(CityE.getText().toString().trim().concat(", ").concat("ST=".concat(StateE.getText().toString().trim().concat(", ").concat("C=".concat(CountryE.getText().toString().trim()))))))))))), (int)ValidityYears, (int)KeySizeBits);
							
							return null;
							 }


						protected void onPostExecute(Void result) {
							IsCreated = true;
											return ;
											    }
					}
					new WorkAsync().execute();
				}
				else {
					
				}
			}
		});
		
		KeyValidityE.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (KeyValidityE.getText().toString().length() > 3) {
					KeyValidityTIP.setError("Too big value entered (I mean why?)");
					KeyValidityTIP.setErrorEnabled(true);
				}
				else {
					KeyValidityTIP.setErrorEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		CountryE.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (CountryE.getText().toString().length() > 2) {
					CountryTIP.setError("The country code must be formed from only 2 characters");
					CountryTIP.setErrorEnabled(true);
				}
				else {
					CountryTIP.setErrorEnabled(false);
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
		KeyTypeE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				KeyTypeE.showDropDown();
			}
		});
		KeyTypeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			            @Override
			            public void onFocusChange(View v, boolean hasFocus) {
				                if (hasFocus) {
					                   IsFocused = true; KeyTypeE.showDropDown();
					                } else {
					                   IsFocused = false;
					                }
				            }
			        });
		KeySizeE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				KeySizeE.showDropDown();
			}
		});
		KeySizeE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			            @Override
			            public void onFocusChange(View v, boolean hasFocus) {
				                if (hasFocus) {
					                   IsFocused = true; KeySizeE.showDropDown();
					                } else {
					                   IsFocused = false;
					                }
				            }
			        });
		KeyValidityTIP.setCounterMaxLength(3);
		KeyValidityTIP.setCounterEnabled(true);
		CountryTIP.setCounterMaxLength(2);
		CountryTIP.setCounterEnabled(true);
		AliasE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    AliasPassE.requestFocus();
					    }
				            return true;
				            }
		});
		AliasPassE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    KeyStorePassE.requestFocus();
					    }
				            return true;
				            }
		});
		KeyStorePassE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    KeyTypeE.requestFocus();
					KeyTypeE.performClick();
					    }
				            return true;
				            }
		});
		KeyTypeE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    KeySizeE.requestFocus();
					KeySizeE.performClick();
					    }
				            return true;
				            }
		});
		KeySizeE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    XUtil.hideKeyboard(KeyCreatingActivity.this);
					    }
				            return true;
				            }
		});
		KeyValidityE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    NameE.requestFocus();
					    }
				            return true;
				            }
		});
		NameE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    OrUnitE.requestFocus();
					    }
				            return true;
				            }
		});
		OrUnitE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    OrNameE.requestFocus();
					    }
				            return true;
				            }
		});
		OrNameE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    CityE.requestFocus();
					    }
				            return true;
				            }
		});
		CityE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    StateE.requestFocus();
					    }
				            return true;
				            }
		});
		StateE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_NEXT) {
					    CountryE.requestFocus();
					    }
				            return true;
				            }
		});
		CountryE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			    @Override
			        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				            if (actionId == EditorInfo.IME_ACTION_DONE) {
					    XUtil.hideKeyboard(KeyCreatingActivity.this);
					    }
				            return true;
				            }
		});
	}
	
	public void _Commands() {
	}
	
	
	public void _SetupUI() {
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
		CreateButton.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
			            @Override
			            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
				               int navigationBarHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
				
				                 ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) CreateButton.getLayoutParams();
				                params.bottomMargin = navigationBarHeight;
				                CreateButton.setLayoutParams(params);
				
				                
				                return insets;
				            }
			        });
		
		int navigationBarHeight = 0;
		int statusBarHeight= 0;
		int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (r1 > 0) {
			    navigationBarHeight = getResources().getDimensionPixelSize(r1);
		}
		int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (r2 > 0) {
			    statusBarHeight = getResources().getDimensionPixelSize(r2);
		}
		_SetMargins(_toolbar, 0, statusBarHeight, 0, 0);
		AliasTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		AliasPassTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		KeyStorePassTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		KeyTypeTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		KeySizeTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		KeyValidityTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		NameTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		OrNameTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		OrUnitTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		CityTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		StateTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
		CountryTIP.setBoxCornerRadii((float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20), (float)_DpToPx(20));
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
}
