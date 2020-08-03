package com.goodwiil.goodwillvoice.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.goodwiil.goodwillvoice.model.IncomingNumber;

public class ScreenManager {

    //Activity 화면 이동 함수
    public static void startActivity(Context context, Class c){
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    //Service 시작 (이름과 번호도 같이 보내기)
    public static void startService(Context context, Class c, IncomingNumber incomingNumber){
        Intent serviceIntent = new Intent(context, c);
        serviceIntent.putExtra("incomingNumber",incomingNumber);
        context.startService(serviceIntent);
    }

    //Toast 출력 함수
    public static void printToast(Context context, String string){
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
