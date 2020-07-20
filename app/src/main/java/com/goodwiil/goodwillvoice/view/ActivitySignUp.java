package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivitySignUpBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.SignUpViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivitySignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();
        populateSpinner();
        setUserInfo();
    }


    private ActivitySignUpBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mBinding.setViewModel(new SignUpViewModel());
        mBinding.setModel(new User());
    }

    private void populateSpinner(){
        ArrayList<String> years = new ArrayList<String>();

        years.add("출생년도");

        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1900; i--) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,years);
        mBinding.spYear.setAdapter(adapter);
    }

    private void setUserInfo(){
        User user = AppDataManager.getUserModel();

        if(user != null){
            mBinding.etNickName.setText(user.getNickName());
        }
    }
}
