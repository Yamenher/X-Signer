package com.xapps.utility.xsigner;

import com.xapps.utility.xsigner.SplashScreenActivity;
import android.Manifest;
import com.xapps.utility.xsigner.R;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.sun.security.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.ktx.*;
import androidx.core.splashscreen.*;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.*;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
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
import java.io.File;
import androidx.core.view.ViewCompat;
import androidx.core.view.OnApplyWindowInsetsListener;



public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private int statusBarHeight;
	private int navigationBarHeight;
	private boolean IsBSBeingShow = false;
	private boolean IsPaused = false;
	private Uri PathUri;
	private Uri TargetUri;
	private Uri AccessUri;
	private String Path = "";
	private double fileName = 0;
	private String FileName = "";
	private String dataPath = "";
	private String fileSize = "";
	private String filePath = "";
	private boolean CanAccessStorage = false;
	private String TestString = "";
	private String KeyHashResult = "";
	private String TestKeyHashResult = "";
	private String KeyData = "";
	private double SelectedKey = 0;
	private String LowercaseFilerPath = "";
	private String OutputApk = "";
	private String OutputAab = "";
	private boolean IsLooperReady = false;
	private boolean IsFileCopied = false;
	private double FinalProgressCount = 0;
	private boolean IsShownOnce = false;
	private boolean FileStartedExisting = false;
	private boolean AabStartedExisting = false;
	private Uri PickedFileUri;
	private boolean IsInputFileExisted = false;
	private boolean IsAlreadySetAsIndeterminate = false;
	private double IsNoticedToInititialize = 0;
	private boolean IsFinalIndeterminate = false;
	private boolean IsShownSuccess = false;
	private boolean PermissionToGetSize = false;
	private boolean IsSigned = false;
	private int navigationBarHeight2;
	private double MaxErrors = 0;
	private boolean IsConfirmed = false;
	
	private ArrayList<HashMap<String, Object>> KeysList = new ArrayList<>();
    private ArrayList<String> AppDataFiles = new ArrayList<>();
	
	private CollapsingToolbarLayout collapsingtoolbar;
	private NestedScrollView vscroll;
	private LinearLayout Container;
	private LinearLayout StorageSignLinear;
	private LinearLayout RecentsLinear;
	private LinearLayout SSTopLinear;
	private TextView SSDesc;
	private MaterialButton SSPickButton;
	private LinearLayout SSIconLinear;
	private TextView SSTopTitle;
	private ImageView StorageSignIcon;
	private LinearLayout TopRecentsLinear;
	private TextView RecentsText;
	private MaterialButton ViewRecentsButton;
	private LinearLayout RecentsIconLinear;
	private TextView RecentsTitle;
	private ImageView RecentsIcon;
	private LinearLayout _drawer_DrawerContainer;
	private ImageView _drawer_UserIcon;
	private TextView _drawer_Username;
	private TextView _drawer_EmailAdress;
	private LinearLayout _drawer_Divider;
	private LinearLayout _drawer_HomeLinear;
	private LinearLayout _drawer_KeysLinear;
	private LinearLayout _drawer_FaqLinear;
	private LinearLayout _drawer_AboutLinear;
	private ImageView _drawer_HomeIcon;
	private TextView _drawer_HomeText;
	private ImageView _drawer_KeysIcon;
	private TextView _drawer_KeysText;
	private ImageView _drawer_FaqIcon;
	private TextView _drawer_FaqText;
	private ImageView _drawer_AboutIcon;
	private TextView _drawer_AboutText;
	
	private TimerTask PermissionTimer;
	private com.google.android.material.bottomsheet.BottomSheetDialog BS;
	private Intent PickIntent = new Intent();
	private Intent UriIntent = new Intent();
	private com.google.android.material.snackbar.Snackbar VipSnackbar;
	private AlertDialog SignOptionsDialog;
	private TimerTask RippleTimerDialog;
	private Intent ActivitiesIntent = new Intent();
	private TimerTask AnimationTimer;
	private SharedPreferences KeysManager;
	private SharedPreferences Tmp;
	private TimerTask GrabTimer;
	private AlertDialog FileSignProgress;
	private TimerTask ProcessingTimer;
	private TimerTask FinishTimer;
	private TimerTask FileSizeTimer;
	private AlertDialog FileCopyingDialog;
	private TimerTask OutputSizeTimer;
	private TimerTask HoldOnTimer;
	private TimerTask RetryTimer;
	private AlertDialog OverwriteDialog;
	private TimerTask FailureTimer;
	private SharedPreferences AppData;
	private TimerTask BeginTimer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
	            getOnBackPressedDispatcher().onBackPressed();
			}
	    	});
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		collapsingtoolbar = findViewById(R.id.collapsingtoolbar);
		vscroll = findViewById(R.id.vscroll);
		Container = findViewById(R.id.Container);
		StorageSignLinear = findViewById(R.id.StorageSignLinear);
		RecentsLinear = findViewById(R.id.RecentsLinear);
		SSTopLinear = findViewById(R.id.SSTopLinear);
		SSDesc = findViewById(R.id.SSDesc);
		SSPickButton = findViewById(R.id.SSPickButton);
		SSIconLinear = findViewById(R.id.SSIconLinear);
		SSTopTitle = findViewById(R.id.SSTopTitle);
		StorageSignIcon = findViewById(R.id.StorageSignIcon);
		TopRecentsLinear = findViewById(R.id.TopRecentsLinear);
		RecentsText = findViewById(R.id.RecentsText);
		ViewRecentsButton = findViewById(R.id.ViewRecentsButton);
		RecentsIconLinear = findViewById(R.id.RecentsIconLinear);
		RecentsTitle = findViewById(R.id.RecentsTitle);
		RecentsIcon = findViewById(R.id.RecentsIcon);
		_drawer_DrawerContainer = _nav_view.findViewById(R.id.DrawerContainer);
		_drawer_UserIcon = _nav_view.findViewById(R.id.UserIcon);
		_drawer_Username = _nav_view.findViewById(R.id.Username);
		_drawer_EmailAdress = _nav_view.findViewById(R.id.EmailAdress);
		_drawer_Divider = _nav_view.findViewById(R.id.Divider);
		_drawer_HomeLinear = _nav_view.findViewById(R.id.HomeLinear);
		_drawer_KeysLinear = _nav_view.findViewById(R.id.KeysLinear);
		_drawer_FaqLinear = _nav_view.findViewById(R.id.FaqLinear);
		_drawer_AboutLinear = _nav_view.findViewById(R.id.AboutLinear);
		_drawer_HomeIcon = _nav_view.findViewById(R.id.HomeIcon);
		_drawer_HomeText = _nav_view.findViewById(R.id.HomeText);
		_drawer_KeysIcon = _nav_view.findViewById(R.id.KeysIcon);
		_drawer_KeysText = _nav_view.findViewById(R.id.KeysText);
		_drawer_FaqIcon = _nav_view.findViewById(R.id.FaqIcon);
		_drawer_FaqText = _nav_view.findViewById(R.id.FaqText);
		_drawer_AboutIcon = _nav_view.findViewById(R.id.AboutIcon);
		_drawer_AboutText = _nav_view.findViewById(R.id.AboutText);
		KeysManager = getSharedPreferences("KeysData", Activity.MODE_PRIVATE);
		Tmp = getSharedPreferences("TmpData", Activity.MODE_PRIVATE);
		AppData = getSharedPreferences("XSignerAppData", Activity.MODE_PRIVATE);
		
		SSPickButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (CanAccessStorage) {
					Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
					intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION); intent.addCategory(Intent.CATEGORY_OPENABLE);
					intent.setType("*/*");
					
					startActivityForResult(intent, 911);
				}
			}
		});
		
		ViewRecentsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (CanAccessStorage) {
					throw new RuntimeException("Congrats! You crashed your app.");
				}
			}
		});
		
		_drawer_DrawerContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_drawer_HomeLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_KeysLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
				AnimationTimer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_KeyActivity();
							}
						});
					}
				};
				_timer.schedule(AnimationTimer, (int)(300));
			}
		});
		
		_drawer_FaqLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
				AnimationTimer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_FaqActivity();
							}
						});
					}
				};
				_timer.schedule(AnimationTimer, (int)(300));
			}
		});
		
		_drawer_AboutLinear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
				AnimationTimer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_InfoActivity();
							}
						});
					}
				};
				_timer.schedule(AnimationTimer, (int)(300));
			}
		});
	}
	
	private void initializeLogic() {
		MaterialSharedAxis exitTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, true);
		    exitTransition.addTarget(R.id._coordinator);
		    getWindow().setExitTransition(exitTransition);
		
		    MaterialSharedAxis reenterTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, false);
		    reenterTransition.addTarget(R.id._coordinator);
		    getWindow().setReenterTransition(reenterTransition);
		_SetupUI();
		if (Build.VERSION.SDK_INT > 29)  {
			if (Environment.isExternalStorageManager()) {
				CanAccessStorage = true;
				if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.pk8")) {
					try{
						int count;
						java.io.InputStream input= this.getAssets().open("testkey.pk8");
						java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"testkey.pk8");
						byte data[] = new byte[1024];
						while ((count = input.read(data))>0) {
							output.write(data, 0, count);
						}
						output.flush();
						output.close();
						input.close();
						
						}catch(Exception e){
								
						}
				}
				if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.x509.pem")) {
					try{
						int count;
						java.io.InputStream input= this.getAssets().open("testkey.x509.pem");
						java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"testkey.x509.pem");
						byte data[] = new byte[1024];
						while ((count = input.read(data))>0) {
							output.write(data, 0, count);
						}
						output.flush();
						output.close();
						input.close();
						
						}catch(Exception e){
								
						}
				}
				if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/blank.apk")) {
					try{
						int count;
						java.io.InputStream input= this.getAssets().open("blank.apk");
						java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"blank.apk");
						byte data[] = new byte[1024];
						while ((count = input.read(data))>0) {
							output.write(data, 0, count);
						}
						output.flush();
						output.close();
						input.close();
						
						}catch(Exception e){
								
						}
				}
				_SetupListMapKeys();
			}
			else {
				CanAccessStorage = false;
			}
		} else {
			if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
 == PackageManager.PERMISSION_GRANTED) {
				CanAccessStorage = true;
				if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.pk8")) {
					try{
						int count;
						java.io.InputStream input= this.getAssets().open("testkey.pk8");
						java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"testkey.pk8");
						byte data[] = new byte[1024];
						while ((count = input.read(data))>0) {
							output.write(data, 0, count);
						}
						output.flush();
						output.close();
						input.close();
						
						}catch(Exception e){
								
						}
				}
				if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.x509.pem")) {
					try{
						int count;
						java.io.InputStream input= this.getAssets().open("testkey.x509.pem");
						java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"testkey.x509.pem");
						byte data[] = new byte[1024];
						while ((count = input.read(data))>0) {
							output.write(data, 0, count);
						}
						output.flush();
						output.close();
						input.close();
						
						}catch(Exception e){
								
						}
				}
				if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/blank.apk")) {
					try{
						int count;
						java.io.InputStream input= this.getAssets().open("blank.apk");
						java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"blank.apk");
						byte data[] = new byte[1024];
						while ((count = input.read(data))>0) {
							output.write(data, 0, count);
						}
						output.flush();
						output.close();
						input.close();
						
						}catch(Exception e){
								
						}
				}
				_SetupListMapKeys();
			}
			else {
				CanAccessStorage = false;
			}
		}
		PermissionTimer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_StorageAccessCheck();
					}
				});
			}
		};
		_timer.schedule(PermissionTimer, (int)(100));
		if (KeysManager.contains("KeysData")) {
			KeysList = new Gson().fromJson(KeysManager.getString("KeysData", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		    if (_resultCode == Activity.RESULT_OK) {
				            if (_data != null) {
						        if (_requestCode == 911) {
								PickedFileUri = _data.getData();
								_QuickSigningLogic();
						}
						       } else {
						        
						   }
				       } else {
				        
				   }
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		KeysList = new Gson().fromJson(KeysManager.getString("KeysData", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.pk8")) {
			try{
				int count;
				java.io.InputStream input= this.getAssets().open("testkey.pk8");
				java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"testkey.pk8");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.x509.pem")) {
			try{
				int count;
				java.io.InputStream input= this.getAssets().open("testkey.x509.pem");
				java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"testkey.x509.pem");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/blank.apk")) {
			try{
				int count;
				java.io.InputStream input= this.getAssets().open("blank.apk");
				java.io.OutputStream output = new  java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/"+"blank.apk");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		_SetupListMapKeys();
		if (Build.VERSION.SDK_INT > 29)  {
			if (Environment.isExternalStorageManager()) {
				CanAccessStorage = true;
			}
			else {
				CanAccessStorage = false;
			}
		} else {
			if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
 == PackageManager.PERMISSION_GRANTED) {
				CanAccessStorage = true;
			}
			else {
				CanAccessStorage = false;
			}
		}
		if (IsPaused) {
			_StorageAccessCheck();
			IsPaused = false;
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		IsPaused = true;
	}
        
    @Override
	public void onDestroy() {
		super.onDestroy();
        FileUtil.listDir("/data/data/com.xapps.utility.xsigner/", AppDataFiles);
        for (int i = 0; i < (int)(AppDataFiles.size()); i++) {
        	if (AppDataFiles.get((int)(i)).endsWith(".apk") || AppDataFiles.get((int)(i)).endsWith(".apk/")) {
        		if (!(AppDataFiles.get((int)(i)).endsWith("blank.apk") || AppDataFiles.get((int)(i)).endsWith("blank.apk/"))) {
		        	FileUtil.deleteFile(AppDataFiles.get((int)(i)));
	        	}
        	}
        }
		
	}
    
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	public void _Radius(final View _view, final double _lefttop, final double _righttop, final double _leftbottom, final double _rightbottom, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadii(new float[] { (float)_lefttop, (float)_lefttop, (float)_righttop, (float)_righttop, (float)_rightbottom, (float)_rightbottom, (float)_leftbottom, (float)_leftbottom });
		_view.setBackground(gd);


	}
	
	
	public double _DpToPx(final double _dp) {
		  Resources resources = this.getResources();
		    DisplayMetrics metrics = resources.getDisplayMetrics();
		    return (double)Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}
	
	
	public void _SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
		
		   layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
		_layout.setLayoutParams(layoutParams);
	}
	
	
	public void _Uri() {
	}
	 public void RequestPermission_Dialog() {
		    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
				      try {
						        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
						        intent.addCategory("android.intent.category.DEFAULT");
						        intent.setData(Uri.parse(String.format("package: ", new Object[]{getApplicationContext().getPackageName()})));
						        startActivityForResult(intent, 2000);
						      } catch (Exception e) {
						        Intent obj = new Intent();
						        obj.setAction(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
						        startActivityForResult(obj, 2000);
						      }
				    } else {
				      androidx.core.app.ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
				    }
		  }
	
	  public boolean permission() {
		    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) { // R is Android 11
				      return Environment.isExternalStorageManager();
				    } else {
				      int write = androidx.core.content.ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
				      int read = androidx.core.content.ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
				
				      return write == android.content.pm.PackageManager.PERMISSION_GRANTED
				          && read == android.content.pm.PackageManager.PERMISSION_GRANTED;
				    }
	} 
	
	// ask permissions request
	
	    
	    public void askPermission(final String _uri) {
			
		
			i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |  Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
					
			i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);				    i.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(_uri));
						        startActivityForResult(i, new_folder);
		}
	
	// check permissions of path if accepted 
	
	
	public boolean checkPermission(final String _uri) {
				Uri muri = Uri.parse(_uri);
				    dFile = androidx.documentfile.provider.DocumentFile.fromTreeUri(getApplicationContext(), muri);
				                    
				if (dFile.canRead() && dFile.canWrite()) {
						return true ;
				}
				return false ;
		}
	
	// simple path to UriTree path
	
	
	public String pathToRealUri( String _path) {
				uriFor1 = "content://com.android.externalstorage.documents/tree/primary%3A";
		
		if ( _path.endsWith("/")) {
			_path = _path.substring(0, _path.length()-1);
		}
		
		
				if (_path.contains("/sdcard/")) {
						uriFor2 = _path.replace("/sdcard/", "").replace("/", "%2F");
						
						if (uriFor2.substring(uriFor2.length()-1, uriFor2.length()).equals("/")) {
								
								uriFor2 = uriFor1.substring(0, uriFor1.length()-1);
								
						}
						
				}
				else {
						if (_path.contains("/storage/") && _path.contains("/emulated/")) {
								uriFor2 = _path.replace("/storage/emulated/0/", "").replace("/", "%2F");
								
								if (uriFor2.substring(uriFor2.length()-1, uriFor2.length()).equals("/")) {
										
										uriFor2 = uriFor1.substring(0, uriFor1.length()-1);
										
								}	
								
						}
						else {
								
						}
				}
				return uriFor1 = uriFor1 + uriFor2;
		}
	
	
	// simple path to UriTree path 2
	
	public String pathToUri( String _path) {
				uriFor1 = "content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3A";
		
		if ( _path.endsWith("/")) {
			_path = _path.substring(0, _path.length()-1);
		}
		
				if (_path.contains("/sdcard/")) {
						uriFor2 = _path.replace("/sdcard/", "").replace("/", "%2F");
						
						if (uriFor2.substring(uriFor2.length()-1, uriFor2.length()).equals("/")) {
								
								uriFor2 = uriFor1.substring(0, uriFor1.length()-1);
								
						}
						
						
				}
				else {
						if (_path.contains("/storage/") && _path.contains("/emulated/")) {
								uriFor2 = _path.replace("/storage/emulated/0/", "").replace("/", "%2F");
								
								if (uriFor2.substring(uriFor2.length()-1, uriFor2.length()).equals("/")) {
										
										uriFor2 = uriFor1.substring(0, uriFor1.length()-1);
										
								}
								
						}
						else {
								
						}
				}
				return uriFor1 = uriFor1 + uriFor2;
		}
	
	// ccopy file from path to path
	
	private boolean copyAsset(final String assetFilename, final Uri targetUri) {
		  			try{
			  				int count;
			  				InputStream input = null;
					OutputStream output = null;
			  				
			  				ContentResolver content = getApplicationContext().getContentResolver();
						  
			            input = getApplicationContext().getAssets().open(assetFilename);
						
			            output = content.openOutputStream(targetUri);
			            
			            
			  				byte data[] = new byte[1024];
			  				while ((count = input.read(data))>0) {
				  					output.write(data, 0, count);
				  			}
			  				output.flush();
			  				output.close();
			  				input.close();
			  				
			  				XUtil.showMessage(getApplicationContext(), "success");
							 
			  		}catch(Exception e){
			  				
			  		FileUtil.writeFile("/sdcard/log.txt", "\n"+ "3   " +e.toString());		XUtil.showMessage(getApplicationContext(), e.toString());
							  return false;
			  		}
		
		return true;
	}
	
	
	
	
	
	private void copyAssetFolder(String  _folder, String _out ) {
		
		
		AssetManager assetManager = getAssets();
		int sizeList = 0;
		    String[] files = null;
		    try {
				        files = assetManager.list(_folder);
				
				    } catch (java.io.IOException e) {
				        
				    }
		final ArrayList<String> str = new ArrayList<>(Arrays.asList(files));
		
		
		
		int nn = 0;
		for(int _repeat12 = 0; _repeat12 < (int)(str.size()); _repeat12++) {
				
				try {
							Uri mUri = Uri.parse(pathToRealUri(_out));
							
							String fileName = str.get((int)nn);
					sizeList = str.size()-1;		
						
						androidx.documentfile.provider.DocumentFile dFile = androidx.documentfile.provider.DocumentFile.fromTreeUri(getApplicationContext(), mUri);
							           Uri mUri2 = Uri.parse(mUri.toString()+ "%2" + fileName);
							          androidx.documentfile.provider.DocumentFile  dFile2 = androidx.documentfile.provider.DocumentFile.fromTreeUri(getApplicationContext(), mUri2);
							            
							  
							
							try {              
										
										androidx.documentfile.provider.DocumentFile file = dFile.findFile(fileName);
										   android.provider.DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), file.getUri());
										
								    android.provider.DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), mUri2);
										
										
							} catch (FileNotFoundException e) {
										                } catch (Exception e2) {
										                }
							
							
							
							dFile2 = dFile.createFile("*/*", fileName);
							            mUri = dFile2.getUri();
							        
							        
							        
							        if (copyAsset(_folder+"/"+fileName, mUri)) {
										    
						if (nn >= sizeList){				    XUtil.showMessage(getApplicationContext(), "️😎✔️");       
					}					        
						} else {
										            
										        
								XUtil.showMessage(getApplicationContext(), "😓❌");
								break;
						}
							
							                
							      } catch (Exception re){}      
				            
				nn++;
		}
		
	}
	
	
	public boolean copy(java.io.File copy, String directory, Context con) {
		     java.io.FileInputStream inStream = null;
		     java.io.OutputStream outStream = null;
		    androidx.documentfile.provider.DocumentFile  dir= androidx.documentfile.provider.DocumentFile.fromTreeUri(getApplicationContext(), Uri.parse(pathToRealUri(directory)));
		    
		 
		try {   
			  String fileN = Uri.parse(copy.getPath()).getLastPathSegment();  
			
			    androidx.documentfile.provider.DocumentFile file = dir.findFile(fileN);
			   android.provider.DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), file.getUri());
			    
			 } catch (Exception e){
			e.printStackTrace();
		}   
		    String mim = mime(copy.toURI().toString());
		    androidx.documentfile.provider.DocumentFile  copy1= dir.createFile(mim, copy.getName());
		    try {
			        inStream = new java.io.FileInputStream(copy);
			        outStream =
			                con.getContentResolver().openOutputStream(copy1.getUri());
			        byte[] buffer = new byte[16384];
			        int bytesRead;
			        while ((bytesRead = inStream.read(buffer)) != -1) {
				            outStream.write(buffer, 0, bytesRead);
				
				        }
			    } catch (java.io.FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (java.io.IOException e) {
			        e.printStackTrace();
			    } finally {
			        try {
				
				            inStream.close();
				
				            outStream.close();
				
				
				            return true;
				
				
				        } catch (java.io.IOException e) {
				            e.printStackTrace();
				        }
			    }
		    return false;
	}
	
	
	
	public  String mime(String URI) {
		       String type = "";
		       String extention = android.webkit.MimeTypeMap.getFileExtensionFromUrl(URI);
		       if (extention != null) {
			           type = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extention);
			       }
		       return type;
		   }
	
	
	
	
	
	private boolean fromStorage = false;
	  final static int REQUEST_CODE = 333;
	  final static  int OLD_REQUEST = 2000;
	  private SharedPreferences sha;
	private Intent i = new Intent();
		private  Uri muri;
		private String uriFor1 = "";
		private String uriFor2 = "";
		private  
		androidx.documentfile.provider.DocumentFile dFile;
		private double PermissionNumber;
		private  static final int new_folder = 43;
	{
	}
	public boolean copyFileFromUri(Context context, Uri fileUri, Uri targetUri)
	    {
		        InputStream inputStream = null;
		        OutputStream outputStream = null;
		
		        try
		        {
			            ContentResolver content = context.getContentResolver();
			            inputStream = content.openInputStream(fileUri);
			
			
				        ParcelFileDescriptor pfd = getApplicationContext().getContentResolver().
				                openFileDescriptor(targetUri, "w");
				        FileOutputStream fileOutputStream =
				                new FileOutputStream(pfd.getFileDescriptor());
				                    byte[] buffer = new byte[1000];
			            int bytesRead = 0;
			            while ( ( bytesRead = inputStream.read( buffer, 0, buffer.length ) ) >= 0 )
			            {
				                 
				fileOutputStream.write(buffer, 0, buffer.length );
				            }
			
			
				        // Let the document provider know you're done by closing the stream.
				        fileOutputStream.close();
				        pfd.close();
			            return true;
				    } catch (Exception e) {
			         
				    }
		        return false;
		    }
	public boolean copyFileFromUri2(Context context, Uri fileUri, Uri targetUri)
	    {
		        		InputStream fis = null;
				OutputStream fos = null;
		
				try {
						
			ContentResolver content = context.getContentResolver();
			            fis = content.openInputStream(fileUri);
			            fos = content.openOutputStream(targetUri);
			
						byte[] buff = new byte[1024];
						int length = 0;
			
						while ((length = fis.read(buff)) > 0) {
								fos.write(buff, 0, length);
						}
				} catch (IOException e) {
						return false;
				} finally {
						if (fis != null) {
								try {
										fis.close();
								} catch (IOException e) {
										return false;
								}
						}
						if (fos != null) {
								try {
										fos.close();
					
								} catch (IOException e) {
										return false;
								}
						}
				}
		return true;
		}
	{
	}
	
	
	public void _CommandBlocks() {
	}
	
	
	public void _MakeRipple(final View _view, final double _shadow, final double _radius, final String _color, final String _ripple) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_radius);
		_view.setElevation((int)_shadow);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_ripple)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_view.setClickable(true);
		_view.setBackground(ripdrb);
	}
	
	
	public void _StorageAccessCheck() {
		if (CanAccessStorage) {
			FileUtil.makeDir("/storage/emulated/0/X-Signer/Keys/");
		}
		else {
			if (!IsBSBeingShow) {
				final com.google.android.material.bottomsheet.BottomSheetDialog BSD = new com.google.android.material.bottomsheet.BottomSheetDialog(MainActivity.this);
				View BSV;
				BSV = getLayoutInflater().inflate(R.layout.permission_bottom_sheet,null );
				BSD.setContentView(BSV);
				BSD.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
				final MaterialButton Button = (MaterialButton) BSV.findViewById(R.id.Button);
				final LinearLayout Handle = (LinearLayout) BSV.findViewById(R.id.Handle);
				final LinearLayout BSBG = (LinearLayout) BSV.findViewById(R.id.BG);
				final LinearLayout MBSBG = (LinearLayout) BSV.findViewById(R.id.MBSBG);
				Button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						BSD.dismiss();
						IsBSBeingShow = false;
						if (Build.VERSION.SDK_INT > 29)  {
							Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
							            intent.setData(Uri.parse("package:" + getPackageName()));
							            startActivity(intent);
						} else {
							requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
						}
					}
				});
				BSD.setCancelable(false);
				BSD.show();
				IsBSBeingShow = true;
				BSD.getWindow().setNavigationBarColor(getColor(R.color.md_theme_surface));
			}
		}
	}
	
	
	public void _SetupUI() {
		navigationBarHeight = 0;
		statusBarHeight= 0;
		int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		if (r1 > 0) {
			    navigationBarHeight = getResources().getDimensionPixelSize(r1);
		}
		int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (r2 > 0) {
			    statusBarHeight = getResources().getDimensionPixelSize(r2);
		}
		_SetMargins(_toolbar, 0, statusBarHeight, 0, 0);
		_SetMargins(_drawer_UserIcon, (int)_DpToPx(23), statusBarHeight, 0, 0);
		collapsingtoolbar.setTitle("X-Signer");
		_toolbar.setElevation((float)0);
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
    	
    }    
        
	
	public void _KeyActivity() {
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
		    Intent intent = new Intent(this, KeysListActivity.class);
		    startActivity(intent, options.toBundle());
	}
	
	
	public String _getTestKeyHash(final String _path, final String _type) {
		try {
			TestKeyHashResult = KeyHashUtils.getTestkeyDigest(new File(_path), _type).toUpperCase();
		} catch (Exception e) {
			TestKeyHashResult = e.toString();
		}
		return (TestKeyHashResult);
	}
	
	
	public String _getKeyHash(final String _path, final String _alias, final String _password, final String _type) {
		try {
			KeyHashResult = KeyHashUtils.getKeyDigest(_path, _password, _alias, _type);
		} catch (Exception e) {
			KeyHashResult = e.toString();
		}
		return (KeyHashResult);
	}
	
	
	public void _SetupListMapKeys() {
		try {
			if (!KeysManager.contains("KeysData")) {
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("keyname", "Testkey");
					KeysList.add(_item);
				}
				
				KeysList.get((int)KeysList.size() - 1).put("alias", "TestKey");
				KeysList.get((int)KeysList.size() - 1).put("type", "pem + pk8");
				KeysList.get((int)KeysList.size() - 1).put("default", "yes");
				KeysList.get((int)KeysList.size() - 1).put("sha1", _getTestKeyHash("/data/data/com.xapps.utility.xsigner/testkey.x509.pem", "SHA-1").toUpperCase());
				KeysList.get((int)KeysList.size() - 1).put("sha256", _getTestKeyHash("/data/data/com.xapps.utility.xsigner/testkey.x509.pem", "SHA-256").toUpperCase());
				KeysList.get((int)KeysList.size() - 1).put("sha512", _getTestKeyHash("/data/data/com.xapps.utility.xsigner/testkey.x509.pem", "SHA-512").toUpperCase());
				KeysList.get((int)KeysList.size() - 1).put("path1", "/data/data/com.xapps.utility.xsigner/testkey.x509.pem");
				KeysList.get((int)KeysList.size() - 1).put("path2", "/data/data/com.xapps.utility.xsigner/testkey.pk8");
				KeysList.get((int)KeysList.size() - 1).put("expiry", _GetTestKeyExpiry("/data/data/com.xapps.utility.xsigner/testkey.x509.pem"));
				KeysManager.edit().putString("KeysData", new Gson().toJson(KeysList)).commit();
			}
		} catch (Exception e) {
			RetryTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_SetupListMapKeys();
						}
					});
				}
			};
			_timer.schedule(RetryTimer, (int)(500));
		}
	}
	
	
	public String _GetReleaseKeyData(final String _path, final String _pass, final String _type) {
		try {
			KeyData = KeyDate.getKeyData(_path, _pass, _type);
		} catch (Exception e) {
			KeyData = e.toString();
		}
		return (KeyData);
	}
	
	
	public String _GetTestKeyExpiry(final String _path) {
		return (TestkeyDate.getTestkeyExpiry(_path));
	}
	
	
	public void _SignWithTestKey(final String _input, final String _output, final String _pem, final String _pk8, final boolean _v1, final boolean _v2, final boolean _v3, final boolean _v4, final boolean _zipalign) {
		try {
			TestKeySigner.signWithTestkey(_input, _output, _pem, _pk8, _v1, _v2, _v3, _v4, _zipalign);
		} catch (Exception e) {
			XUtil.showMessage(getApplicationContext(), e.toString());
		}
	}
	
	
	public void _DialogFragmentSetup() {
	}
	private KeySelectDialogFragmentActivity KeySelectDialogFragmentActivityN;
	private FragmentManager KeySelectDialogFragmentActivityFM;
	public void test_KeySelectDialogFragmentActivity () {
	}
	
	
	public void _SignWithRelease(final String _input, final String _output, final String _keypath, final String _alias, final String _keystorepass, final String _keypass, final String _type, final boolean _v1, final boolean _v2, final boolean _v3, final boolean _v4, final boolean _zipalign) {
        try {
            APKSignerUtils.signFile(_input, _output, _keypath, _alias, _keystorepass, _keypass, _v1, _v2, _v3, _v4, _zipalign, _type);
        } catch (Exception e) {
            XUtil.showMessage(getApplicationContext(), e.toString());
        }
	}
	
	
	public void _SignTest(final String _input, final String _output, final String _pem, final String _pk8, final boolean _v1, final boolean _v2, final boolean _v3, final boolean _v4, final boolean _zipalign) {
		try {
			TestKeySigner.signWithTestkey(_input, _output, _pem, _pk8, _v1, _v2, _v3, _v4, _zipalign);
		} catch (Exception e) {
			XUtil.showMessage(getApplicationContext(), e.toString());
		}
	}
	
	
	public void _SigningLogic() {
		try {
			if (SelectedKey == 0) {
				if (Uri.parse(LowercaseFilerPath).getLastPathSegment().substring((int)(Uri.parse(LowercaseFilerPath).getLastPathSegment().length() - ".".concat("apk").length()), (int)(Uri.parse(LowercaseFilerPath).getLastPathSegment().length())).equals(".".concat("apk"))) {
					_SignWithTestKey(filePath, OutputApk, "/data/data/com.xapps.utility.xsigner/testkey.x509.pem", "/data/data/com.xapps.utility.xsigner/testkey.pk8", true, true, true, true, true);
				}
				if (Uri.parse(LowercaseFilerPath).getLastPathSegment().substring((int)(Uri.parse(LowercaseFilerPath).getLastPathSegment().length() - ".".concat("aab").length()), (int)(Uri.parse(LowercaseFilerPath).getLastPathSegment().length())).equals(".".concat("aab"))) {
					_SignWithTestKey(filePath, OutputAab, "/data/data/com.xapps.utility.xsigner/testkey.x509.pem", "/data/data/com.xapps.utility.xsigner/testkey.pk8", true, true, false, true, true);
				}
			}
			else {
				if (Uri.parse(LowercaseFilerPath).getLastPathSegment().substring((int)(Uri.parse(LowercaseFilerPath).getLastPathSegment().length() - ".".concat("apk").length()), (int)(Uri.parse(LowercaseFilerPath).getLastPathSegment().length())).equals(".".concat("apk"))) {
					if (Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().length() - ".".concat("jks").length()), (int)(Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().length())).equals(".".concat("jks"))) {
						_SignWithRelease(filePath, OutputApk, KeysList.get((int)SelectedKey).get("path").toString(), KeysList.get((int)SelectedKey).get("alias").toString(), KeysList.get((int)SelectedKey).get("keystorepass").toString(), KeysList.get((int)SelectedKey).get("keypass").toString(), "JKS", true, true, true, true, false);
					}
					if (Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().length() - ".".concat("bks").length()), (int)(Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().length())).equals(".".concat("bks"))) {
						_SignWithRelease(filePath, OutputApk, KeysList.get((int)SelectedKey).get("path").toString(), KeysList.get((int)SelectedKey).get("alias").toString(), KeysList.get((int)SelectedKey).get("keystorepass").toString(), KeysList.get((int)SelectedKey).get("keypass").toString(), "BKS", true, true, true, false, false);
					}
					if (Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().substring((int)(Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().length() - ".".concat("pkcs12").length()), (int)(Uri.parse(KeysList.get((int)SelectedKey).get("path").toString().toLowerCase()).getLastPathSegment().length())).equals(".".concat("pkcs12"))) {
						_SignWithRelease(filePath, OutputApk, KeysList.get((int)SelectedKey).get("path").toString(), KeysList.get((int)SelectedKey).get("alias").toString(), KeysList.get((int)SelectedKey).get("keystorepass").toString(), KeysList.get((int)SelectedKey).get("keypass").toString(), "PKCS12", true, true, true, false, false);
					}
				}
				else {
					
				}
			}
		} catch (Exception e) {
			 
		}
	}
	
	
	public boolean _verifyIfSigned(final String _path) {
		return (SignatureVerifier.checkSignatureFilesExist(_path));
	}
	
	
	public boolean _IsInputFileValid() {
		return (SignatureVerifier.isValidZipFile());
	}
	
	
	public void _QuickSigningLogic() {
		IsShownSuccess = false;
		PermissionToGetSize = false;
		IsShownOnce = false;
		IsFileCopied = false;
		IsAlreadySetAsIndeterminate = false;
		PathUri = PickedFileUri;
		String dataPath = "/data/data/"+getApplicationContext().getPackageName();
		
		Cursor returnCursor = getContentResolver().query(PathUri, null, null, null, null);
		    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
		    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
		    returnCursor.moveToFirst();
		    FileName = returnCursor.getString(nameIndex);
		    fileSize = Long.toString(returnCursor.getLong(sizeIndex));
		filePath = dataPath.concat("/".concat(FileName));
		LowercaseFilerPath = filePath.toLowerCase();
		if (LowercaseFilerPath.endsWith(".apk") || LowercaseFilerPath.endsWith(".aab")) {
			FileUtil.deleteFile(filePath);
			AsyncTask.execute(new Runnable() {
				   @Override
				   public void run() {
					try {
						InputStream in = null;
						OutputStream out = null;
						try {
							    in = getContentResolver().openInputStream(PathUri);
							    out = new FileOutputStream(new File(dataPath+"/"+FileName));
							    byte[] buffer = new byte[1024];
							    int len;
							    while ((len = in.read(buffer)) != -1) {
								        out.write(buffer, 0, len);
								    }
						} finally {
							    if (in != null) {
								        in.close();
								    }
							    if (out != null){
								        out.close();
								    }
						}
						IsFileCopied = true;
						  } catch (Exception e) {
						        e.printStackTrace(); 
						    }
					   }
			});
			final AlertDialog FileCopyingDialog = new AlertDialog.Builder(MainActivity.this).create();
			LayoutInflater FileCopyingDialogLI = getLayoutInflater();
			View FileCopyingDialogCV = (View) FileCopyingDialogLI.inflate(R.layout.file_signing_progress, null);
			FileCopyingDialog.setView(FileCopyingDialogCV);
			final com.google.android.material.progressindicator.LinearProgressIndicator progress = (com.google.android.material.progressindicator.LinearProgressIndicator)
			FileCopyingDialogCV.findViewById(R.id.progress);
			FileCopyingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			final TextView Text = (TextView)
			FileCopyingDialogCV.findViewById(R.id.Text);
			Text.setText("Copying file...");
			progress.setProgressCompat((int)0, true);
			progress.setMax((int)Double.parseDouble(fileSize));
			FileSizeTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (FileUtil.isExistFile(filePath)) {
								java.io.File file = new java.io.File(filePath);
								String copy_file_size = String.valueOf(file.length());
								progress.setProgressCompat((int)Double.parseDouble(copy_file_size), true);
							}
							else {
								FileSizeTimer.cancel();
								FailureTimer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												View VipSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
												VipSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
												final LinearLayout SBG = (LinearLayout)
												VipSnackbarView.findViewById(R.id.BG);
												final TextView Message = (TextView)
												VipSnackbarView.findViewById(R.id.Message);
												final ImageView Icon = (ImageView)
												VipSnackbarView.findViewById(R.id.Icon);
												Icon.setImageResource(R.drawable.ic_error_white);
												Message.setText("Failed to copy ".concat(filePath.substring((int)(filePath.length() - 3), (int)(filePath.length())).toUpperCase().concat(" file : Input file being copied no longer exists in app data")));
												SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
												Message.setTextColor(0xFFFFFFFF);
												VipSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
												Snackbar.SnackbarLayout VipSnackbarView2 = (Snackbar.SnackbarLayout) VipSnackbar.getView(); 
												VipSnackbarView2.addView(VipSnackbarView, 0);
												VipSnackbar.show();
												try {
													FileSignProgress.dismiss();
												} catch (Exception e) {
													 
												}
												try {
													FileCopyingDialog.dismiss();
												} catch (Exception e) {
													 
												}
											}
										});
									}
								};
								_timer.schedule(FailureTimer, (int)(1500));
							}
							if (IsFileCopied) {
								FileSizeTimer.cancel();
								HoldOnTimer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												try {
													FileCopyingDialog.dismiss();
												} catch (Exception e) {
													 
												}
												if (!IsShownOnce) {
													KeySelectDialogFragmentActivityN = new KeySelectDialogFragmentActivity();
													KeySelectDialogFragmentActivityN.setCancelable(false);
													KeySelectDialogFragmentActivityN.show(getSupportFragmentManager(),"1");
													IsShownOnce = true;
												}
												GrabTimer = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																if (Tmp.contains("IsCanceled")) {
																	Tmp.edit().remove("IsCanceled").commit();
																	GrabTimer.cancel();
																}
																if (Tmp.contains("position")) {
																	Text.setText("Initializing...");
																	if (!IsAlreadySetAsIndeterminate) {
																		progress.setProgressCompat((int)0, true);
																		IsAlreadySetAsIndeterminate = true;
																	}
																	GrabTimer.cancel();
																	OutputApk = new StringBuilder(new StringBuilder("/storage/emulated/0/X-Signer/".concat(FileName.toLowerCase())).reverse().toString().replaceFirst("kpa.", "kpa.dengis_")).reverse().toString();
																	OutputAab = new StringBuilder(new StringBuilder("/storage/emulated/0/X-Signer/".concat(FileName.toLowerCase())).reverse().toString().replaceFirst("baa.", "baa.dengis_")).reverse().toString();
																	SelectedKey = Double.parseDouble(Tmp.getString("position", ""));
																	Tmp.edit().remove("position").commit();
																	final AlertDialog FileSignProgress = new AlertDialog.Builder(MainActivity.this).create();
																	LayoutInflater FileSignProgressLI = getLayoutInflater();
																	View FileSignProgressCV = (View) FileSignProgressLI.inflate(R.layout.file_signing_progress, null);
																	FileSignProgress.setView(FileSignProgressCV);
																	final com.google.android.material.progressindicator.LinearProgressIndicator progress = (com.google.android.material.progressindicator.LinearProgressIndicator)
																	FileSignProgressCV.findViewById(R.id.progress);
																	FileSignProgress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
																	if (!IsAlreadySetAsIndeterminate) {
																		progress.setProgressCompat((int)0, true);
																		IsAlreadySetAsIndeterminate = true;
																	}
																	java.io.File file = new java.io.File(filePath);
																	String file_size = String.valueOf(file.length());
																	progress.setMax((int)Double.parseDouble(file_size));
																	OutputSizeTimer = new TimerTask() {
																		@Override
																		public void run() {
																			runOnUiThread(new Runnable() {
																				@Override
																				public void run() {
																					if (FileUtil.isExistFile(OutputApk) && IsConfirmed) {
																						java.io.File file = new java.io.File(OutputApk);
																						String file_size = String.valueOf(file.length());
																						progress.setProgressCompat((int)Double.parseDouble(file_size), true);
																						if (_verifyIfSigned(OutputApk) && PermissionToGetSize) {
																							OutputSizeTimer.cancel();
																							IsSigned = true;
																							try {
																								OutputSizeTimer.cancel();
																							} catch (Exception e) {
																								 
																							}
																							FinishTimer = new TimerTask() {
																								@Override
																								public void run() {
																									runOnUiThread(new Runnable() {
																										@Override
																										public void run() {
																											if (!IsShownSuccess) {
																												FileSignProgress.dismiss();
                                                                                                                FileUtil.deleteFile(filePath);                 
																												View VipSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
																												VipSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
																												final LinearLayout SBG = (LinearLayout)
																												VipSnackbarView.findViewById(R.id.BG);
																												final TextView Message = (TextView)
																												VipSnackbarView.findViewById(R.id.Message);
																												final ImageView Icon = (ImageView)
																												VipSnackbarView.findViewById(R.id.Icon);
																												Icon.setImageResource(R.drawable.ic_done_white);
																												Message.setText("File signed successfully at : \n".concat(OutputApk));
																												SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
																												Message.setTextColor(0xFFFFFFFF);
																												VipSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
																												Snackbar.SnackbarLayout VipSnackbarView2 = (Snackbar.SnackbarLayout) VipSnackbar.getView(); 
																												VipSnackbarView2.addView(VipSnackbarView, 0);
																												VipSnackbar.show();
																												IsShownSuccess = true;
																											}
																										}
																									});
																								}
																							};
																							_timer.schedule(FinishTimer, (int)(3000));
																						}
																					}
																					else {
																						if ((!FileUtil.isExistFile(OutputApk) || !FileUtil.isExistFile(filePath)) && PermissionToGetSize) {
																							if (!_verifyIfSigned(OutputApk)) {
																								MaxErrors++;
																								if (MaxErrors == 10) {
																									try {
																										OutputSizeTimer.cancel();
																									} catch (Exception e) {
																										 
																									}
																									FailureTimer = new TimerTask() {
																										@Override
																										public void run() {
																											runOnUiThread(new Runnable() {
																												@Override
																												public void run() {
																													View VipSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
																													VipSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
																													final LinearLayout SBG = (LinearLayout)
																													VipSnackbarView.findViewById(R.id.BG);
																													final TextView Message = (TextView)
																													VipSnackbarView.findViewById(R.id.Message);
																													final ImageView Icon = (ImageView)
																													VipSnackbarView.findViewById(R.id.Icon);
																													Icon.setImageResource(R.drawable.ic_error_white);
																													if (!FileUtil.isExistFile(filePath)) {
																														Message.setText("Failed to sign ".concat(filePath.substring((int)(filePath.length() - 3), (int)(filePath.length())).toUpperCase().concat(" file : Input file no longer exists in app data folder")));
																													}
																													if (!FileUtil.isExistFile(OutputApk)) {
																														Message.setText("Failed to sign ".concat(filePath.substring((int)(filePath.length() - 3), (int)(filePath.length())).toUpperCase().concat(" file : Output file being written no longer exists in Device Storage")));
                                                                                                                        FileUtil.deleteFile(filePath);                
																													}
																													SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
																													Message.setTextColor(0xFFFFFFFF);
																													VipSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
																													Snackbar.SnackbarLayout VipSnackbarView2 = (Snackbar.SnackbarLayout) VipSnackbar.getView(); 
																													VipSnackbarView2.addView(VipSnackbarView, 0);
																													VipSnackbar.show();
																													FileSignProgress.dismiss();
																												}
																											});
																										}
																									};
																									_timer.schedule(FailureTimer, (int)(1500));
																								}
																							}
																						}
																					}
																				}
																			});
																		}
																	};
																	_timer.scheduleAtFixedRate(OutputSizeTimer, (int)(2500), (int)(300));
																	if (FileUtil.isExistFile(OutputApk)) {
																		final AlertDialog OverwriteDialog = new AlertDialog.Builder(MainActivity.this).create();
																		LayoutInflater OverwriteDialogLI = getLayoutInflater();
																		View OverwriteDialogCV = (View) OverwriteDialogLI.inflate(R.layout.material_dialog_layout_custom, null);
																		OverwriteDialog.setView(OverwriteDialogCV);
																		OverwriteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
																		final LinearLayout LeftButton = (LinearLayout)
																		OverwriteDialogCV.findViewById(R.id.LeftButton);
																		final LinearLayout RightButton = (LinearLayout)
																		OverwriteDialogCV.findViewById(R.id.RightButton);
																		LeftButton.setOnClickListener(new View.OnClickListener() {
																			@Override
																			public void onClick(View _view) {
																				OutputSizeTimer.cancel();
																				OverwriteDialog.dismiss();
																			}
																		});
																		RightButton.setOnClickListener(new View.OnClickListener() {
																			@Override
																			public void onClick(View _view) {
																				BeginTimer = new TimerTask() {
																					@Override
																					public void run() {
																						runOnUiThread(new Runnable() {
																							@Override
																							public void run() {
																								IsConfirmed = true;
																							}
																						});
																					}
																				};
																				_timer.schedule(BeginTimer, (int)(2000));
																				OverwriteDialog.dismiss();
																				FileSignProgress.setCancelable(false);
																				FileSignProgress.show();
																				FileUtil.deleteFile(OutputApk);
																				ProcessingTimer = new TimerTask() {
																					@Override
																					public void run() {
																						runOnUiThread(new Runnable() {
																							@Override
																							public void run() {
																								if (FileUtil.isExistFile(filePath)) {
																									FileUtil.makeDir("/storage/emulated/0/X-Signer/");
																									AsyncTask.execute(new Runnable() {
																										   @Override
																										   public void run() {
																											_SigningLogic();
																											PermissionToGetSize = true;
																											IsConfirmed = true;
																											   }
																									});
																								}
																								else {
																									OutputSizeTimer.cancel();
																									FailureTimer = new TimerTask() {
																										@Override
																										public void run() {
																											runOnUiThread(new Runnable() {
																												@Override
																												public void run() {
																													View VipSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
																													VipSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
																													final LinearLayout SBG = (LinearLayout)
																													VipSnackbarView.findViewById(R.id.BG);
																													final TextView Message = (TextView)
																													VipSnackbarView.findViewById(R.id.Message);
																													final ImageView Icon = (ImageView)
																													VipSnackbarView.findViewById(R.id.Icon);
																													Icon.setImageResource(R.drawable.ic_error_white);
																													Message.setText("Failed to sign ".concat(filePath.substring((int)(filePath.length() - 3), (int)(filePath.length())).toUpperCase().concat(" file : Input file no longer exists in app data folder")));
																													SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
																													Message.setTextColor(0xFFFFFFFF);
																													VipSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
																													Snackbar.SnackbarLayout VipSnackbarView2 = (Snackbar.SnackbarLayout) VipSnackbar.getView(); 
																													VipSnackbarView2.addView(VipSnackbarView, 0);
																													VipSnackbar.show();
																													FileSignProgress.dismiss();
																												}
																											});
																										}
																									};
																									_timer.schedule(FailureTimer, (int)(1500));
																								}
																							}
																						});
																					}
																				};
																				_timer.schedule(ProcessingTimer, (int)(2000));
																			}
																		});
																		OverwriteDialog.setCancelable(false);
																		OverwriteDialog.show();
																	}
																	else {
																		PermissionToGetSize = true;
																		FileSignProgress.setCancelable(false);
																		FileSignProgress.show();
																		ProcessingTimer = new TimerTask() {
																			@Override
																			public void run() {
																				runOnUiThread(new Runnable() {
																					@Override
																					public void run() {
																						if (FileUtil.isExistFile(filePath)) {
																							FileUtil.makeDir("/storage/emulated/0/X-Signer/");
																							AsyncTask.execute(new Runnable() {
																								   @Override
																								   public void run() {
																									_SigningLogic();
																									   }
																							});
																						}
																						else {
																							OutputSizeTimer.cancel();
																							FailureTimer = new TimerTask() {
																								@Override
																								public void run() {
																									runOnUiThread(new Runnable() {
																										@Override
																										public void run() {
																											View VipSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
																											VipSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
																											final LinearLayout SBG = (LinearLayout)
																											VipSnackbarView.findViewById(R.id.BG);
																											final TextView Message = (TextView)
																											VipSnackbarView.findViewById(R.id.Message);
																											final ImageView Icon = (ImageView)
																											VipSnackbarView.findViewById(R.id.Icon);
																											Icon.setImageResource(R.drawable.ic_error_white);
																											Message.setText("Failed to sign ".concat(filePath.substring((int)(filePath.length() - 3), (int)(filePath.length())).toUpperCase().concat(" file : Input file no longer exists in app data folder")));
																											SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
																											Message.setTextColor(0xFFFFFFFF);
																											VipSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
																											Snackbar.SnackbarLayout VipSnackbarView2 = (Snackbar.SnackbarLayout) VipSnackbar.getView(); 
																											VipSnackbarView2.addView(VipSnackbarView, 0);
																											VipSnackbar.show();
																											FileSignProgress.dismiss();
																										}
																									});
																								}
																							};
																							_timer.schedule(FailureTimer, (int)(1500));
																						}
																					}
																				});
																			}
																		};
																		_timer.schedule(ProcessingTimer, (int)(2500));
																	}
																}
															}
														});
													}
												};
												_timer.scheduleAtFixedRate(GrabTimer, (int)(0), (int)(100));
											}
										});
									}
								};
								_timer.schedule(HoldOnTimer, (int)(1500));
							}
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(FileSizeTimer, (int)(1500), (int)(500));
			FileCopyingDialog.setCancelable(false);
			FileCopyingDialog.show();
		}
		else {
			View VipSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
			VipSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
			final LinearLayout SBG = (LinearLayout)
			VipSnackbarView.findViewById(R.id.BG);
			final TextView Message = (TextView)
			VipSnackbarView.findViewById(R.id.Message);
			final ImageView Icon = (ImageView)
			VipSnackbarView.findViewById(R.id.Icon);
			Icon.setImageResource(R.drawable.ic_error_white);
			Message.setText("Only APK/AAB files can be signed");
			SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
			Message.setTextColor(0xFFFFFFFF);
			VipSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
			Snackbar.SnackbarLayout VipSnackbarView2 = (Snackbar.SnackbarLayout) VipSnackbar.getView(); 
			VipSnackbarView2.addView(VipSnackbarView, 0);
			VipSnackbar.show();
		}
	}
	
	
	public void _InfoActivity() {
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
		
		    Intent intent = new Intent(this, InfoActivity.class);
		    startActivity(intent, options.toBundle());
	}
	
	
	public boolean _isAabFile(final String _path) {
		return (ZipVerifier.isAabFile(_path));
	}
	
	
	public boolean _isApkFile(final String _path) {
		return (ZipVerifier.isApkFile(_path));
	}
	
	
	public void _FaqActivity() {
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
		Intent intent = new Intent(this, FaqActivity.class);
		startActivity(intent, options.toBundle());
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
