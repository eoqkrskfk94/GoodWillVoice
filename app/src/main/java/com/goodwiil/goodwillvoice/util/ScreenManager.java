package com.goodwiil.goodwillvoice.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ScreenManager {


    public static void startActivity(Context context, Class c){
        Intent intent = new Intent(context, c);
        context.startActivity(intent);

    }

    public static void printToast(Context context, String string){
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
