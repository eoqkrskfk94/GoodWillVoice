package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityFaqBinding;
import com.goodwiil.goodwillvoice.viewModel.FaqViewModel;

public class ActivityFaq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        mBinding.tv.setText("FAQ");
    }

    private ActivityFaqBinding mBinding;
    private void createBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_faq);
        mBinding.setViewModel(new FaqViewModel());
    }
}
