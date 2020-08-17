package com.goodwiil.goodwillvoice.view;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ServiceWarningBinding;
import com.goodwiil.goodwillvoice.viewModel.WarningViewModel;

import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class ServiceWarning extends Service{
    private int state;
    WindowManager wm;
    View mView;
    WindowManager.LayoutParams params;
    private float START_Y;                            //움직이기 위해 터치한 시작 점
    private int PREV_Y;                                //움직이기 이전에 뷰가 위치한 점
    public static ServiceWarning ins;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createBinding();
        addWindowManager();
        ins = this;

        return super.onStartCommand(intent, flags, startId);
    }


    private void addWindowManager() {
        mView = mBinding.getRoot();
        mView.setOnTouchListener(mViewTouchListener);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = setParams();
        wm.addView(mView, params);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wm != null) {
            if (mView != null) {
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


    private ServiceWarningBinding mBinding;

    private void createBinding() {
        mBinding = ServiceWarningBinding.inflate(LayoutInflater.from(this));
        mBinding.setViewModel(new WarningViewModel());

    }

    private WindowManager.LayoutParams setParams() {
        WindowManager.LayoutParams params;

        params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        return params;
    }

    private View.OnTouchListener mViewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:                //사용자 터치 다운이면
                    START_Y = event.getRawY();                    //터치 시작 점
                    PREV_Y = params.y;                            //뷰의 시작 점
                    break;
                case MotionEvent.ACTION_MOVE:
                    int y = (int) (event.getRawY() - START_Y);    //이동한 거리

                    //터치해서 이동한 만큼 이동 시킨다
                    params.y = PREV_Y + y;

                    //optimizePosition();        //뷰의 위치 최적화
                    wm.updateViewLayout(mView, params);    //뷰 업데이트
                    break;
            }
            return true;
        }
    };

    public static ServiceWarning getInstance(){
        return ins;
    }


    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(mView != null) setState(state);
        }
    };
    public void update(int state){
        this.state = state;
        Message msg = handler.obtainMessage();
        handler.sendMessage(msg);
    }

    public TimerTask timerTaskMaker() {
        TimerTask tempTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);

                //counter++;
            }
        };
        return tempTask;
    }

    public void setState(int state){
        if(state == 0){
            mBinding.tvWarning.setText("양호");
            mBinding.tvWarning.setTextColor(getColor(R.color.warning1));
        }
        else if(state == 1){
            mBinding.tvWarning.setText("주의");
            mBinding.tvWarning.setTextColor(getColor(R.color.warning2));

        }
        else if(state == 2){
            mBinding.tvWarning.setText("위험");
            mBinding.tvWarning.setTextColor(getColor(R.color.warning3));

        }
    }


}
