package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;
import com.goodwiil.goodwillvoice.view.ServiceIncoming;

public class CallViewModel {

    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view){
        view.getContext().stopService(new Intent(view.getContext(), ServiceCall.class));
    }

    public void friendBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "지인");
    }
    public void familyBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "가족");
    }
    public void loanBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "대출");
    }
    public void insuranceBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "보험");
    }
    public void adBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "광고");
    }
    public void parcelBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "택배");
    }
    public void happyCallBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "해피콜");
    }
    public void policeBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "경찰");
    }
    public void governBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "금강원");
    }
    public void othersBtnClick(View view){
        ScreenManager.printToast(view.getContext(), "기타");
    }

}
