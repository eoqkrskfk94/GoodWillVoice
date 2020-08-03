package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.CallBroadcast;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;
import com.goodwiil.goodwillvoice.view.ServiceEnd;

import androidx.constraintlayout.widget.ConstraintLayout;

public class EndViewModel {

    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view){
        view.getContext().stopService(new Intent(view.getContext(), ServiceEnd.class));
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.unknown));
    }

    public void familyBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.family) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.family));
        off(view);

    }
    public void friendBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.friend) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.friend));
        off(view);
    }
    public void loanBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.loan) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.loan));
        off(view);
    }
    public void insuranceBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.insurance) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.insurance));
        off(view);
    }
    public void adBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.ad) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.ad));
        off(view);
    }
    public void parcelBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.parcel) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.parcel));
        off(view);
    }
    public void happyCallBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.happy_call) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.happy_call));
        off(view);
    }
    public void policeBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.police) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.police));
        off(view);
    }
    public void governBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.government) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.government));
        off(view);
    }
    public void othersBtnClick(View view, ConstraintLayout cl){
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.others) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.others));
        off(view);
    }


    public void off(View view){
        view.getContext().stopService(new Intent(view.getContext(), ServiceEnd.class));
    }
}
