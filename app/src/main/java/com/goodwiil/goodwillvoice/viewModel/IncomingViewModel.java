package com.goodwiil.goodwillvoice.viewModel;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.model.ContactInfo;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.view.ServiceIncoming;

import java.util.ArrayList;

public class IncomingViewModel {


    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view){
        view.getContext().stopService(new Intent(view.getContext(), ServiceIncoming.class));
    }



}
