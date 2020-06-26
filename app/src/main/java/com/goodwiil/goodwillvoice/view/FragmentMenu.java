package com.goodwiil.goodwillvoice.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.util.AppDataManager;

import androidx.annotation.Nullable;

public class FragmentMenu extends PreferenceFragment {

    ListPreference levelPreference;
    Preference batteryPreference;
    Preference overlayPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        levelPreference = (ListPreference) findPreference("level_list");
        batteryPreference = (Preference) findPreference("battery");
        overlayPreference = (Preference) findPreference("overlay");



    }

    @Override
    public void onResume() {
        super.onResume();
        //설정값 변경리스너..등록
        AppDataManager.getSharedPrefs("settingInfo").registerOnSharedPreferenceChangeListener(listener);
    }


    @Override
    public void onPause() {
        super.onPause();
        AppDataManager.getSharedPrefs("settingInfo").unregisterOnSharedPreferenceChangeListener(listener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("vibration_alarm")){
                boolean vibrate = AppDataManager.getSharedPrefs("settingInfo").getBoolean("vibration_alarm", false);
            }

            if(key.equals("voice_alarm")){
                boolean voice = AppDataManager.getSharedPrefs("settingInfo").getBoolean("voice_alarm", false);

            }

            if(key.equals("level_list")){
                String level = AppDataManager.getSharedPrefs("settingInfo").getString("level_list", "");
                levelPreference.setSummary(level);
            }

        }
    };



}
