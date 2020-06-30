package com.goodwiil.goodwillvoice.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;

import com.goodwiil.goodwillvoice.application.GoodWillApplication;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AppDataManager {

    public static final String SP_NAME = "userInfo";
    public static final String SP_KEY_USER = "userInfoKey";


    public static final String SETTING_VIBRATE = "vibration_alarm";
    public static final String SETTING_VOICE = "voice_alarm";
    public static final String SETTING_LEVEL = "level_list";

    public static final String PERMISSION_KEY = "permissionInfo";
    public static final String PERMISSION_BATTERY = "battery_optimization";
    public static final String PERMISSION_OVERLAY = "overlay_permission";

    public static final String SP_NAME_YEAR = "year";
    public static final String SP_NAME_GENDER = "gender";
    public static final String SP_NAME_CAREER = "career";
    public static final String SP_NAME_CITY = "city";


    //shared preference에 값을 저장하기
    public static void setSharedPrefs(String name, User user) {
        SharedPreferences prefs = GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(SP_KEY_USER, json);
        editor.apply();

    }

    public static void setSharedPrefs(String name, String permission, Boolean accessibility) {
        SharedPreferences prefs = GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(permission, accessibility);
        editor.apply();
    }


    //shared preference에 값을 불러오기
    public static SharedPreferences getSharedPrefs(String name) {
        return GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    public static User getUserModel() {
        Gson gson = new Gson();
        return (gson.fromJson(getSharedPrefs(SP_NAME).getString(SP_KEY_USER, null), User.class));
    }



    public static ArrayList<CallLogInfo> getCallLog(Context context) {

        ArrayList<CallLogInfo> callLogInfos = new ArrayList<CallLogInfo>();

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null);
            int log_name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int log_number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int log_type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int log_date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int log_duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()){

            }
        }






        return callLogInfos;
    }







}
