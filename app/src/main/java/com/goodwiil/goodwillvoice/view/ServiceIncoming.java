package com.goodwiil.goodwillvoice.view;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ServiceIncomingBinding;
import com.goodwiil.goodwillvoice.model.IncomingNumber;
import com.goodwiil.goodwillvoice.viewModel.IncomingViewModel;


import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class ServiceIncoming extends Service {

    WindowManager wm;
    View mView;



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createBinding();

        WindowManager.LayoutParams params = setParams();

        mView = mBinding.getRoot();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mBinding.textView.setText((String) intent.getExtras().get("incomingNumber"));
        wm.addView(mView, params);


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(wm != null) {
            if(mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            wm = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private ServiceIncomingBinding mBinding;

    private void createBinding(){
        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflate, R.layout.service_incoming, null, false);
        mBinding.setViewModel(new IncomingViewModel());
        mBinding.setModel(new IncomingNumber());

    }

    private WindowManager.LayoutParams setParams(){
        WindowManager.LayoutParams params;

        params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        return params;
    }






}

