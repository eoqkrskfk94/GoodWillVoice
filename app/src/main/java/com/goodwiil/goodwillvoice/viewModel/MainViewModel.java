package com.goodwiil.goodwillvoice.viewModel;


import android.view.View;

import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityMenu;

public class MainViewModel extends BaseViewModel{

    public void menuBtnClick(View view){
        ScreenManager.startActivity(view.getContext(), ActivityMenu.class);
    }

    public void setPermissionPref(String name, Boolean auth){
        AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, name, auth);
    }

}
