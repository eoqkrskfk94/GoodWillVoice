package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.view.ActivityMain;

public class SignUpViewModel {


    public void signUpBtnClick(View view){
        Intent intent = new Intent(view.getContext(), ActivityMain.class);
        view.getContext().startActivity(intent);
    }


}
