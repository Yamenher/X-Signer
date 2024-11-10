package com.xapps.utility.xsigner;
import android.app.*;
import android.content.*;
import android.graphics.drawable.*;
import android.net.*;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

import java.io.*;
import java.util.*;

public class XUtil {

    public static int TOP = 1;
    public static int CENTER = 2;
    public static int BOTTOM = 3;

    public static void sortListMap(final ArrayList<HashMap<String, Object>> listMap, final String key, final boolean isNumber, final boolean ascending) {
        Collections.sort(listMap, new Comparator<HashMap<String, Object>>() {
            public int compare(HashMap<String, Object> _compareMap1, HashMap<String, Object> _compareMap2) {
                if (isNumber) {
                    int _count1 = Integer.valueOf(_compareMap1.get(key).toString());
                    int _count2 = Integer.valueOf(_compareMap2.get(key).toString());
                    if (ascending) {
                        return _count1 < _count2 ? -1 : _count1 < _count2 ? 1 : 0;
                    } else {
                        return _count1 > _count2 ? -1 : _count1 > _count2 ? 1 : 0;
                    }
                } else {
                    if (ascending) {
                        return (_compareMap1.get(key).toString()).compareTo(_compareMap2.get(key).toString());
                    } else {
                        return (_compareMap2.get(key).toString()).compareTo(_compareMap1.get(key).toString());
                    }
                }
            }
        });
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                return networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
            }
        }
        return false;
    }
    
    public static void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
    
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    
    public static void showMessage(Context _context, String _s) {
        Toast.makeText(_context, _s, Toast.LENGTH_SHORT).show();
    }

    public static int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    public static ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double) _arr.keyAt(_iIdx));
        }
        return _result;
    }

    public static void getAllKeysFromMap(Map<String, Object> _map, ArrayList<String> _output) {
        if (_output == null) return;
        _output.clear();
        if (_map == null || _map.size() < 1) return;
        for (Map.Entry<String, Object> _entry : _map.entrySet()) {
            _output.add(_entry.getKey());
        }
    }
}
