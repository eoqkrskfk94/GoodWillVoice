package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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
        checkPermission();

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


    String[] permission_list = {
            Manifest.permission.READ_PHONE_STATE
    };


    public void checkPermission() {

        for (String permission : permission_list) {
            //권한 허용 여부를 확인한다.
            int chk = checkCallingOrSelfPermission(permission);

            if (chk == PackageManager.PERMISSION_DENIED) {
                //권한 허용을여부를 확인하는 창을 띄운다

                requestPermissions(permission_list, 0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        mBinding.getViewModel().onBackClick(this);
    }

}
