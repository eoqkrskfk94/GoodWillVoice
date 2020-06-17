package com.goodwiil.goodwillvoice.viewModel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

public class MainViewModel {

    String[] permission_list = {
            Manifest.permission.READ_PHONE_STATE
    };



    public void checkPermission(Context context) {
        //현재 안드로이드 버전이 6.0미만이면 메서드를 종료한다.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;

        for (String permission : permission_list) {
            //권한 허용 여부를 확인한다.
            int chk = context.checkCallingOrSelfPermission(permission);

            if (chk == PackageManager.PERMISSION_DENIED) {
                //권한 허용을여부를 확인하는 창을 띄운다
                (Activity)context.requestPermissions(permission_list, 0);
            }
        }
    }



}
