package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityCallLogBinding;
import com.goodwiil.goodwillvoice.model.CallLogData;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.viewModel.CallLogViewModel;

import java.util.ArrayList;

public class ActivityCallLog extends AppCompatActivity {
    //data binding
    private ActivityCallLogBinding mBinding;
    private int numberOfIncomingCall;
    private int numberOfknown;
    private int numberOfUnknown;
    private int unknownRejected;
    private int unknownReceived;
    private int unknownMissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();
        processingData();

        mBinding.tvTotal.setText(String.valueOf(numberOfIncomingCall));
        mBinding.tvTotalUnknown.setText(String.valueOf(numberOfUnknown));
        mBinding.tvTotalUnknownMore.setText(String.valueOf(numberOfUnknown));
        mBinding.tvTotalKnown.setText(String.valueOf(numberOfknown));

        mBinding.tvReceived.setText(String.valueOf(unknownReceived));
        mBinding.tvRejected.setText(String.valueOf(unknownRejected));
        mBinding.tvMissed.setText(String.valueOf(unknownMissed));

        mBinding.tvMax.setText(CallLogDataManager.secondsToString(CallLogDataManager.callAnalysisInfo.getUnknownCallMax()));
        mBinding.tvMin.setText(CallLogDataManager.secondsToString(CallLogDataManager.callAnalysisInfo.getUnknownCallMin()));
        mBinding.tvAverage.setText(CallLogDataManager.secondsToString(CallLogDataManager.unknownCallTotal/CallLogDataManager.unknownCallTotalNum));
        // Write a message to the database

    }

    //Data 분별하기
    public void processingData() {
        ArrayList<CallLogData> callLogData = new ArrayList<>();

        // 사용자 통화내역 기록 가져오기
        ArrayList<CallLogInfo> callLogList = CallLogDataManager.getCallLog(this);

        numberOfIncomingCall = 0;
        numberOfknown = 0;
        numberOfUnknown = 0;
        unknownRejected = 0;
        unknownReceived = 0;
        unknownMissed = 0;

        for (CallLogInfo info : callLogList) {

            if (!info.getType().equals("OUTGOING")) {
                numberOfIncomingCall++;
                if (info.getName().equals("unknown")) {
                    numberOfUnknown++;
                    if(info.getDuration() > 0){
                        unknownReceived++;
                    }
                }
            }
            if (info.getName().equals("unknown")) {
                if(info.getType().equals("REJECTED")){
                    unknownRejected++;
                }
                else if(info.getType().equals("MISSED")){
                    unknownMissed++;
                }
            }
        }
        numberOfknown = numberOfIncomingCall - numberOfUnknown;
    }

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_log);
        mBinding.setViewModel(new CallLogViewModel());
    }

}
