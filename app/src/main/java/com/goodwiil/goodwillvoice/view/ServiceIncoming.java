package com.goodwiil.goodwillvoice.view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.goodwiil.goodwillvoice.R;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class ServiceIncoming extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    private ServiceIncoming mBinding;

    private void createBinding(){
        //mBinding = DataBindingUtil.setContentView(this, R.layout.service_incoming);
        //mBinding.
    }

}

