package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.view.ActivitySignUp;

public class SplashViewModel {

    public void startApp(View view){
        Intent intent = new Intent(view.getContext(), ActivitySignUp.class);
        view.getContext().startActivity(intent);
    }


}
