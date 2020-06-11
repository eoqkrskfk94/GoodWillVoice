package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivitySignUpBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.viewModel.SignUpViewModel;

public class ActivitySignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();




    }


    private ActivitySignUpBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mBinding.setViewModel(new SignUpViewModel());
        mBinding.setModel(new User());

        //mBinding.getViewModel().yearSpinner(this,mBinding.yearSpinner);


    }
}
