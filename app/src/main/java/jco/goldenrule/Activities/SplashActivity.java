package jco.goldenrule.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import jco.goldenrule.Activities.ChatActivities.MainActivity;
import jco.goldenrule.R;
import jco.goldenrule.Utility.Constant;
import jco.goldenrule.Utility.PreferenceClass;


public class SplashActivity extends AppCompatActivity {
    Context mContext;
    private Button mRegBtn;
    private Button mLoginBtn;



    protected void setStatusBarTranslucent(boolean makeTranslucent){
        if(makeTranslucent){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        mContext = this;
        setStatusBarTranslucent(true);

        mRegBtn = (Button) findViewById(R.id.start_reg_btn);
        mLoginBtn = (Button) findViewById(R.id.start_login_btn);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent = new Intent(SplashActivity.this, RegisterActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                startActivity(reg_intent);

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login_intent = new Intent(SplashActivity.this, LoginActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


                startActivity(login_intent);

            }
        });

        if(PreferenceClass.getBooleanPreferences(mContext, Constant.User.IS_LOGIN)){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                }
            },Constant.AppConstant.SPLASH_TIME_OUT);
           }else{
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {


                    Animation fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                    fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            mLoginBtn.setVisibility(View.VISIBLE);
                            mRegBtn.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mLoginBtn.startAnimation(fadeInAnimation);
                    mRegBtn.startAnimation(fadeInAnimation);



                }
            }, Constant.AppConstant.SPLASH_TIME_OUT);
        }


    }
}
