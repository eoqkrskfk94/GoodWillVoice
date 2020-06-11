package com.goodwiil.goodwillvoice.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class CallBroadCastManager extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        //전화 상태 받아오기
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);


        //전화가 울릴때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){

        }

        //전화를 받았을때, 전화를 걸때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)){

        }

        //전화를 끊었을때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)){

        }

    }
}
