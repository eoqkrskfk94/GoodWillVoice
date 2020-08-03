package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.DBManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityMain;


public class SignUpViewModel extends BaseViewModel {

    public void signUpBtnClick(View view, ConstraintLayout cl) {

        String year = ((Spinner) cl.getChildAt(2)).getSelectedItem().toString();
        String gender = ((Spinner) cl.getChildAt(3)).getSelectedItem().toString();
        String career = ((Spinner) cl.getChildAt(4)).getSelectedItem().toString();
        EditText nickNameEdit = cl.findViewById(R.id.et_nick_name);
        String nickName = nickNameEdit.getText().toString();

        if (year.equals("출생년도") || gender.equals("성별") || career.equals("직업")) {
            Toast.makeText(view.getContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User(year, gender, career, nickName);
            DBManager dbManager = new DBManager();
            dbManager.insertData(user);
            AppDataManager.setSharedPrefs(AppDataManager.SP_NAME, user);
            ScreenManager.startActivity(view.getContext(), ActivityMain.class);
            ((Activity) view.getContext()).finish();
        }

        ScreenManager.printToast(view.getContext(), "회원정보가 등록되었습니다.");
    }

    public void setPermissionPref(String name, Boolean auth) {
        AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, name, auth);
    }

}
