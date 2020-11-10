package com.goodwiil.goodwillvoice.viewModel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityMain;
import com.goodwiil.goodwillvoice.view.ActivityMain2;
import com.goodwiil.goodwillvoice.view.ActivitySignUp;

public class SplashViewModel {

    private Context context;

    String[] permission_list = {
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION
    };



    public void startApp(Context context) {
        //화원등록이 되어있으면 바로 메인화면으로 시작 아니면 회원등록화면으로 시작
        ScreenManager.startActivity(context, (AppDataManager.getUserModel() == null) ? (ActivitySignUp.class) : (ActivityMain2.class));
    }

    public void getPermission(Context context){

        this.context = context;
        //전화 상태 권한 받기
        checkPermission();

        //배터리 최적화 권한 받기
        checkPermissionBattery();

        //앱 위에 그리기 권한 받기
        checkPermissionOverlay();
    }



    //권한 받기
    public void checkPermission() {

        for (String permission : permission_list) {
            //권한 허용 여부를 확인한다.
            int chk = context.checkCallingOrSelfPermission(permission);

            if (chk == PackageManager.PERMISSION_DENIED) {
                //권한 허용을여부를 확인하는 창을 띄운다

                ActivityCompat.requestPermissions((Activity)context,permission_list, 0);
            }
        }
    }


    //배터리 최적화 권한 받기
    private void checkPermissionBattery() {
        Intent intent = new Intent();
        String packageName = context.getPackageName();
        PowerManager pm = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        Boolean battery = pm.isIgnoringBatteryOptimizations(packageName);
        AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, AppDataManager.PERMISSION_BATTERY, battery);
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + packageName));
            context.startActivity(intent);
        }
    }

    //overlay 권한받기
    public void checkPermissionOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean overlay = Settings.canDrawOverlays(context);// 마시멜로우 이상일 경우
            AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, AppDataManager.PERMISSION_OVERLAY, overlay);
            if (!Settings.canDrawOverlays(context)) {              // 체크
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + context.getPackageName()));
                ((Activity)context).startActivityForResult(intent, 1);
            }
        }
    }



}
