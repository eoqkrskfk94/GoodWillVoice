package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.goodwiil.goodwillvoice.R;
//import com.goodwiil.goodwillvoice.databinding.ActivityCallLogBinding;
import com.goodwiil.goodwillvoice.model.CallLogData;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.viewModel.CallLogViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityCallLog extends AppCompatActivity {
    //data binding
//    private ActivityCallLogBinding mBinding;

    public ArrayList<CallLogInfo> logList = new ArrayList<>();
    public ArrayList<CallLogData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        createBinding();


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

    }

    //Data 분별하기
    public ArrayList<CallLogData> processingData() {
        ArrayList<CallLogData> callLogData = new ArrayList<>();

        // 사용자 통화내역 기록 가져오기
        ArrayList<CallLogInfo> callLogList = CallLogDataManager.getCallLog(this);

        int numberOfIncomingCall = 0;
        int numberOfknown = 0;
        int numberOfUnknown = 0;

        for (CallLogInfo info : callLogList) {
            if (info.getType().equals("INCOMING")) {
                numberOfIncomingCall++;
                if (info.getName().equals("unknown")) {
                    numberOfUnknown++;
                }
            }
        }

        numberOfknown = numberOfIncomingCall - numberOfUnknown;

        callLogData.add(new CallLogData("총 수신전화", numberOfIncomingCall));
        callLogData.add(new CallLogData("등록된 전화", numberOfknown));
        callLogData.add(new CallLogData("미등록된 전화", numberOfUnknown));

        return callLogData;
    }

    private void createBinding() {
//        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_log);
//        mBinding.setViewModel(new CallLogViewModel());
    }


}
