package com.goodwiil.goodwillvoice.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class FragmentMenu extends PreferenceFragmentCompat {

    SharedPreferences prefs;
    ListPreference levelPreference;
    Preference batteryPreference;
    Preference overlayPreference;
    Preference userInfoPreference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        levelPreference = (ListPreference) findPreference("level_list");
        batteryPreference = (Preference) findPreference("battery");
        overlayPreference = (Preference) findPreference("overlay");
        userInfoPreference = (Preference) findPreference("userInfo");


        if(AppDataManager.getSharedPrefs(AppDataManager.PERMISSION_KEY).getBoolean(AppDataManager.PERMISSION_BATTERY, false)){
            batteryPreference.setSummary("ON");
        }else{
            batteryPreference.setSummary("OFF");
        }

        if(AppDataManager.getSharedPrefs(AppDataManager.PERMISSION_KEY).getBoolean(AppDataManager.PERMISSION_OVERLAY, false)){
            overlayPreference.setSummary("ON");
        }else{
            overlayPreference.setSummary("OFF");
        }

        //SharedPreference객체를 참조하여 설정상태에 대한 제어 가능..
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());



        levelPreference.setSummary(prefs.getString("level_list", "약"));

        prefs.registerOnSharedPreferenceChangeListener(listener);


        changeUserBtnClick(userInfoPreference);


    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }


    @Override
    public void onResume() {
        super.onResume();

        //설정값 변경리스너..등록
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();

        prefs.unregisterOnSharedPreferenceChangeListener(listener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals(AppDataManager.SETTING_VIBRATE)){
                boolean vibrate = prefs.getBoolean(AppDataManager.SETTING_VIBRATE, false);

            }

            if(key.equals(AppDataManager.SETTING_VOICE)){
                boolean voice = prefs.getBoolean(AppDataManager.SETTING_VOICE, false);

            }

            if(key.equals(AppDataManager.SETTING_LEVEL)){
                String level = prefs.getString(AppDataManager.SETTING_LEVEL, "");
                levelPreference.setSummary(level);
            }

        }
    };



    private void changeUserBtnClick(Preference preference){
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ScreenManager.startActivity(getActivity(), ActivitySignUp.class);
                return false;
            }
        });

    }





}
