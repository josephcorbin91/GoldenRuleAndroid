package jco.goldenrule.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import jco.goldenrule.Activities.ChatActivities.MainActivity;
import jco.goldenrule.R;
import jco.goldenrule.Utility.Constant;
import jco.goldenrule.Utility.PreferenceClass;


public class SplashActivity extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        mContext = this;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if(PreferenceClass.getBooleanPreferences(mContext, Constant.User.IS_LOGIN)){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }else{

                    startActivity(new Intent(SplashActivity.this, StartActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }

            }
        }, Constant.AppConstant.SPLASH_TIME_OUT);
    }
}
