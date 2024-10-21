package com.xapps.utility.xsigner;

import android.Manifest;
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
import android.net.Uri;
import android.os.*;
import android.sun.security.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import androidx.transition.*;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialArcMotion;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.android.material.internal.EdgeToEdgeUtils;
import androidx.core.widget.NestedScrollView;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import androidx.core.widget.NestedScrollView;

public class UpdateActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private boolean isLifted = false;
	private boolean IsInternetWorking = false;
	private boolean isMetered = false;
	private String statusCode = "";
	private boolean DataFetched = false;
	private String versionName = "";
	private String versionCode = "";
	private boolean isTf1R = false;
	private boolean isTf2R = false;
	private boolean isTf3R = false;
	private boolean isTf4R = false;
	private boolean isTf5R = false;
	private boolean isTF6R = false;
	private boolean isTF7R = false;
	private boolean isTF8R = false;
	private String Map = "";
	
	private ArrayList<HashMap<String, Object>> UpdateDataMap = new ArrayList<>();
	
	private SwipeRefreshLayout swiperefreshlayout;
	private NestedScrollView Scroller;
	private LinearLayout Container;
	private LinearLayout VersionRelatedLinear;
	private LinearLayout NewLatestLinear;
	private LinearLayout VRTopLinear;
	private LinearLayout CurrentLinear;
	private LinearLayout StatusLinear;
	private TextView VersionDetailsTitle;
	private TextView CurrentTitle;
	private TextView VRDetail;
	private TextView Status;
	private TextView StatusDetail;
	private LinearLayout NewLatestTopLinear;
	private LinearLayout LatestLinear;
	private LinearLayout ChangelogLinear;
	private TextView NewLatestTitle;
	private TextView LatestTitle;
	private TextView LatestDetail;
	private LinearLayout MainNewHeader;
	private LinearLayout ChangelogTopLinear;
	private TextView ChangeLogDetail;
	private LinearLayout WhatsNewContainer;
	private TextView WhatsNewTitle;
	private LinearLayout LatestPhaseLinear;
	private LinearLayout Space;
	private LinearLayout StatusContainer;
	private TextView LatestPhaseTitle;
	private TextView StatusTitle;
	
	private TimerTask LiftTimer;
	private com.google.android.material.snackbar.Snackbar OfflineSnackbar;
	private DatabaseReference UpdateChecker = _firebase.getReference("UpdateData");
	private ChildEventListener _UpdateChecker_child_listener;
	private TimerTask RefreshTimer;
	private FirebaseAuth LoginData;
	private OnCompleteListener<AuthResult> _LoginData_create_user_listener;
	private OnCompleteListener<AuthResult> _LoginData_sign_in_listener;
	private OnCompleteListener<Void> _LoginData_reset_password_listener;
	private OnCompleteListener<Void> LoginData_updateEmailListener;
	private OnCompleteListener<Void> LoginData_updatePasswordListener;
	private OnCompleteListener<Void> LoginData_emailVerificationSentListener;
	private OnCompleteListener<Void> LoginData_deleteUserListener;
	private OnCompleteListener<Void> LoginData_updateProfileListener;
	private OnCompleteListener<AuthResult> LoginData_phoneAuthListener;
	private OnCompleteListener<AuthResult> LoginData_googleSignInListener;
	
	private TimerTask FailureTimer;
	private TimerTask RetardTimer;
	private ObjectAnimator TextFader = new ObjectAnimator();
	private TimerTask TextTimer;
	private ObjectAnimator TextFader2 = new ObjectAnimator();
	private ObjectAnimator TextFader3 = new ObjectAnimator();
	private ObjectAnimator TextFader4 = new ObjectAnimator();
	private ObjectAnimator TextFader5 = new ObjectAnimator();
	private TimerTask NonNullTimer;
	private ObjectAnimator TextFader6 = new ObjectAnimator();
	private ObjectAnimator TextFader7 = new ObjectAnimator();
	private ObjectAnimator TextFader8 = new ObjectAnimator();
	private StorageReference Downloader = _firebase_storage.getReference("/");
	private OnCompleteListener<Uri> _Downloader_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _Downloader_download_success_listener;
	private OnSuccessListener _Downloader_delete_success_listener;
	private OnProgressListener _Downloader_upload_progress_listener;
	private OnProgressListener _Downloader_download_progress_listener;
	private OnFailureListener _Downloader_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
       _SetupMaterialSharedTransition();
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.update);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
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
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		swiperefreshlayout = findViewById(R.id.swiperefreshlayout);
		Scroller = findViewById(R.id.Scroller);
		Container = findViewById(R.id.Container);
		VersionRelatedLinear = findViewById(R.id.VersionRelatedLinear);
		NewLatestLinear = findViewById(R.id.NewLatestLinear);
		VRTopLinear = findViewById(R.id.VRTopLinear);
		CurrentLinear = findViewById(R.id.CurrentLinear);
		StatusLinear = findViewById(R.id.StatusLinear);
		VersionDetailsTitle = findViewById(R.id.VersionDetailsTitle);
		CurrentTitle = findViewById(R.id.CurrentTitle);
		VRDetail = findViewById(R.id.VRDetail);
		Status = findViewById(R.id.Status);
		StatusDetail = findViewById(R.id.StatusDetail);
		NewLatestTopLinear = findViewById(R.id.NewLatestTopLinear);
		LatestLinear = findViewById(R.id.LatestLinear);
		ChangelogLinear = findViewById(R.id.ChangelogLinear);
		NewLatestTitle = findViewById(R.id.NewLatestTitle);
		LatestTitle = findViewById(R.id.LatestTitle);
		LatestDetail = findViewById(R.id.LatestDetail);
		MainNewHeader = findViewById(R.id.MainNewHeader);
		ChangelogTopLinear = findViewById(R.id.ChangelogTopLinear);
		ChangeLogDetail = findViewById(R.id.ChangeLogDetail);
		WhatsNewContainer = findViewById(R.id.WhatsNewContainer);
		WhatsNewTitle = findViewById(R.id.WhatsNewTitle);
		LatestPhaseLinear = findViewById(R.id.LatestPhaseLinear);
		Space = findViewById(R.id.Space);
		StatusContainer = findViewById(R.id.StatusContainer);
		LatestPhaseTitle = findViewById(R.id.LatestPhaseTitle);
		StatusTitle = findViewById(R.id.StatusTitle);
		LoginData = FirebaseAuth.getInstance();
		
		swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				RetardTimer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								NetworkRequest networkRequest = new NetworkRequest.Builder()
								        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
								        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
								        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
								        .build();
								
								ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(ConnectivityManager.class);
								connectivityManager.requestNetwork(networkRequest, networkCallback);
							}
						});
					}
				};
				_timer.schedule(RetardTimer, (int)(500));
				RefreshTimer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								if (IsInternetWorking) {
									UpdateChecker.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											UpdateDataMap = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													UpdateDataMap.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											DataFetched = true;
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
									RefreshTimer = new TimerTask() {
										@Override
										public void run() {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													swiperefreshlayout.setRefreshing(false);
													if (DataFetched) {
														SketchwareUtil.showMessage(getApplicationContext(), UpdateDataMap.get((int)0).get("Latest").toString());
													}
													else {
														View OfflineSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
														OfflineSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
														final LinearLayout SBG = (LinearLayout)
														OfflineSnackbarView.findViewById(R.id.BG);
														final TextView Message = (TextView)
														OfflineSnackbarView.findViewById(R.id.Message);
														final ImageView Icon = (ImageView)
														OfflineSnackbarView.findViewById(R.id.Icon);
														Icon.setImageResource(R.drawable.ic_error_white);
														Message.setText("Couldn't fetch data from server, please verify your internet connection and try again");
														SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
														Message.setTextColor(0xFFFFFFFF);
														OfflineSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
														Snackbar.SnackbarLayout OfflineSnackbarView2 = (Snackbar.SnackbarLayout) OfflineSnackbar.getView(); 
														OfflineSnackbarView2.addView(OfflineSnackbarView, 0);
														OfflineSnackbar.show();
													}
												}
											});
										}
									};
									_timer.schedule(RefreshTimer, (int)(1500));
								}
								else {
									swiperefreshlayout.setRefreshing(false);
									View OfflineSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
									OfflineSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
									final LinearLayout SBG = (LinearLayout)
									OfflineSnackbarView.findViewById(R.id.BG);
									final TextView Message = (TextView)
									OfflineSnackbarView.findViewById(R.id.Message);
									final ImageView Icon = (ImageView)
									OfflineSnackbarView.findViewById(R.id.Icon);
									Icon.setImageResource(R.drawable.ic_error_white);
									Message.setText("No internet connection is available, unable to fetch data from server.");
									SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
									Message.setTextColor(0xFFFFFFFF);
									OfflineSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
									Snackbar.SnackbarLayout OfflineSnackbarView2 = (Snackbar.SnackbarLayout) OfflineSnackbar.getView(); 
									OfflineSnackbarView2.addView(OfflineSnackbarView, 0);
									OfflineSnackbar.show();
								}
							}
						});
					}
				};
				_timer.schedule(RefreshTimer, (int)(1000));
			}
		});
		
		//ScrollChange2
		Scroller.setOnScrollChangeListener(new ScrollView.OnScrollChangeListener() {
			@Override
			public void onScrollChange(View v, int _scrollX, int _scrollY, int _oldScrollX, int _oldScrollY) {
				if (_scrollY == 0) {
					if (isLifted) {
						animateColorChange(getColor(R.color.colorToolbarLifted), getColor(R.color.colorPrimaryDark));
						isLifted = false;
					}
				}
				else {
					if (!isLifted) {
						animateColorChange(getColor(R.color.colorPrimaryDark), getColor(R.color.colorToolbarLifted));
						isLifted = true;
					}
				}
			}
		});
		
		LatestDetail.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		_UpdateChecker_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		UpdateChecker.addChildEventListener(_UpdateChecker_child_listener);
		
		_Downloader_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_Downloader_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_Downloader_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_Downloader_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				DataFetched = true;
				SketchwareUtil.showMessage(getApplicationContext(), FileUtil.readFile("/storage/emulated/0/test"));
				Map = FileUtil.readFile("/storage/emulated/0/test");
				String[] hi = Map.split(",");
				for (int i = 0; i < (int)(hi.length); i++) {
					SketchwareUtil.showMessage(getApplicationContext(), hi[i]);
				}
			}
		};
		
		_Downloader_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_Downloader_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		LoginData_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		LoginData_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		LoginData_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		LoginData_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		LoginData_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		LoginData_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		LoginData_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_LoginData_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_LoginData_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_LoginData_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		Map = FileUtil.readFile("/storage/emulated/0/test");
		String[] hi = Map.split("NEXTITEM");
		for(int _repeat151 = 0; _repeat151 < (int)(hi.length); _repeat151++) {
			SketchwareUtil.showMessage(getApplicationContext(), hi[_repeat151]);
		}
		setTitle("Check for updates");
		_SetupUi();
		NetworkRequest networkRequest = new NetworkRequest.Builder()
		        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
		        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
		        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
		        .build();
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(ConnectivityManager.class);
		connectivityManager.requestNetwork(networkRequest, networkCallback);
		_firebase_storage.getReferenceFromUrl("gs://x-signer-xapps.appspot.com/wow.txt").getFile(new File("/storage/emulated/0/test")).addOnSuccessListener(_Downloader_download_success_listener).addOnFailureListener(_Downloader_failure_listener).addOnProgressListener(_Downloader_download_progress_listener);
		RefreshTimer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (IsInternetWorking) {
							swiperefreshlayout.setRefreshing(true);
							RefreshTimer = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (DataFetched) {
												
											}
											else {
												swiperefreshlayout.setRefreshing(false);
												View OfflineSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
												OfflineSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
												final LinearLayout SBG = (LinearLayout)
												OfflineSnackbarView.findViewById(R.id.BG);
												final TextView Message = (TextView)
												OfflineSnackbarView.findViewById(R.id.Message);
												final ImageView Icon = (ImageView)
												OfflineSnackbarView.findViewById(R.id.Icon);
												Icon.setImageResource(R.drawable.ic_error_white);
												Message.setText("Couldn't fetch data from server, please verify your internet connection and try again");
												SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
												Message.setTextColor(0xFFFFFFFF);
												OfflineSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
												Snackbar.SnackbarLayout OfflineSnackbarView2 = (Snackbar.SnackbarLayout) OfflineSnackbar.getView(); 
												OfflineSnackbarView2.addView(OfflineSnackbarView, 0);
												OfflineSnackbar.show();
											}
										}
									});
								}
							};
							_timer.schedule(RefreshTimer, (int)(7500));
						}
						else {
							View OfflineSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
							OfflineSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
							final LinearLayout SBG = (LinearLayout)
							OfflineSnackbarView.findViewById(R.id.BG);
							final TextView Message = (TextView)
							OfflineSnackbarView.findViewById(R.id.Message);
							final ImageView Icon = (ImageView)
							OfflineSnackbarView.findViewById(R.id.Icon);
							Icon.setImageResource(R.drawable.ic_error_white);
							Message.setText("No internet connection is available, unable to fetch data from server.");
							SBG.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)_DpToPx(10), 0xFF27313A));
							Message.setTextColor(0xFFFFFFFF);
							OfflineSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
							Snackbar.SnackbarLayout OfflineSnackbarView2 = (Snackbar.SnackbarLayout) OfflineSnackbar.getView(); 
							OfflineSnackbarView2.addView(OfflineSnackbarView, 0);
							OfflineSnackbar.show();
						}
					}
				});
			}
		};
		_timer.schedule(RefreshTimer, (int)(500));
	}
	
	private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
		    @Override
		    public void onAvailable(@NonNull Network network) {
			        super.onAvailable(network);
			IsInternetWorking= true;
			    }
		
		    @Override
		    public void onLost(@NonNull Network network) {
			        super.onLost(network);
			IsInternetWorking = false;
			    }
		
		    @Override
		    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
			        super.onCapabilitiesChanged(network, networkCapabilities);
			        final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
			if (unmetered) {
				isMetered = false;
			} else {
				isMetered = true;
			}
			    }
	};
	
	{
		_FetchVersion();
	}
	
	public void _CommandBlock() {
	}
	
	
	public void _SetupUi() {
		_coordinator.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		
		swiperefreshlayout.setColorSchemeColors(getColor(R.color.colorAccent));
		
		swiperefreshlayout.setProgressBackgroundColorSchemeColor(getColor(R.color.colorToolbarLifted));
		
		_toolbar.setTitleTextColor(getColor(R.color.colorTextMain));
		_app_bar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_toolbar.setBackgroundColor(getColor(R.color.colorPrimaryDark));
		_toolbar.getNavigationIcon().setColorFilter(getColor(R.color.colorTextMain), PorterDuff.Mode.SRC_IN);
		int nightModeMask = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		if (nightModeMask == android.content.res.Configuration.UI_MODE_NIGHT_YES) {		
					_Radius(VersionRelatedLinear, _DpToPx(20), _DpToPx(20), _DpToPx(20), _DpToPx(20), "#17242D");
			_Radius(NewLatestLinear, _DpToPx(20), _DpToPx(20), _DpToPx(20), _DpToPx(20), "#17242D");
		} else {
					getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			_Radius(VersionRelatedLinear, _DpToPx(20), _DpToPx(20), _DpToPx(20), _DpToPx(20), "#EFF4F8");
			_Radius(NewLatestLinear, _DpToPx(20), _DpToPx(20), _DpToPx(20), _DpToPx(20), "#EFF4F8");
		}
		EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
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
		WhatsNewContainer.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)500, getColor(R.color.colorDrawerSelected)));
		_SetMargins(_toolbar, 0, statusBarHeight, 0, 0);
	}
	
	
	public void _SetupMaterialSharedTransition() {
		findViewById(android.R.id.content).setTransitionName("transition2");
		setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
		setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
		getWindow().setSharedElementEnterTransition(buildContainerTransform(true));
		getWindow().setSharedElementReturnTransition(buildContainerTransform(false));
	}
	
	private MaterialContainerTransform buildContainerTransform(boolean entering) {
		MaterialContainerTransform transform = new MaterialContainerTransform();
		if (entering) {
			    transform.setFadeMode(MaterialContainerTransform.FADE_MODE_IN);
			    transform.setDuration(750L);
		} else {
			    transform.setFadeMode(MaterialContainerTransform.FADE_MODE_OUT); 
			    transform.setDuration(400L);
		}
		    transform.setAllContainerColors(getColor(R.color.colorPrimaryDark));
		    transform.setScrimColor(getColor(R.color.colorPrimaryDark));
		    transform.addTarget(android.R.id.content);
		return transform;
	}
	{
	}
	
	
	public void _SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
		ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();
		
		   layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
		_layout.setLayoutParams(layoutParams);
	}
	
	
	private void animateColorChange(int startColor, int endColor) {
		    ValueAnimator colorAnimation = ValueAnimator.ofArgb(startColor, endColor);
		    colorAnimation.setDuration(150);
		    colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			        @Override
			        public void onAnimationUpdate(ValueAnimator animator) {
				            _app_bar.setBackgroundColor((int)animator.getAnimatedValue());
				_toolbar.setBackgroundColor((int) animator.getAnimatedValue());
				        }
			    });
		    colorAnimation.start();
	}
	
	{
	}
	
	
	public double _DpToPx(final double _dp) {
		  Resources resources = this.getResources();
		    DisplayMetrics metrics = resources.getDisplayMetrics();
		    return (double)Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}
	
	
	public void _ChangeTextWithFade(final TextView _textview, final String _text) {
		if (!isTf1R) {
			isTf1R = true;
			TextFader.setTarget(_textview);
			TextFader.setPropertyName("alpha");
			TextFader.setFloatValues((float)(0));
			TextFader.setDuration((int)(150));
			TextFader.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader.setTarget(_textview);
							TextFader.setPropertyName("alpha");
							TextFader.setFloatValues((float)(1));
							TextFader.setDuration((int)(150));
							TextFader.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf2R) {
			isTf2R = true;
			TextFader2.setTarget(_textview);
			TextFader2.setPropertyName("alpha");
			TextFader2.setFloatValues((float)(0));
			TextFader2.setDuration((int)(150));
			TextFader2.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader2.setTarget(_textview);
							TextFader2.setPropertyName("alpha");
							TextFader2.setFloatValues((float)(1));
							TextFader2.setDuration((int)(150));
							TextFader2.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf3R) {
			isTf3R = true;
			TextFader3.setTarget(_textview);
			TextFader3.setPropertyName("alpha");
			TextFader3.setFloatValues((float)(0));
			TextFader3.setDuration((int)(150));
			TextFader3.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader3.setTarget(_textview);
							TextFader3.setPropertyName("alpha");
							TextFader3.setFloatValues((float)(1));
							TextFader3.setDuration((int)(150));
							TextFader3.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf4R) {
			isTf4R = true;
			TextFader4.setTarget(_textview);
			TextFader4.setPropertyName("alpha");
			TextFader4.setFloatValues((float)(0));
			TextFader4.setDuration((int)(150));
			TextFader4.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader4.setTarget(_textview);
							TextFader4.setPropertyName("alpha");
							TextFader4.setFloatValues((float)(1));
							TextFader4.setDuration((int)(150));
							TextFader4.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf5R) {
			isTf5R = true;
			TextFader5.setTarget(_textview);
			TextFader5.setPropertyName("alpha");
			TextFader5.setFloatValues((float)(0));
			TextFader5.setDuration((int)(150));
			TextFader5.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader5.setTarget(_textview);
							TextFader5.setPropertyName("alpha");
							TextFader5.setFloatValues((float)(1));
							TextFader5.setDuration((int)(150));
							TextFader5.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTF6R) {
			isTF6R = true;
			TextFader6.setTarget(_textview);
			TextFader6.setPropertyName("alpha");
			TextFader6.setFloatValues((float)(0));
			TextFader6.setDuration((int)(150));
			TextFader6.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader6.setTarget(_textview);
							TextFader6.setPropertyName("alpha");
							TextFader6.setFloatValues((float)(1));
							TextFader6.setDuration((int)(150));
							TextFader6.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTF7R) {
			isTF7R = true;
			TextFader7.setTarget(_textview);
			TextFader7.setPropertyName("alpha");
			TextFader7.setFloatValues((float)(0));
			TextFader7.setDuration((int)(150));
			TextFader7.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader7.setTarget(_textview);
							TextFader7.setPropertyName("alpha");
							TextFader7.setFloatValues((float)(1));
							TextFader7.setDuration((int)(150));
							TextFader7.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTF8R) {
			isTF8R = true;
			TextFader8.setTarget(_textview);
			TextFader8.setPropertyName("alpha");
			TextFader8.setFloatValues((float)(0));
			TextFader8.setDuration((int)(150));
			TextFader8.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							TextFader8.setTarget(_textview);
							TextFader8.setPropertyName("alpha");
							TextFader8.setFloatValues((float)(1));
							TextFader8.setDuration((int)(150));
							TextFader8.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
	}
	
	
	public void _ChangeTextWithFadeAndColor(final TextView _textview, final String _text, final int _color) {
		if (!isTf1R) {
			isTf1R = true;
			TextFader.setTarget(_textview);
			TextFader.setPropertyName("alpha");
			TextFader.setFloatValues((float)(0));
			TextFader.setDuration((int)(150));
			TextFader.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setTextColor(_color);
							_textview.setText(_text);
							TextFader.setTarget(_textview);
							TextFader.setPropertyName("alpha");
							TextFader.setFloatValues((float)(1));
							TextFader.setDuration((int)(150));
							TextFader.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf2R) {
			isTf2R = true;
			TextFader2.setTarget(_textview);
			TextFader2.setPropertyName("alpha");
			TextFader2.setFloatValues((float)(0));
			TextFader2.setDuration((int)(150));
			TextFader2.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setTextColor(_color);
							_textview.setText(_text);
							TextFader2.setTarget(_textview);
							TextFader2.setPropertyName("alpha");
							TextFader2.setFloatValues((float)(1));
							TextFader2.setDuration((int)(150));
							TextFader2.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf3R) {
			isTf3R = true;
			TextFader3.setTarget(_textview);
			TextFader3.setPropertyName("alpha");
			TextFader3.setFloatValues((float)(0));
			TextFader3.setDuration((int)(150));
			TextFader3.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setTextColor(_color);
							_textview.setText(_text);
							TextFader3.setTarget(_textview);
							TextFader3.setPropertyName("alpha");
							TextFader3.setFloatValues((float)(1));
							TextFader3.setDuration((int)(150));
							TextFader3.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf4R) {
			TextFader4.setTarget(_textview);
			TextFader4.setPropertyName("alpha");
			TextFader4.setFloatValues((float)(0));
			TextFader4.setDuration((int)(150));
			TextFader4.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setTextColor(_color);
							_textview.setText(_text);
							TextFader4.setTarget(_textview);
							TextFader4.setPropertyName("alpha");
							TextFader4.setFloatValues((float)(1));
							TextFader4.setDuration((int)(150));
							TextFader4.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTf5R) {
			TextFader5.setTarget(_textview);
			TextFader5.setPropertyName("alpha");
			TextFader5.setFloatValues((float)(0));
			TextFader5.setDuration((int)(150));
			TextFader5.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							_textview.setTextColor(_color);
							TextFader5.setTarget(_textview);
							TextFader5.setPropertyName("alpha");
							TextFader5.setFloatValues((float)(1));
							TextFader5.setDuration((int)(150));
							TextFader5.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTF6R) {
			isTF6R = true;
			TextFader6.setTarget(_textview);
			TextFader6.setPropertyName("alpha");
			TextFader6.setFloatValues((float)(0));
			TextFader6.setDuration((int)(150));
			TextFader6.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							_textview.setTextColor(_color);
							TextFader6.setTarget(_textview);
							TextFader6.setPropertyName("alpha");
							TextFader6.setFloatValues((float)(1));
							TextFader6.setDuration((int)(150));
							TextFader6.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTF7R) {
			isTF7R = true;
			TextFader7.setTarget(_textview);
			TextFader7.setPropertyName("alpha");
			TextFader7.setFloatValues((float)(0));
			TextFader7.setDuration((int)(150));
			TextFader7.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							_textview.setTextColor(_color);
							TextFader7.setTarget(_textview);
							TextFader7.setPropertyName("alpha");
							TextFader7.setFloatValues((float)(1));
							TextFader7.setDuration((int)(150));
							TextFader7.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
		if (!isTF8R) {
			isTF8R = true;
			TextFader8.setTarget(_textview);
			TextFader8.setPropertyName("alpha");
			TextFader8.setFloatValues((float)(0));
			TextFader8.setDuration((int)(150));
			TextFader8.start();
			TextTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_textview.setText(_text);
							_textview.setTextColor(_color);
							TextFader8.setTarget(_textview);
							TextFader8.setPropertyName("alpha");
							TextFader8.setFloatValues((float)(1));
							TextFader8.setDuration((int)(150));
							TextFader8.start();
						}
					});
				}
			};
			_timer.schedule(TextTimer, (int)(200));
		}
	}
	
	
	public void _Radius(final View _view, final double _lefttop, final double _righttop, final double _leftbottom, final double _rightbottom, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadii(new float[] { (float)_lefttop, (float)_lefttop, (float)_righttop, (float)_righttop, (float)_rightbottom, (float)_rightbottom, (float)_leftbottom, (float)_leftbottom });
		_view.setBackground(gd);
	}
	
	
	public void _FetchVersion() {
		try {
			VRDetail.setText(getIntent().getStringExtra("version"));
		} catch (Exception e) {
			NonNullTimer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_FetchVersion();
						}
					});
				}
			};
			_timer.schedule(NonNullTimer, (int)(50));
		}
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