package com.xapps.utility.xsigner;

import android.Manifest;
import android.opengl.Visibility;
import android.transition.TransitionManager;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.sun.security.*;
import android.text.*;
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
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.savedstate.*;
import androidx.startup.*;
import androidx.transition.*;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyo.expandablelayout.ExpandableLayout;
import com.mursaat.extendedtextview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.transition.platform.MaterialSharedAxis;
import android.view.animation.LinearInterpolator;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.internal.EdgeToEdgeUtils;
import androidx.core.app.ActivityOptionsCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import android.database.Cursor;
import android.provider.OpenableColumns;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import android.transition.TransitionManager;


public class KeysListActivity extends AppCompatActivity {

    private Timer _timer = new Timer();

    private ExtendedFloatingActionButton _fab2;
    private Toolbar _toolbar;
    private AppBarLayout _app_bar;
    private CoordinatorLayout _coordinator;
    private ExtendedFloatingActionButton _fab;
    private int statusBarHeight;
    private int navigationBarHeight;
    private boolean DismissImportDialog = false;
    private boolean DismissLoadingDialog = false;
    private String error = "";
    private boolean LooperReady = false;
    private boolean KnownError = false;
    private boolean IsSent = false;
    private boolean IsNotSent = false;
    private boolean IsSupportedKey = false;
    private String path = "";
    private String KeyHashResult = "";
    private String Alias = "";
    private String AliasKey = "";
    private String KeystorePass = "";
    private String KeyFileName = "";
    private String KeyFilePath = "";
    private String KeyData = "";
    private int NeededMargin;
    private String LowerCasePath = "";
    private double PositionToDelete = 0;
    private boolean IsItemDelted = false;
    private boolean IsItemAdded = false;
    private String ToShareFileName = "";
    private boolean NeedToRefresh = false;
    private int OldMargin;
    private boolean isApplied = false;
    private boolean isApplied2 = false;
    private int FabMarginToUse;
    private boolean IsPkcs12 = false;
    private ValueAnimator animator;
    private Handler NonNullHandler = new Handler(Looper.getMainLooper());
    private double CurrentHeight = 0;
    private double TargetHeight = 0;
    private int targetHeight;
    private int targetHeight2;
    private boolean IsExpanded = false;

    private ArrayList < HashMap < String, Object >> KeysMap = new ArrayList < > ();

    private CollapsingToolbarLayout collapsingtoolbar;
    private LinearLayout LoadingLinear;
    private RecyclerView recyclerview;
    private CircularProgressIndicator progressbar1;

