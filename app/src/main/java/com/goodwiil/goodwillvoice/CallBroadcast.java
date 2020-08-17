package com.goodwiil.goodwillvoice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.IncomingNumber;
import com.goodwiil.goodwillvoice.model.PhoneCall;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.DBManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;
import com.goodwiil.goodwillvoice.view.ServiceEnd;
import com.goodwiil.goodwillvoice.view.ServiceIncoming;
import com.goodwiil.goodwillvoice.view.ServiceWarning;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.content.ContextCompat;

import static android.content.Context.POWER_SERVICE;

public class CallBroadcast extends BroadcastReceiver {

    public static CallLogInfo callLogInfo;

    private String number;
    private Vibrator vibrator;
    private String incomingNumber;
    private String incomingName;
    private IncomingNumber model;
    private int counter;
    static TimerTask tt;
    private SharedPreferences prefs;
    private PowerManager powerManager;
    static PowerManager.WakeLock wakeLock;
    private Boolean vibrate;
    private Boolean voice;
    private String level;
    private static String lastState;

    private Context context;

    @SuppressLint("InvalidWakeLockTag")

    @Override
    public void onReceive(Context context, Intent intent) {

        //화면이 꺼져도 앱 실행 기능
        this.context = context;
        setWakeLock(context);
        setting(context);

//        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
//        am.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 0, 0);
//        am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

        //전화 상태 받아오기
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if (number != null) incomingNumber = number;
        incomingName = "";


        //전화가 울릴때
        if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {

            if (number != null) {
                lastState = state;
                incomingName = CallLogDataManager.contactExists(context, number);
                if (incomingName.equals("unknown"))
                    model = new IncomingNumber(incomingNumber, incomingName);
                ScreenManager.startService(context, ServiceIncoming.class, model);
                //ScreenManager.startService(context, VoiceService.class, model);

                //전화번호 기록
                callLogInfo = new CallLogInfo();
                callLogInfo.setNumber(incomingNumber);
            }
        }

        //전화를 받았을때, 전화를 걸때
        if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

            //통화 받았을때만
            if(lastState.equals("RINGING")){

                context.stopService(new Intent(context, ServiceIncoming.class));
                if (!(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL))) {
                    if (number != null) {
                        model = new IncomingNumber(incomingNumber, incomingName);

                        wakeLock.acquire();


                        //날짜 기록
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        callLogInfo.setDate(dateFormat.format(date));

                        //전화 받은 위치 기록
                        ArrayList<Double> gps = CallLogDataManager.getCurrentLoc(context);
                        callLogInfo.setLongitude(gps.get(0));
                        callLogInfo.setLatitude(gps.get(1));

                        //앱 위에 그리기 권한이 있으
                        if (Settings.canDrawOverlays(context)) {
                            ScreenManager.startService(context, ServiceCall.class, model);
                        }

                        tt = timerTaskMaker();
                        final Timer timer = new Timer();
                        timer.schedule(tt, 0, 1000);

                    }
                }

            }



        }

        //전화를 끊었을때
        if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {

            if(wakeLock.isHeld()) wakeLock.release();
            if(number != null){
                model = new IncomingNumber(incomingNumber, incomingName);

                prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                User user = AppDataManager.getUserModel();
                PhoneCall phoneCall = new PhoneCall(user,callLogInfo);
                int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    DBManager dbManager = new DBManager();
                    dbManager.insertData(phoneCall);
                    ScreenManager.printToast(context, "통화기록이 등록되었습니다.");
                }

//                if(callLogInfo != null && callLogInfo.getType() == null){
//                    if(Settings.canDrawOverlays(context)){
//                        ScreenManager.startService(context, ServiceEnd.class, model);
//                    }
//                }
            }

            lastState = state;

            if(tt != null) tt.cancel();

            context.stopService(new Intent(context, ServiceIncoming.class));
            context.stopService(new Intent(context, ServiceCall.class));
            context.stopService(new Intent(context, ServiceWarning.class));

        }
    }

    private void setting(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        voice = prefs.getBoolean("voice_alarm", false);
        vibrate = prefs.getBoolean("vibration_alarm", true);
        level = prefs.getString("level_list", "강 (1분 마다 진동)");
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    }


    private void setWakeLock(Context context) {
        powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);

        if (wakeLock == null) {
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TimerWakeLock:");
        }
    }

    public TimerTask timerTaskMaker() {
        TimerTask tempTask = new TimerTask() {
            @Override
            public void run() {
                counter++;

                //통화시간 기록
                callLogInfo.setDuration(counter);
                if(counter == 30){
                    context.stopService(new Intent(context, ServiceCall.class));
                }
                vibrateAndVoice(counter);
            }
        };
        return tempTask;
    }


    //진동 기능
    public void vibrateAndVoice(int sec) {

        long[] timings = new long[]{100, 100, 400, 200, 400};
        int[] amplitudes = new int[]{0, 50, 100, 50, 150};


        int call_length[] = {0, 0, 0};
        if (level.equals("")) {
            //call_length[0] = 540;
            //call_length[1] = 900;
            call_length[0] = 30;
            call_length[1] = 60;
            call_length[2] = 90;
        } else if (level.equals("약 (10분 마다 진동)")) {
            //call_length[0] = 180;
            //call_length[1] = 300;
            call_length[0] = 30;
            call_length[1] = 60;
            call_length[2] = 90;
        } else if (level.equals("중 (5분 마다 진동)")) {
            //call_length[0] = 540;
            //call_length[1] = 900;
            call_length[0] = 20;
            call_length[1] = 30;
            call_length[2] = 40;
        } else if (level.equals("강 (1분 마다 진동)")) {
            //call_length[0] = 60;
            //call_length[1] = 180;
            call_length[0] = 10;
            call_length[1] = 15;
            call_length[2] = 20;
        }

        if (sec == call_length[0]) {
            if (vibrate)
                //vibrator.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1));
                ScreenManager.startService(context, ServiceWarning.class, model);
                vibrator.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else if (sec == call_length[1]) {
            ServiceWarning.getInstance().update(1);
            if (vibrate)
                vibrator.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else if (sec == call_length[2]) {
            ServiceWarning.getInstance().update(2);
            if (vibrate)
                vibrator.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE));
        }

    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
}