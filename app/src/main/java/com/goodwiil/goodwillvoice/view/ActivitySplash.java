package com.goodwiil.goodwillvoice.view;

import android.content.Intent;
import android.os.Bundle;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivitySplashBinding;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


public class ActivitySplash extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        startApp();
        //mBinding.getViewModel().startApp(this);

    }

    private ActivitySplashBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mBinding.setViewModel(new SplashViewModel());

    }

    private void startApp(){
        //if
        Intent intent = new Intent(this, ActivitySignUp.class);
        startActivity(intent);
        finish();
    }


}
