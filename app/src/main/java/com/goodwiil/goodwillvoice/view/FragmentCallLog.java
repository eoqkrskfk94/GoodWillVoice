package com.goodwiil.goodwillvoice.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.SubItemAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentCallLogBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;

import java.util.ArrayList;


public class FragmentCallLog extends Fragment {
    private FragmentCallLogBinding mBinding;
    private ArrayList<CallLogInfo> callLogInfos;
    private SubItemAdapter itemAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = FragmentCallLogBinding.inflate(inflater, container, false);
        //mBinding.setViewModel(new SplashViewModel());
        mBinding.setLifecycleOwner(getActivity());

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
            callLogInfos = CallLogDataManager.getCallLog(getContext());
            System.out.println("hello");
        }

//        if(callLogInfos.size() == 0)
//            swipeDownImage.setVisibility(View.VISIBLE);
//        else
//            swipeDownImage.setVisibility(View.GONE);

        System.out.println(callLogInfos.size());

        itemAdapter = new SubItemAdapter(getActivity(), callLogInfos);
        mBinding.headerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.headerRecyclerView.setAdapter(itemAdapter);

        mBinding.text.setText("yes");



        return mBinding.getRoot();
    }


}