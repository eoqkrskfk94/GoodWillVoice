package com.goodwiil.goodwillvoice.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.goodwiil.goodwillvoice.application.GoodWillApplication;
import com.goodwiil.goodwillvoice.model.Setting;
import com.goodwiil.goodwillvoice.model.User;
import com.google.gson.Gson;

public class AppDataManager {

    public static final String SP_NAME = "userInfo";
    public static final String SP_KEY_USER = "userInfoKey";

    public static final String SETTINGS = "settingInfo";
    public static final String SETTINGS_KEY = "settingInfoKey";

    public static final String SP_NAME_YEAR = "year";
    public static final String SP_NAME_GENDER = "gender";
    public static final String SP_NAME_CAREER = "career";
    public static final String SP_NAME_CITY = "city";

    //shared preference에 값을 저장하기
    public static void setSharedPrefs(String name, User user){
        SharedPreferences prefs = GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(SP_KEY_USER, json);
        editor.apply();

    }

    public static void setSharedPrefs(String name, Setting setting){
        SharedPreferences prefs = GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(setting);
        editor.putString(SETTINGS_KEY, json);
        editor.apply();

    }

    //shared preference에 값을 불러오기
    private static SharedPreferences getSharedPrefs(String name){
        return GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    public static User getUserModel(){
        Gson gson = new Gson();
        return (gson.fromJson(getSharedPrefs(SP_NAME).getString(SP_KEY_USER, null), User.class));
    }

    public static User getSettingModel(){
        Gson gson = new Gson();
        return (gson.fromJson(getSharedPrefs(SETTINGS).getString(SETTINGS_KEY, null), User.class));
    }








}
