package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityMainBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.MainViewModel;
import com.goodwiil.goodwillvoice.viewModel.MenuViewModel;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        //현재 회원 등록 정보 화면에 출력하기
        getData();

        //전화 상태 권한 받기
        mBinding.getViewModel().checkPermission(this);

    }

    private ActivityMainBinding mBinding;
    private void createBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(new MainViewModel());
    }

    private void getData(){
        User user = AppDataManager.getUserModel();
        mBinding.tv.setText(
                "Year : "+user.getYear() + "\n"
                        +"Gender : "+user.getGender() + "\n"
                        +"Career : "+user.getCareer() + "\n"
                        +"City : "+user.getCity() + "\n"
        );
    }
}
