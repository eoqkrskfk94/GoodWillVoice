package com.goodwiil.goodwillvoice.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivitySplashBinding;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        //mBinding.getViewModel().startApp(this);


        if (!AppDataManager.isConnected(this)) {
            noService();
            return;
        }

        else{
            mBinding.getViewModel().getPermission(this);
        }


        //finish();

    }

    private ActivitySplashBinding mBinding;

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mBinding.setViewModel(new SplashViewModel());

    }


    /**
     * 현재 인터넷에 접속할 수 없기 때문에 서비스를 사용할 수 없다는 메시지와
     * 화면 종료 버튼을 보여준다.
     */
    private void noService() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_network, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        Button networkDialogCancelBtn = (Button)alertDialog.findViewById(R.id.btn_yes);

        networkDialogCancelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
