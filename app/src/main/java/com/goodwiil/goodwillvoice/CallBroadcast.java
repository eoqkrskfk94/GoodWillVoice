package com.goodwiil.goodwillvoice;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

import com.goodwiil.goodwillvoice.model.IncomingNumber;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;
import com.goodwiil.goodwillvoice.view.ServiceIncoming;

import static android.content.Context.POWER_SERVICE;

public class CallBroadcast extends BroadcastReceiver {

    private String number;
    private String incomingNumber;
    private  String incomingName;
    private IncomingNumber model;

    PowerManager powerManager;
    static PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")


    @Override
    public void onReceive(Context context, Intent intent) {

        //화면이 꺼져도 앱 실행 기능
        setWakeLock(context);


        //전화 상태 받아오기
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if(number != null) incomingNumber = number;
        incomingName = "";





        //전화가 울릴때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){

            if(number != null){
                incomingName = CallLogDataManager.contactExists(context, number);
                if(incomingName.equals("unknown"))
                    model = new IncomingNumber(incomingNumber, incomingName);
                    ScreenManager.startService(context, ServiceIncoming.class, model);
            }

        }

        //전화를 받았을때, 전화를 걸때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            context.stopService(new Intent(context, ServiceIncoming.class));
            if(number != null) {
                model = new IncomingNumber(incomingNumber, incomingName);
                ScreenManager.startService(context, ServiceIncoming.class, model);
            }

        }

        //전화를 끊었을때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)){
            context.stopService(new Intent(context, ServiceIncoming.class));
            context.stopService(new Intent(context, ServiceCall.class));

        }

    }


    private void setWakeLock(Context context){
        powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);

        if(wakeLock == null){
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerWakeLock:");

        }
    }
}