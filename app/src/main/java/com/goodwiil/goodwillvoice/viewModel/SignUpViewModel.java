package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.view.ActivityMain;
import com.goodwiil.goodwillvoice.view.ActivitySignUp;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;


public class SignUpViewModel {

    public void yearSpinner(ActivitySignUp view, Spinner spinner){
        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getBaseContext(), R.layout.activity_sign_up, years);
        spinner.setAdapter(adapter);

    }

    public void signUpBtnClick(View view, ConstraintLayout cl){


        String year = ((Spinner)cl.getChildAt(2)).getSelectedItem().toString();
        String gender = ((Spinner)cl.getChildAt(3)).getSelectedItem().toString();
        String career = ((Spinner)cl.getChildAt(4)).getSelectedItem().toString();


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
