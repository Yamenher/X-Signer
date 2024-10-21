package com.xapps.utility.xsigner;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
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
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.core.*;
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
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class KeySelectDialogFragmentActivity extends DialogFragment {
	
	private Timer _timer = new Timer();
	
	private Handler NonNullHandler = new Handler(Looper.getMainLooper());
	
	private ArrayList<HashMap<String, Object>> KeysList = new ArrayList<>();
	
	private LinearLayout BG;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private TextView title;
	private RecyclerView List;
	private LinearLayout LoaderContainer;
	private CircularProgressIndicator progressbar;
	private LinearLayout LeftButton;
	private TextView LeftText;
	
	private SharedPreferences Data;
	private SharedPreferences TmpProcess;
	private AlertDialog SigningOptionsDialog;
	private TimerTask RippleTimerDialog;
	private TimerTask RetryTimer;
	private TimerTask LoadingTimer;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.key_select_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		BG = _view.findViewById(R.id.BG);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		linear4 = _view.findViewById(R.id.linear4);
		title = _view.findViewById(R.id.title);
		List = _view.findViewById(R.id.List);
		LoaderContainer = _view.findViewById(R.id.LoaderContainer);
		progressbar = _view.findViewById(R.id.progressbar);
		LeftButton = _view.findViewById(R.id.LeftButton);
		LeftText = _view.findViewById(R.id.LeftText);
		Data = getContext().getSharedPreferences("KeysData", Activity.MODE_PRIVATE);
		TmpProcess = getContext().getSharedPreferences("TmpData", Activity.MODE_PRIVATE);
		
		LeftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
				TmpProcess.edit().putString("IsCanceled", "true").commit();
			}
		});
	}
	
	private void initializeLogic() {
		try {
			getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			int width = ViewGroup.LayoutParams.MATCH_PARENT;
					int height = ViewGroup.LayoutParams.WRAP_CONTENT; 
					 getDialog().getWindow().setLayout(width, height);
		} catch (Exception e) {
			SketchwareUtil.showMessage(getContext().getApplicationContext(), "error");
		}
		_TryToLoad();
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
	
	
	public void _LoadListMap() {
		try {
			KeysList = new Gson().fromJson(Data.getString("KeysData", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			List.setAdapter(new ListAdapter(KeysList));
			List.setLayoutManager(new LinearLayoutManager(getContext()));
			List.setVisibility(View.VISIBLE);
			LoaderContainer.setVisibility(View.GONE);
		} catch (Exception e) {
			RetryTimer = new TimerTask() {
				@Override
				public void run() {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_LoadListMap();
						}
					});
				}
			};
			_timer.schedule(RetryTimer, (int)(100));
		}
	}
	
	
	public void _TryToLoad() {
		if (getActivity() != null) {
			            _StartLoading();
			        } else {
			            
			            NonNullHandler.postDelayed(new Runnable() {
				                @Override
				                public void run() {
					                    _TryToLoad(); 
					                }
				            }, 75);
			        }
	}
	
	
	public void _StartLoading() {
		LoadingTimer = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_LoadListMap();
					}
				});
			}
		};
		_timer.schedule(LoadingTimer, (int)(1000));
	}
	
	public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public ListAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.key_select_list, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout ItemLayout = _view.findViewById(R.id.ItemLayout);
			final TextView KeyName = _view.findViewById(R.id.KeyName);
			final TextView KeyType = _view.findViewById(R.id.KeyType);
			
			try {
				KeyName.setText(KeysList.get((int)_position).get("keyname").toString());
				KeyType.setText("Type : ".concat(KeysList.get((int)_position).get("type").toString()));
			} catch (Exception e) {
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "An Unknown Error has occurred");
			}
			ItemLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					TmpProcess.edit().putString("position", String.valueOf((long)(_position))).commit();
					dismiss();
				}
			});
		}
		
		@Override
		public int getItemCount() {
			try {
	   return _data.size();
       } catch (Exception e) {
	RetryTimer = new TimerTask() {
		@Override
		public void run() {
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					getItemCount();
			      	}
		        });
       		}
	      };
	_timer.schedule(RetryTimer, (int)(30));
return 0;
     }
     		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}