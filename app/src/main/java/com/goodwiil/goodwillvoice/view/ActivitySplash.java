package com.goodwiil.goodwillvoice.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivitySplashBinding;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


public class ActivitySplash extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();


        mBinding.getViewModel().startApp(this);
        finish();

    }



    private ActivitySplashBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mBinding.setViewModel(new SplashViewModel());

    }






}
