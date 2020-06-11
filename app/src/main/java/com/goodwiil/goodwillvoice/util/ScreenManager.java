package com.goodwiil.goodwillvoice.util;

import android.content.Context;
import android.content.Intent;

public class ScreenManager {


    public static void startActivity(Context context, Class c){
        Intent intent = new Intent(context, c);
        context.startActivity(intent);

    }
}
