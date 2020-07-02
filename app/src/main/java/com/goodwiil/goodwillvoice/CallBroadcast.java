package com.goodwiil.goodwillvoice;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceIncoming;

import static android.content.Context.POWER_SERVICE;

public class CallBroadcast extends BroadcastReceiver {

    private String number;

    PowerManager powerManager;
    static PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")


    @Override
    public void onReceive(Context context, Intent intent) {

        powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);

        if(wakeLock == null){
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerWakeLock");

        }


        number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

        //전화 상태 받아오기
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);


        //전화가 울릴때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){

            if(number != null){
                ScreenManager.printToast(context, "INCOMMING CALL");
                Intent serviceIntent = new Intent(context, ServiceIncoming.class);
//            serviceIntent.putExtra("incomingNumber",incomingNumber);
//            serviceIntent.putExtra("incomingName",incomingName);
                context.startService(serviceIntent);
            }


        }

        //전화를 받았을때, 전화를 걸때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            //ScreenManager.printToast(context, "ANSWERED CALL");
            context.stopService(new Intent(context, ServiceIncoming.class));

        }

        //전화를 끊었을때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)){
            //ScreenManager.printToast(context, "END CALL");
            context.stopService(new Intent(context, ServiceIncoming.class));

        }

    }
}
