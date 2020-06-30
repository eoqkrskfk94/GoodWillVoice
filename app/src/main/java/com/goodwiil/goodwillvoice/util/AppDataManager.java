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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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


    //최근기록 불러오기
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
                CallLogInfo callLogInfo = new CallLogInfo();

                if(cursor.getString(log_name) == null)
                    callLogInfo.setName("unknown");
                else
                    callLogInfo.setName(cursor.getString(log_name));

                callLogInfo.setNumber(cursor.getString(log_number));
                callLogInfo.setDate(changeDate(cursor.getString(log_date)));
                callLogInfo.setDuration(cursor.getString(log_duration));

                String callType = cursor.getString(log_type);
                int code = Integer.parseInt(callType);
                switch(code){
                    case CallLog.Calls.OUTGOING_TYPE:
                        callLogInfo.setType("OUTGOING");
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        callLogInfo.setType("INCOMING");
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callLogInfo.setType("MISSED");
                        break;
                    case CallLog.Calls.REJECTED_TYPE:
                        callLogInfo.setType("REJECTED");
                        break;
                }

                callLogInfos.add(callLogInfo);


            }
        }

        return callLogInfos;
    }


    public static String changeDate(String log_date){
        Date logDate = new Date(Long.valueOf(log_date));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String newDate = formatter.format(logDate);


        return newDate;
    }







}