    private TimerTask BehaviorTimer;
    private SharedPreferences KeyManager;
    private AlertDialog KeyImportDialog;
    private AlertDialog KeyLoadingDialog;
    private TimerTask DialogsTimer;
    private AlertDialog UnhandledErrorDialog;
    private AlertDialog SimpleLoaderDialog;
    private TimerTask ErrorSengingTimer;
    private TimerTask LoadingTimer;
    private TimerTask TimeOutTimer;
    private com.google.android.material.snackbar.Snackbar InvalidSnackbar;
    private TimerTask UITimer;
    private AlertDialog KeyDeleteDialog;
    private AlertDialog KeySigningProgress;
    private TimerTask ErrorTimer;
    private TimerTask NonNullTimer;
    private ObjectAnimator NewAnimator = new ObjectAnimator();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        getWindow().setAllowEnterTransitionOverlap(true);
        MaterialSharedAxis enterTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, true);
        enterTransition.addTarget(R.id._coordinator);
        enterTransition.setDuration(300L);
        getWindow().setEnterTransition(enterTransition);
        MaterialSharedAxis returnTransition = new MaterialSharedAxis(MaterialSharedAxis.Y, false);
        returnTransition.setDuration(300L);
        returnTransition.addTarget(R.id._coordinator);
        getWindow().setReturnTransition(returnTransition);
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.keys_list);
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
        _fab2 = findViewById(R.id._fab2);
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
        _fab = findViewById(R.id._fab);

        collapsingtoolbar = findViewById(R.id.collapsingtoolbar);
        LoadingLinear = findViewById(R.id.LoadingLinear);
        recyclerview = findViewById(R.id.recyclerview);
        progressbar1 = findViewById(R.id.progressbar1);
        KeyManager = getSharedPreferences("KeysData", Activity.MODE_PRIVATE);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastDy = 0;
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    _app_bar.setLifted(true);
                } else {
                    _app_bar.setLifted(false);
                }

                if (dy > 10) {
                    _fab.shrink();
                    _fab2.shrink();
                } else if (dy < -10) {
                    _fab.extend();
                    _fab2.extend();
                }
            }
        });

        _fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");

                startActivityForResult(intent, 911);
            }
        });
    }

    private void initializeLogic() {
        _SetupUI();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        try {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    KeysMap = new Gson().fromJson(KeyManager.getString("KeysData", ""), new TypeToken < ArrayList < HashMap < String, Object >>> () {}.getType());
                }
            });
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {

        }
        UITimer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _LoadListMap();
                    }
                });
            }
        };
        _timer.schedule(UITimer, (0));
        _fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _KeyCreatingActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        if (_resultCode == Activity.RESULT_OK) {
            if (_data != null) {
                if (_requestCode == 911) {
                    IsPkcs12 = false;
                    IsSupportedKey = false;
                    IsSent = false;
                    IsNotSent = false;
                    final Uri PathUri = _data.getData();
                    String dataPath = "/data/data/" + getApplicationContext().getPackageName();

                    Cursor returnCursor = getContentResolver().query(PathUri, null, null, null, null);
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    returnCursor.moveToFirst();
                    final String FileName = returnCursor.getString(nameIndex);
                    final String fileSize = Long.toString(returnCursor.getLong(sizeIndex));
                    path = dataPath + "/" + FileName;
                    LowerCasePath = path.toLowerCase();
                    if (Uri.parse(LowerCasePath).getLastPathSegment().substring((int)(Uri.parse(LowerCasePath).getLastPathSegment().length() - ".".concat("jks").length()), (int)(Uri.parse(LowerCasePath).getLastPathSegment().length())).equals(".".concat("jks"))) {
                        IsSupportedKey = true;
                    }
                    if (!IsSupportedKey) {
                        if (Uri.parse(LowerCasePath).getLastPathSegment().substring((int)(Uri.parse(LowerCasePath).getLastPathSegment().length() - ".".concat("bks").length()), (int)(Uri.parse(LowerCasePath).getLastPathSegment().length())).equals(".".concat("bks"))) {
                            IsSupportedKey = true;
                        }
                    }
                    if (!IsSupportedKey) {
                        if (Uri.parse(LowerCasePath).getLastPathSegment().substring((int)(Uri.parse(LowerCasePath).getLastPathSegment().length() - ".".concat("pkcs12").length()), (int)(Uri.parse(LowerCasePath).getLastPathSegment().length())).equals(".".concat("pkcs12"))) {
                            IsSupportedKey = true;
                            IsPkcs12 = true;
                        }
                    }
                    if (!IsSupportedKey) {
                        View InvalidSnackbarView = getLayoutInflater().inflate(R.layout.premium_snackbar, null);
                        InvalidSnackbar = com.google.android.material.snackbar.Snackbar.make(_coordinator, "", Snackbar.LENGTH_LONG);
                        final LinearLayout SBG = (LinearLayout)
                        InvalidSnackbarView.findViewById(R.id.BG);
                        final TextView Message = (TextView)
                        InvalidSnackbarView.findViewById(R.id.Message);
                        final ImageView Icon = (ImageView)
                        InvalidSnackbarView.findViewById(R.id.Icon);
                        Icon.setImageResource(R.drawable.ic_error_white);
                        Message.setText("Key invalid or not supported");
                        SBG.setBackground(new GradientDrawable() {
                            public GradientDrawable getIns(int a, int b) {
                                this.setCornerRadius(a);
                                this.setColor(b);
                                return this;
                            }
                        }.getIns((int) _DpToPx(10), 0xFF27313A));
                        Message.setTextColor(0xFFFFFFFF);
                        InvalidSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                        Snackbar.SnackbarLayout InvalidSnackbarView2 = (Snackbar.SnackbarLayout) InvalidSnackbar.getView();
                        InvalidSnackbarView2.addView(InvalidSnackbarView, 0);
                        InvalidSnackbar.show();
                    } else {
                        try {
                            InputStream in = null;
                            OutputStream out = null;
                            try { in = getContentResolver().openInputStream(PathUri);
                                out = new FileOutputStream(new File(dataPath + "/" + FileName));
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = in .read(buffer)) != -1) {
                                    out.write(buffer, 0, len);
                                }
                            } finally {
                                if ( in != null) { in .close();
                                }
                                if (out != null) {
                                    out.close();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        final AlertDialog KeyImportDialog = new AlertDialog.Builder(KeysListActivity.this).create();
                        LayoutInflater KeyImportDialogLI = getLayoutInflater();
                        View KeyImportDialogCV = KeyImportDialogLI.inflate(R.layout.key_import_dialog, null);
                        KeyImportDialog.setView(KeyImportDialogCV);
                        KeyImportDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        final com.google.android.material.textfield.TextInputEditText AliasE = (com.google.android.material.textfield.TextInputEditText)
                        KeyImportDialogCV.findViewById(R.id.AliasE);
                        final com.google.android.material.textfield.TextInputEditText KeyPassE = (com.google.android.material.textfield.TextInputEditText)
                        KeyImportDialogCV.findViewById(R.id.KeyPassE);
                        final com.google.android.material.textfield.TextInputEditText KeyStoreE = (com.google.android.material.textfield.TextInputEditText)
                        KeyImportDialogCV.findViewById(R.id.KeyStoreE);
                        final com.google.android.material.textfield.TextInputLayout AliasTIP = (com.google.android.material.textfield.TextInputLayout)
                        KeyImportDialogCV.findViewById(R.id.AliasTIP);
                        final com.google.android.material.textfield.TextInputLayout KeyPassTIP = (com.google.android.material.textfield.TextInputLayout)
                        KeyImportDialogCV.findViewById(R.id.KeyPassTIP);
                        final com.google.android.material.textfield.TextInputLayout KeyStoreTIP = (com.google.android.material.textfield.TextInputLayout)
                        KeyImportDialogCV.findViewById(R.id.KeyStoreTIP);
                        final LinearLayout LeftButton = (LinearLayout)
                        KeyImportDialogCV.findViewById(R.id.LeftButton);
                        final LinearLayout RightButton = (LinearLayout)
                        KeyImportDialogCV.findViewById(R.id.RightButton);
                        final TextView note = (TextView)
                        KeyImportDialogCV.findViewById(R.id.note);
                        if (IsPkcs12) {
                            KeyPassTIP.setVisibility(View.GONE);
                        } else {
                            note.setVisibility(View.GONE);
                        }
                        AliasE.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                                final String AliasEnn = _param1.toString();

                                AliasTIP.setErrorEnabled(false);
                            }

                            @Override
                            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

                            }

                            @Override
                            public void afterTextChanged(Editable _param1) {

                            }
                        });

                        KeyStoreE.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                                final String KeyStoreEnn = _param1.toString();

                                KeyStoreTIP.setErrorEnabled(false);
                            }

                            @Override
                            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

                            }

                            @Override
                            public void afterTextChanged(Editable _param1) {

                            }
                        });

                        KeyPassE.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                                final String KeyPassEnn = _param1.toString();

                                KeyPassTIP.setErrorEnabled(false);
                            }

                            @Override
                            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

                            }

                            @Override
                            public void afterTextChanged(Editable _param1) {

                            }
                        });

                        KeyStoreTIP.setBoxCornerRadii((float) 25, (float) 25, (float) 25, (float) 25);
                        AliasTIP.setBoxCornerRadii((float) 25, (float) 25, (float) 25, (float) 25);
                        KeyPassTIP.setBoxCornerRadii((float) 25, (float) 25, (float) 25, (float) 25);
                        RightButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View _view) {
                                try {
                                    FileUtil.deleteFile("/data/data/com.xapps.utility.xsigner/error.txt");
                                } catch (Exception e) {

                                }
                                final AlertDialog KeyLoadingDialog = new AlertDialog.Builder(KeysListActivity.this).create();
                                LayoutInflater KeyLoadingDialogLI = getLayoutInflater();
                                View KeyLoadingDialogCV = (View) KeyLoadingDialogLI.inflate(R.layout.key_loading_dialog, null);
                                KeyLoadingDialog.setView(KeyLoadingDialogCV);
                                final com.google.android.material.progressindicator.CircularProgressIndicator progress = (com.google.android.material.progressindicator.CircularProgressIndicator)
                                KeyLoadingDialogCV.findViewById(R.id.progress);
                                progress.setIndeterminate(true);
                                KeyLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                KeyLoadingDialog.setCancelable(false);
                                KeyLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        if (FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/error.txt")) {
                                            if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").equals("")) {
                                                XUtil.showMessage(getApplicationContext(), "Signature imported successfully");
                                                AsyncTask.execute(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            FileUtil.deleteFile("/data/data/com.xapps.utility.xsigner/blank_signed.apk");
                                                        } catch (Exception e) {

                                                        }
                                                    }
                                                });
                                                KeystorePass = KeyStoreE.getText().toString();
                                                AliasKey = KeyPassE.getText().toString();
                                                KeyFileName = FileName;
                                                Alias = AliasE.getText().toString();
                                                _ImportKeyToList();
                                                KeyImportDialog.dismiss();
                                            } else {
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.lang.NullPointerException: Attempt to") || FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.lang.Exception: Certificate chain is empty or null")) {
                                                    AliasTIP.setError("No alias found with given name");
                                                    AliasTIP.setErrorEnabled(true);
                                                    KnownError = true;
                                                }
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.io.IOException: Keystore was tampered with, or password was incorrect") || (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.io.IOException: PKCS12 key store mac invalid - wrong password or corrupted file.") || FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.io.IOException: KeyStore integrity check failed."))) {
                                                    KeyStoreTIP.setError("Incorrect KeyStore password ");
                                                    KeyStoreTIP.setErrorEnabled(true);
                                                    KnownError = true;
                                                }
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.security.UnrecoverableKeyException: Cannot recover key")) {
                                                    KeyPassTIP.setError("Incorrect password for given alias");
                                                    KeyPassTIP.setErrorEnabled(true);
                                                    KnownError = true;
                                                }
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").equals("java.io.FileNotFoundException: /data/data/com.xapps.utility.xsigner/") && FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("open failed: ENOENT (No such file or directory)")) {
                                                    XUtil.showMessage(getApplicationContext(), "The key is not longer accessible, please re-import it again");
                                                    KeyImportDialog.dismiss();
                                                    KnownError = true;
                                                }
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.io.FileNotFoundException: /data/data/com.xapps.utility.xsigner/") && FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("open failed: EACCES (Permission denied)")) {
                                                    XUtil.showMessage(getApplicationContext(), "Insufficient permissions to read the key file, please re-import it again");
                                                    KeyImportDialog.dismiss();
                                                    KnownError = true;
                                                    try {
                                                        FileUtil.deleteFile(dataPath + "/" + FileName);
                                                    } catch (Exception e) {

                                                    }
                                                }
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("java.io.IOException: Invalid keystore format")) {
                                                    XUtil.showMessage(getApplicationContext(), "Failed to load key : the key is not a valid keystore");
                                                    KeyImportDialog.dismiss();
                                                    KnownError = true;
                                                    try {
                                                        FileUtil.deleteFile(dataPath + "/" + FileName);
                                                    } catch (Exception e) {

                                                    }
                                                }
                                                if (FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt").contains("Exception: java.io.IOException: Wrong version of key store")) {
                                                    XUtil.showMessage(getApplicationContext(), "Renamed key detected, please rename keystore extension back to original.");
                                                    KeyImportDialog.dismiss();
                                                    KnownError = true;
                                                    try {
                                                        FileUtil.deleteFile(dataPath + "/" + FileName);
                                                    } catch (Exception e) {

                                                    }
                                                }
                                                if (!KnownError) {
                                                    final AlertDialog UnhandledErrorDialog = new AlertDialog.Builder(KeysListActivity.this).create();
                                                    LayoutInflater UnhandledErrorDialogLI = getLayoutInflater();
                                                    View UnhandledErrorDialogCV = (View) UnhandledErrorDialogLI.inflate(R.layout.unhandled_error_dialog, null);
                                                    UnhandledErrorDialog.setView(UnhandledErrorDialogCV);
                                                    UnhandledErrorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                    final WebView w = (WebView)
                                                    UnhandledErrorDialogCV.findViewById(R.id.w);
                                                    final LinearLayout LeftButton2 = (LinearLayout)
                                                    UnhandledErrorDialogCV.findViewById(R.id.LeftButton);
                                                    final LinearLayout RightButton2 = (LinearLayout)
                                                    UnhandledErrorDialogCV.findViewById(R.id.RightButton);
                                                    LeftButton2.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View _view) {
                                                            UnhandledErrorDialog.dismiss();
                                                        }
                                                    });
                                                    RightButton2.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View _view) {
                                                            final AlertDialog SimpleLoaderDialog = new AlertDialog.Builder(KeysListActivity.this).create();
                                                            LayoutInflater SimpleLoaderDialogLI = getLayoutInflater();
                                                            View SimpleLoaderDialogCV = (View) SimpleLoaderDialogLI.inflate(R.layout.simple_loader_dialog, null);
                                                            SimpleLoaderDialog.setView(SimpleLoaderDialogCV);
                                                            SimpleLoaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                            SimpleLoaderDialog.setCancelable(false);
                                                            LoadingTimer = new TimerTask() {
                                                                @Override
                                                                public void run() {
                                                                    runOnUiThread(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            w.loadUrl("https://api.telegram.org/bot".concat(TelegramBot.BOT_TOKEN.concat("/sendMessage?chat_id=".concat("6889163631".concat("&text=".concat(FileUtil.readFile("/data/data/com.xapps.utility.xsigner/error.txt")))))));
                                                                            w.setWebViewClient(new WebViewClient() {
                                                                                @Override
                                                                                public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
                                                                                    final String _url = _param2;

                                                                                    super.onPageStarted(_param1, _param2, _param3);
                                                                                }

                                                                                @Override
                                                                                public void onPageFinished(WebView _param1, String _param2) {
                                                                                    final String _url = _param2;
                                                                                    try {
                                                                                        TimeOutTimer.cancel();
                                                                                    } catch (Exception ee) {
                                                                                        ee.printStackTrace();
                                                                                    }
                                                                                    IsSent = true;
                                                                                    if (_isConnected()) {
                                                                                        try {

                                                                                            SimpleLoaderDialog.dismiss();

                                                                                        } catch (Exception e2) {

                                                                                            e2.printStackTrace();

                                                                                        }

                                                                                        try {

                                                                                            UnhandledErrorDialog.dismiss();

                                                                                        } catch (Exception e) {

                                                                                            e.printStackTrace();

                                                                                        }

                                                                                        try {

                                                                                            ErrorSengingTimer.cancel();

                                                                                        } catch (Exception eee) {

                                                                                            eee.printStackTrace();

                                                                                        }
                                                                                        XUtil.showMessage(getApplicationContext(), "Error message was sent successfully");
                                                                                    } else {
                                                                                        try {

                                                                                            SimpleLoaderDialog.dismiss();

                                                                                        } catch (Exception e) {

                                                                                            e.printStackTrace();

                                                                                        }
                                                                                        try {

                                                                                            ErrorSengingTimer.cancel();

                                                                                        } catch (Exception ee2) {

                                                                                            ee2.printStackTrace();

                                                                                        }
                                                                                        XUtil.showMessage(getApplicationContext(), "Failed to send error message");
                                                                                    }
                                                                                    super.onPageFinished(_param1, _param2);
                                                                                }
                                                                            });

                                                                            TimeOutTimer = new TimerTask() {
                                                                                @Override
                                                                                public void run() {
                                                                                    runOnUiThread(new Runnable() {
                                                                                        @Override
                                                                                        public void run() {
                                                                                            SimpleLoaderDialog.dismiss();
                                                                                            XUtil.showMessage(getApplicationContext(), "Error message sending timeout, verify your internet connection and try again");
                                                                                        }
                                                                                    });
                                                                                }
                                                                            };
                                                                            _timer.schedule(TimeOutTimer, (int)(15000));
                                                                        }
                                                                    });
                                                                }
                                                            };
                                                            _timer.schedule(LoadingTimer, (int)(1500));
                                                            SimpleLoaderDialog.show();
                                                        }
                                                    });
                                                    UnhandledErrorDialog.setCancelable(false);
                                                    UnhandledErrorDialog.show();
                                                }
                                                KnownError = false;
                                            }
                                        } else {
                                            XUtil.showMessage(getApplicationContext(), "Signature imported successfully");
                                            AsyncTask.execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        FileUtil.deleteFile("/data/data/com.xapps.utility.xsigner/blank_signed.apk");
                                                    } catch (Exception e) {

                                                    }
                                                }
                                            });
                                            KeystorePass = KeyStoreE.getText().toString();
                                            AliasKey = KeyPassE.getText().toString();
                                            KeyFileName = FileName;
                                            Alias = AliasE.getText().toString();
                                            _TryToLoadKeys();
                                            KeyImportDialog.dismiss();
                                        }
                                    }
                                });
                                KeyLoadingDialog.show();
                                LoadingTimer = new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                AsyncTask.execute(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (Uri.parse(LowerCasePath).getLastPathSegment().substring((Uri.parse(LowerCasePath).getLastPathSegment().length() - ".".concat("jks").length()), (int)(Uri.parse(LowerCasePath).getLastPathSegment().length())).equals(".".concat("jks"))) {
                                                            _SignWithReleaseKey("/data/data/com.xapps.utility.xsigner/blank.apk", "/data/data/com.xapps.utility.xsigner/blank_signed.apk", dataPath + "/" + FileName, AliasE.getText().toString(), KeyStoreE.getText().toString(), KeyPassE.getText().toString(), "JKS", true, true, true, false, true);
                                                        }
                                                        if (Uri.parse(LowerCasePath).getLastPathSegment().substring((Uri.parse(LowerCasePath).getLastPathSegment().length() - ".".concat("bks").length()), (int)(Uri.parse(LowerCasePath).getLastPathSegment().length())).equals(".".concat("bks"))) {
                                                            _SignWithReleaseKey("/data/data/com.xapps.utility.xsigner/blank.apk", "/data/data/com.xapps.utility.xsigner/blank_signed.apk", dataPath + "/" + FileName, AliasE.getText().toString(), KeyStoreE.getText().toString(), KeyPassE.getText().toString(), "BKS", true, true, true, false, true);
                                                        }
                                                        if (IsPkcs12) {
                                                            _SignWithReleaseKey("/data/data/com.xapps.utility.xsigner/blank.apk", "/data/data/com.xapps.utility.xsigner/blank_signed.apk", dataPath + "/" + FileName, AliasE.getText().toString(), KeyStoreE.getText().toString(), "", "PKCS12", true, true, true, false, true);
                                                        }
                                                        KeyLoadingDialog.dismiss();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                };
                                _timer.schedule(LoadingTimer, (1500));
                            }
                        });
                        LeftButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View _view) {
                                KeyImportDialog.dismiss();
                            }
                        });
                        KeyImportDialog.setCancelable(false);
                        KeyImportDialog.show();
                    }
                }
            } else {

            }
        } else {

        }
        switch (_requestCode) {

            default: break;
        }
    }


    @Override
    public void onDestroy() {
        for (int i = 0; i < (KeysMap.size()); i++) {
            KeysMap.get(i).put("expansion", "false");
        }
        KeyManager.edit().putString("KeysData", new Gson().toJson(KeysMap)).commit();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isApplied = false;
    }
    public void _commandBlocks() {


    }


    public void _SetupUI() {
        collapsingtoolbar.setTitle("Saved keys");
        int nightModeMask = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        navigationBarHeight = 0;
        statusBarHeight = 0;
        int r1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (r1 > 0) {
            navigationBarHeight = getResources().getDimensionPixelSize(r1);
        }
        int r2 = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (r2 > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(r2);
        }
        _SetMargins(_toolbar, 0, statusBarHeight, 0, 0);
        EdgeToEdgeUtils.applyEdgeToEdge(getWindow(), true);
        _fab.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                navigationBarHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) _fab.getLayoutParams();
                if (!isApplied) {
                    FabMarginToUse = params.bottomMargin;
                    NeededMargin = params.bottomMargin * 2 + _fab.getHeight() + navigationBarHeight;
                    isApplied = true;
                    params.bottomMargin = navigationBarHeight + params.bottomMargin;
                }
                _fab.setLayoutParams(params);
                return insets;
            }
        });
        _fab2.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                navigationBarHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
                ViewGroup.MarginLayoutParams params3 = (ViewGroup.MarginLayoutParams) _fab2.getLayoutParams();
                if (!isApplied2) {
                    params3.bottomMargin = params3.bottomMargin + navigationBarHeight;
                    NeededMargin = FabMarginToUse * 3 + _fab.getHeight() * 2 + navigationBarHeight;
                    isApplied2 = true;
                }
                _fab2.setLayoutParams(params3);
                return insets;
            }
        });
        _SetMargins(recyclerview, 0, 0, 0, OldMargin * 2 + _fab.getHeight());
    }


    public void _MakeRipple(final View _view, final double _shadow, final double _radius, final String _color, final String _ripple) {
        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setColor(Color.parseColor(_color));
        gd.setCornerRadius((int) _radius);
        _view.setElevation((int) _shadow);
        android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][] {
            new int[] {}
        }, new int[] {
            Color.parseColor(_ripple)
        });
        android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb, gd, null);
        _view.setClickable(true);
        _view.setBackground(ripdrb);
    }


    public void _SetMargins(final View _layout, final int _leftMargin, final int _TopMargin, final int _RightMargin, final int _BottomMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _layout.getLayoutParams();

        layoutParams.setMargins(_leftMargin, _TopMargin, _RightMargin, _BottomMargin);
        _layout.setLayoutParams(layoutParams);
    }


    public void _Radius(final View _view, final double _lefttop, final double _righttop, final double _leftbottom, final double _rightbottom, final String _color) {
        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setColor(Color.parseColor(_color));
        gd.setCornerRadii(new float[] {
            (float) _lefttop, (float) _lefttop, (float) _righttop, (float) _righttop, (float) _rightbottom, (float) _rightbottom, (float) _leftbottom, (float) _leftbottom
        });
        _view.setBackground(gd);

    }


    public double _DpToPx(final double _dp) {
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (double) Math.round(_dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public void _SignWithReleaseKey(final String _input, final String _output, final String _keypath, final String _alias, final String _keystorepass, final String _keypass, final String _type, final boolean _v1, final boolean _v2, final boolean _v3, final boolean _v4, final boolean _zipalign) {
        try {
            APKSignerUtils.signFile(_input, _output, _keypath, _alias, _keystorepass, _keypass, _v1, _v2, _v3, _v4, _zipalign, _type);
        } catch (Exception e) {
            XUtil.showMessage(getApplicationContext(), e.toString());
        }
    }


    public void _SignWithTestKey(final String _input, final String _output, final String _pem, final String _pk8, final boolean _v1, final boolean _v2, final boolean _v3, final boolean _v4, final boolean _zipalign) {
        try {
            TestKeySigner.signWithTestkey(_input, _output, _pem, _pk8, _v1, _v2, _v3, _v4, _zipalign);
        } catch (Exception e) {
            XUtil.showMessage(getApplicationContext(), e.toString());
        }
    }


    public void _SendError() {

    }


    public void _ImportKeyToList() {
        IsItemAdded = true; {
            HashMap < String, Object > _item = new HashMap < > ();
            _item.put("keyname", KeyFileName);
            KeysMap.add(_item);
        }

        KeysMap.get((int) KeysMap.size() - 1).put("alias", Alias);
        KeysMap.get((int) KeysMap.size() - 1).put("keystorepass", KeystorePass);
        KeysMap.get((int) KeysMap.size() - 1).put("keypass", AliasKey);
        if (IsPkcs12) {
            KeysMap.get((int) KeysMap.size() - 1).put("type", "PKCS12");
        } else {
            KeysMap.get((int) KeysMap.size() - 1).put("type", KeyFileName.substring((int)(KeyFileName.length() - 3), (int)(KeyFileName.length())).toUpperCase());
        }
        KeysMap.get((int) KeysMap.size() - 1).put("sha1", _getKeyHash(path, Alias, KeystorePass, "SHA-1").toUpperCase());
        KeysMap.get((int) KeysMap.size() - 1).put("sha256", _getKeyHash(path, Alias, KeystorePass, "SHA-256").toUpperCase());
        KeysMap.get((int) KeysMap.size() - 1).put("sha512", _getKeyHash(path, Alias, KeystorePass, "SHA-512").toUpperCase());
        KeysMap.get((int) KeysMap.size() - 1).put("expiry", _GetReleaseKeyData(path, KeystorePass, "exp"));
        KeysMap.get((int) KeysMap.size() - 1).put("path", path);
        recyclerview.setAdapter(new RecyclerviewAdapter(KeysMap));
        KeyManager.edit().putString("KeysData", new Gson().toJson(KeysMap)).commit();
    }


    public String _getKeyHash(final String _path, final String _alias, final String _password, final String _type) {
        try {
            KeyHashResult = KeyHashUtils.getKeyDigest(_path, _password, _alias, _type);
        } catch (Exception e) {
            KeyHashResult = e.toString();
        }
        return (KeyHashResult);
    }


    public String _GetReleaseKeyData(final String _path, final String _pass, final String _type) {
        try {
            KeyData = KeyDate.getKeyData(_path, _pass, _type);
        } catch (Exception e) {
            KeyData = e.toString();
        }
        return (KeyData);
    }


    public void _DeleteItem(final double _position) {
        IsItemDelted = true;
        KeysMap.remove((int)(_position));
        recyclerview.setAdapter(new RecyclerviewAdapter(KeysMap));
        KeyManager.edit().putString("KeysData", new Gson().toJson(KeysMap)).commit();
    }


    public void _ScrollToBottom() {
        ((LinearLayoutManager) recyclerview.getLayoutManager()).scrollToPositionWithOffset((int) KeysMap.size() - 1, (int) 20);
        IsItemDelted = false;
        IsItemAdded = false;
    }


    public boolean _isConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        } else {

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
    }


    public void _ExtractAssets() {
        if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.pk8")) {
            try {
                int count;
                java.io.InputStream input = this.getAssets().open("testkey.pk8");
                java.io.OutputStream output = new java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/" + "testkey.pk8");
                byte data[] = new byte[1024];
                while ((count = input.read(data)) > 0) {
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {

            }
        }
        if (!FileUtil.isExistFile("/data/data/com.xapps.utility.xsigner/testkey.x509.pem")) {
            try {
                int count;
                java.io.InputStream input = this.getAssets().open("testkey.x509.pem");
                java.io.OutputStream output = new java.io.FileOutputStream("/data/data/com.xapps.utility.xsigner/" + "testkey.x509.pem");
                byte data[] = new byte[1024];
                while ((count = input.read(data)) > 0) {
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {

            }
        }
    }


    public void _RefreshList() {
        recyclerview.setAdapter(new RecyclerviewAdapter(KeysMap));
    }


    public void _LoadListMap() {
        NonNullTimer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NonNullTimer.cancel();
                        if (KeysMap != null) {
                            try {
                                recyclerview.setAdapter(new RecyclerviewAdapter(KeysMap));
                                LoadingLinear.setVisibility(View.GONE);
                                recyclerview.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                ErrorTimer = new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                _LoadListMap();
                                            }
                                        });
                                    }
                                };
                                _timer.schedule(ErrorTimer, (int)(100));
                            }
                        }
                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(NonNullTimer, (int)(0), (int)(25));
    }


    public void _SetMarginsStable(final View _view, final int _top, final int _bottom) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) _view.getLayoutParams();

        layoutParams.bottomMargin = _bottom;
        layoutParams.topMargin = _top;
        _view.setLayoutParams(layoutParams);
    }


    public void _CreateKeyScreen() {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, KeyCreatingActivity.class);
        startActivity(intent, options.toBundle());
    }


    public void _KeyCreatingActivity() {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, KeyCreatingActivity.class);
        startActivity(intent, options.toBundle());
    }


    public void _Extra() {

    }

    public void animateHeight(final View view, int startHeight, int endHeight, long duration) {
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, endHeight);
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = animatedValue;
                view.setLayoutParams(layoutParams);
            }
        });

        animator.start();
    }

    public void _AnimateHeight(final View _view, final int _start, final int _end, final long _duration) {
        animator = ValueAnimator.ofInt(_start, _end);
        animator.setDuration(_duration);
        animator.setInterpolator(new FastOutSlowInInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = _view.getLayoutParams();
                layoutParams.height = animatedValue;
                _view.setLayoutParams(layoutParams);
            }
        });

        animator.start();
    }


    public void _TryToLoadKeys() {
        if (KeysMap != null) {
            _ImportKeyToList();
        } else {

            NonNullHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    _TryToLoadKeys();
                }
            }, 75);
        }
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter < RecyclerviewAdapter.ViewHolder > {

        ArrayList < HashMap < String,
        Object >> _data;

        public RecyclerviewAdapter(ArrayList < HashMap < String, Object >> _arr) {
            _data = _arr;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater _inflater = getLayoutInflater();
            View _v = _inflater.inflate(R.layout.keys_list_custom_view, null);
            RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            _v.setLayoutParams(_lp);
            return new ViewHolder(_v);
        }

        @Override
        public void onBindViewHolder(ViewHolder _holder, final int _position) {
            View _view = _holder.itemView;

            final LinearLayout KeyInfoLayout = _view.findViewById(R.id.KeyInfoLayout);
            final LinearLayout TopLayout = _view.findViewById(R.id.TopLayout);
            final LinearLayout TypeLinear = _view.findViewById(R.id.TypeLinear);
            final ExpandableLayout FixContainer = _view.findViewById(R.id.FixContainer);
            final LinearLayout KeyFrame = _view.findViewById(R.id.KeyFrame);
            final TextView KeyName = _view.findViewById(R.id.KeyName);
            final ImageView KeyIcon = _view.findViewById(R.id.KeyIcon);
            final TextView TypeTitle = _view.findViewById(R.id.TypeTitle);
            final TextView TypeDesc = _view.findViewById(R.id.TypeDesc);
            final LinearLayout DataToExpandLinear = _view.findViewById(R.id.DataToExpandLinear);
            final LinearLayout Dv1 = _view.findViewById(R.id.Dv1);
            final LinearLayout AliasLinear = _view.findViewById(R.id.AliasLinear);
            final LinearLayout Sha1Linear = _view.findViewById(R.id.Sha1Linear);
            final LinearLayout Sha256Linear = _view.findViewById(R.id.Sha256Linear);
            final LinearLayout Sha512Linear = _view.findViewById(R.id.Sha512Linear);
            final LinearLayout ExpiryLinear = _view.findViewById(R.id.ExpiryLinear);
            final LinearLayout linear2 = _view.findViewById(R.id.linear2);
            final TextView AliasTitle = _view.findViewById(R.id.AliasTitle);
            final TextView AliasDesc = _view.findViewById(R.id.AliasDesc);
            final TextView Sha1Title = _view.findViewById(R.id.Sha1Title);
            final TextView Sha1Desc = _view.findViewById(R.id.Sha1Desc);
            final TextView Sha256Title = _view.findViewById(R.id.Sha256Title);
            final TextView Sha256Desc = _view.findViewById(R.id.Sha256Desc);
            final TextView Sha512Title = _view.findViewById(R.id.Sha512Title);
            final TextView Sha512Desc = _view.findViewById(R.id.Sha512Desc);
            final TextView KeyExpiryTitle = _view.findViewById(R.id.KeyExpiryTitle);
            final TextView KeyExpiryDesc = _view.findViewById(R.id.KeyExpiryDesc);
            final TextView textview3 = _view.findViewById(R.id.textview3);
            final TextView textview4 = _view.findViewById(R.id.textview4);

            TransitionDrawable transitionDrawable = (TransitionDrawable) ContextCompat.getDrawable(KeyInfoLayout.getContext(), R.drawable.key_linear_animation);
            KeyInfoLayout.setBackground(transitionDrawable); 
            KeyInfoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                     if (!FixContainer.isExpanded()) {
                        if (FixContainer.toggleExpansion()){
                            transitionDrawable.startTransition(350);
                        }   
                     } else {
                         if (FixContainer.toggleExpansion()) {
                            transitionDrawable.reverseTransition(350);     
                         }        
                     }
                }
            });
            try {
                if (_position == 0) {
                    _SetMarginsStable(KeyInfoLayout, (int) _DpToPx(35), (int) _DpToPx(9));
                    if (!FileUtil.isExistFile(KeysMap.get(_position).get("path1").toString()) || !FileUtil.isExistFile(KeysMap.get((int) _position).get("path2").toString())) {
                        _ExtractAssets();
                    }
                } else {
                    if (!FileUtil.isExistFile(KeysMap.get(_position).get("path").toString())) {
                        _DeleteItem(_position);
                        NeedToRefresh = true;
                    }
                }
                if ((_position == (KeysMap.size() - 1)) && (KeysMap.size() > 1)) {
                    _SetMarginsStable(KeyInfoLayout, (int) _DpToPx(10), NeededMargin);
                }
            } catch (Exception e) {
                _RefreshList();
            }
            if (KeysMap.size() == 1) {
                _fab.extend();
                _fab2.extend();
            }
            if (NeedToRefresh) {
                _RefreshList();
            }
            try {
                UITimer.cancel();
            } catch (Exception e) {

            }
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    UITimer = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (IsItemAdded || IsItemDelted) {
                                        _ScrollToBottom();
                                    }
                                }
                            });
                        }
                    };
                    _timer.scheduleAtFixedRate(UITimer, (0), (50));
                }
            });
            try {
                KeyName.setText(KeysMap.get(_position).get("keyname").toString());
                TypeDesc.setText(KeysMap.get(_position).get("type").toString());
                AliasDesc.setText(KeysMap.get(_position).get("alias").toString());
                Sha1Desc.setText(KeysMap.get(_position).get("sha1").toString());
                Sha256Desc.setText(KeysMap.get(_position).get("sha256").toString());
                Sha512Desc.setText(KeysMap.get(_position).get("sha512").toString());
                KeyExpiryDesc.setText(KeysMap.get(_position).get("expiry").toString());
            } catch (Exception e) {
                _RefreshList();
            }
            Sha256Desc.setTextIsSelectable(true);
            Sha1Desc.setTextIsSelectable(true);
            Sha512Desc.setTextIsSelectable(true);
            KeyExpiryDesc.setTextIsSelectable(true);
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
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
    public ArrayList < Double > getCheckedItemPositionsToArray(ListView _list) {
        ArrayList < Double > _result = new ArrayList < Double > ();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double) _arr.keyAt(_iIdx));
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