package com.goodwiil.goodwillvoice.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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

        //전화 상태 권한 받기
        checkPermission();

        //배터리 최적화 권한 받기
        checkPermissionBattery();

        //앱 위에 그리기 권한 받기
        checkPermissionOverlay();

        populateSpinner();
        setUserInfo();
    }

    private ActivitySignUpBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mBinding.setViewModel(new SignUpViewModel());
        mBinding.setModel(new User());
    }

    String[] permission_list = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    //권한 받기
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

    //배터리 최적화 권한 받기
    private void checkPermissionBattery() {
        Intent intent = new Intent();
        String packageName = getPackageName();
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        Boolean battery = pm.isIgnoringBatteryOptimizations(packageName);

        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + packageName));
            startActivity(intent);
        }
    }

    //overlay 권한받기
    public void checkPermissionOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean overlay = Settings.canDrawOverlays(this);// 마시멜로우 이상일 경우

            if (!Settings.canDrawOverlays(this)) {              // 체크
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1);
            }
        }
    }


    private void populateSpinner() {
        ArrayList<String> years = new ArrayList<String>();

        years.add("출생년도");

        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1900; i--) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        mBinding.spYear.setAdapter(adapter);
    }

    private void setUserInfo() {
        User user = AppDataManager.getUserModel();

        if (user != null) {
            mBinding.etNickName.setText(user.getNickName());
        }
    }
}
