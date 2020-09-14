package com.goodwiil.goodwillvoice.viewModel;

import android.content.Context;

import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityMain;
import com.goodwiil.goodwillvoice.view.ActivityMain2;
import com.goodwiil.goodwillvoice.view.ActivitySignUp;

public class SplashViewModel {

    public void startApp(Context context) {
        //화원등록이 되어있으면 바로 메인화면으로 시작 아니면 회원등록화면으로 시작
        ScreenManager.startActivity(context, (AppDataManager.getUserModel() == null) ? (ActivitySignUp.class) : (ActivityMain2.class));
    }
}
