package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.view.View;

public class CallLogViewModel extends CallCenterViewModel {

    public void backBtnClick(View view){
        ((Activity)view.getContext()).finish();
    }


}
