package com.goodwiil.goodwillvoice;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.goodwiil.goodwillvoice.model.IncomingNumber;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;
import com.goodwiil.goodwillvoice.view.ServiceIncoming;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.POWER_SERVICE;

public class CallBroadcast extends BroadcastReceiver {

    private String number;
    private Vibrator vibrator;
    private String incomingNumber;
    private  String incomingName;
    private IncomingNumber model;
    private int counter;
    static TimerTask tt;
    private SharedPreferences prefs;
    private PowerManager powerManager;
    static PowerManager.WakeLock wakeLock;
    private Boolean vibrate;
    private String level;


    @SuppressLint("InvalidWakeLockTag")


    @Override
    public void onReceive(Context context, Intent intent) {

        //화면이 꺼져도 앱 실행 기능
        setWakeLock(context);
        setting(context);

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
            if (!(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL))){
                if(number != null) {
                    model = new IncomingNumber(incomingNumber, incomingName);

                    wakeLock.acquire();

                    //앱 위에 그리기 권한이 있으
                    if(Settings.canDrawOverlays(context)){
                        ScreenManager.startService(context, ServiceCall.class, model);

                        tt = timerTaskMaker();
                        final Timer timer = new Timer();
                        timer.schedule(tt, 0, 1000);

                    }


                }
            }


        }

        //전화를 끊었을때
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)){

            if(wakeLock.isHeld()) wakeLock.release();

            tt.cancel();
            context.stopService(new Intent(context, ServiceIncoming.class));
            context.stopService(new Intent(context, ServiceCall.class));

        }

    }

    private void setting(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        vibrate = prefs.getBoolean("vibration_alarm", true);
        level = prefs.getString("level_list", "강");
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    }


    private void setWakeLock(Context context){
        powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);

        if(wakeLock == null){
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerWakeLock:");

        }
    }
    public TimerTask timerTaskMaker(){
        TimerTask tempTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                vibrate(counter);
            }
        };
        return  tempTask;
    }


    //진동 기능
    public void vibrate(int sec){


        int call_length[] = {0, 0, 0};
        if (level.equals("")) {
            //call_length[0] = 540;
            //call_length[1] = 900;
            call_length[0] = 30;
            call_length[1] = 60;
            call_length[2] = 90;
        }else if (level.equals("약")) {
            //call_length[0] = 180;
            //call_length[1] = 300;
            call_length[0] = 30;
            call_length[1] = 60;
            call_length[2] = 90;
        } else if (level.equals("중")) {
            //call_length[0] = 540;
            //call_length[1] = 900;
            call_length[0] = 20;
            call_length[1] = 30;
            call_length[2] = 40;
        } else if (level.equals("강")) {
            //call_length[0] = 60;
            //call_length[1] = 180;
            call_length[0] = 10;
            call_length[1] = 15;
            call_length[2] = 20;
        }

        if(sec == call_length[0]){
            if (vibrate) vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else if(sec == call_length[1]){
            if (vibrate) vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else if(sec == call_length[2]){
            if (vibrate) vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));

        }


    }

}