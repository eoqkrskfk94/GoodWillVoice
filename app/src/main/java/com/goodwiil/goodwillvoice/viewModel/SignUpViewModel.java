package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.view.ActivityMain;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;


public class SignUpViewModel {

    // This gets updated once spinner item selection changes
    private MutableLiveData<Integer> spinnerSelectedPosition = new MutableLiveData<Integer>();



    public void signUpBtnClick(View view, ConstraintLayout cl){


        String year = ((Spinner)cl.getChildAt(2)).getSelectedItem().toString();
        String gender = ((Spinner)cl.getChildAt(3)).getSelectedItem().toString();
        String career = ((Spinner)cl.getChildAt(4)).getSelectedItem().toString();

        Log.i("info: " ,year + gender + career);
        System.out.println(year + gender + career);
        if(year.equals("출생년도") || gender.equals("성별") || career.equals("직업")){
        //if(year.equals(R.string.born_year) || gender.equals(R.string.gender) || career.equals(R.string.career)){
            Toast.makeText(view.getContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else{
            User user = new User(year, gender, career, "");
            AppDataManager.setSharedPrefs(AppDataManager.SP_NAME, user);
            Intent intent = new Intent(view.getContext(), ActivityMain.class);
            view.getContext().startActivity(intent);
            ((Activity)view.getContext()).finish();
        }

    }






}
