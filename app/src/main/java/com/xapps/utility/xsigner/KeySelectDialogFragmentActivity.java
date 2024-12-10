package com.xapps.utility.xsigner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xapps.utility.xsigner.databinding.KeySelectDialogFragmentBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class KeySelectDialogFragmentActivity extends DialogFragment {

    private KeySelectDialogFragmentBinding binding;
    
    private Timer _timer = new Timer();
    private Handler NonNullHandler = new Handler(Looper.getMainLooper());
    private ArrayList < HashMap < String, Object >> KeysList = new ArrayList < > ();

    private SharedPreferences Data;
    private SharedPreferences TmpProcess;
    private AlertDialog SigningOptionsDialog;
    private TimerTask RippleTimerDialog;
    private TimerTask RetryTimer;
    private TimerTask LoadingTimer;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
        binding = KeySelectDialogFragmentBinding.inflate(getLayoutInflater());
        initialize(_savedInstanceState, binding.getRoot());
        initializeLogic();
        return binding.getRoot();
    }

    private void initialize(Bundle _savedInstanceState, View _view) {
        Data = getContext().getSharedPreferences("KeysData", Activity.MODE_PRIVATE);
        TmpProcess = getContext().getSharedPreferences("TmpData", Activity.MODE_PRIVATE);
        binding.LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                dismiss();
                TmpProcess.edit().putString("IsCanceled", "true").commit();
            }
        });
    }

    private void initializeLogic() {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(width, height);
        _TryToLoad();
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


    public void _LoadListMap() {
        try {
            KeysList = new Gson().fromJson(Data.getString("KeysData", ""), new TypeToken < ArrayList < HashMap < String, Object >>> () {}.getType());
            binding.List.setAdapter(new ListAdapter(KeysList));
            binding.List.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.List.setVisibility(View.VISIBLE);
            binding.LoaderContainer.setVisibility(View.GONE);
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
            _timer.schedule(RetryTimer, (100));
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
        try {
            new Handler(Looper.getMainLooper()).postDelayed(() -> _LoadListMap(), 1);
        } catch (Exception e) {}
    }

    public class ListAdapter extends RecyclerView.Adapter < ListAdapter.ViewHolder > {

        ArrayList < HashMap < String,
        Object >> _data;

        public ListAdapter(ArrayList < HashMap < String, Object >> _arr) {
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
                KeyName.setText(KeysList.get(_position).get("keyname").toString());
                KeyType.setText("Type : ".concat(KeysList.get(_position).get("type").toString()));
            } catch (Exception e) {
                XUtil.showMessage(getContext().getApplicationContext(), "An Unknown Error has occurred");
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
                _timer.schedule(RetryTimer,(30));
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