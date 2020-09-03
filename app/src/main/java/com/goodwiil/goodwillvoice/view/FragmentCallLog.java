package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.FragmentCallLogBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;


public class FragmentCallLog extends Fragment {
    private FragmentCallLogBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_call_log, container, false);
        //mBinding.setViewModel(new SplashViewModel());



        return mBinding.getRoot();
    }


}