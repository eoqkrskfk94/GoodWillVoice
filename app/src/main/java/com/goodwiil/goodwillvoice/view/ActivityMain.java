package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityMainBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.MainViewModel;

public class ActivityMain extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {
    private static final String DEFAULT_PATTERN = "%d%%";
    CircleProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        //현재 회원 등록 정보 화면에 출력하기
        getData();

        //프로그레스 바 세팅
        circleProgressBar = findViewById(R.id.cpb_circlebar);
        circleProgressBar.setProgress(80);
    }

    private ActivityMainBinding mBinding;

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(new MainViewModel());
    }

    private void getData() {
        User user = AppDataManager.getUserModel();

//        mBinding.tv.setText(
//                "Year : "+user.getYear() + "\n"
//                        +"Gender : "+user.getGender() + "\n"
//                        +"Career : "+user.getCareer() + "\n"
//                        +"City : "+user.getCity() + "\n"
//        );
    }

    @Override
    public void onBackPressed() {
        mBinding.getViewModel().onBackClick(this);
    }

    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }
}
