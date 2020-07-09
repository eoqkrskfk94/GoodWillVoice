package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityCallLogBinding;
import com.goodwiil.goodwillvoice.model.CallLogData;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.ContactInfo;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.viewModel.CallLogViewModel;

import java.util.ArrayList;

import static com.goodwiil.goodwillvoice.util.CallLogDataManager.getContacts;

public class ActivityCallLog extends AppCompatActivity {
    public MyAdapter adapter;
    public ArrayList<CallLogInfo> logList = new ArrayList<>();
    public ArrayList<CallLogData> dataList = new ArrayList<>();
    public ListView listCallLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        // adapter with listview
        listCallLog = (ListView) findViewById(R.id.list_call_log);
        adapter = new MyAdapter(this);
        listCallLog.setAdapter(adapter);

        dataList = processingData();
    }

    //Data 분별하기
    public ArrayList<CallLogData> processingData(){
        ArrayList<CallLogData> callLogData = new ArrayList<>();

        // 사용자 통화내역 기록 가져오기
        ArrayList<CallLogInfo> callLogList = CallLogDataManager.getCallLog(this);

        int numberOfIncomingCall = 0;
        int numberOfknown = 0;
        int numberOfUnknown = 0;

        for(CallLogInfo info : callLogList){
            if(info.getType().equals("INCOMING")){
                numberOfIncomingCall++;
                if(info.getName().equals("unknown")){
                    numberOfUnknown++;
                }
            }
        }

        numberOfknown = numberOfIncomingCall - numberOfUnknown;

        callLogData.add(new CallLogData("총 수신전화",numberOfIncomingCall));
        callLogData.add(new CallLogData("등록된 전화",numberOfknown));
        callLogData.add(new CallLogData("미등록된 전화",numberOfUnknown));

        return callLogData;
    }



    //data binding
    private ActivityCallLogBinding mBinding;

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_log);
        mBinding.setViewModel(new CallLogViewModel());
    }

    // adapter with array
    public class RowDataViewHolder {
        public TextView nameTvHolder;
        public TextView valueTvHolder;
    }

    // inner class MyAdapter
    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.data, dataList);
            lnf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            RowDataViewHolder viewHolder;
            if (convertView == null) {
                convertView = lnf.inflate(R.layout.data, parent, false);
                viewHolder = new RowDataViewHolder();
                viewHolder.nameTvHolder = (TextView) convertView.findViewById(R.id.name_tv);
                viewHolder.valueTvHolder = (TextView) convertView.findViewById(R.id.value_tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RowDataViewHolder) convertView.getTag();
            }
            viewHolder.nameTvHolder.setText(dataList.get(position).name);
            viewHolder.valueTvHolder.setText(Integer.toString(dataList.get(position).value));

            return convertView;
        }
    }

}
