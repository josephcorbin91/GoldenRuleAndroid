package jco.goldenrule.Activities.LoginRegistrationActivities;

import android.app.AlarmManager;
import android.app.PendingIntent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ServerValue;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Calendar;

import jco.goldenrule.Activities.HomeActivity;
import jco.goldenrule.BroadcastReceivers.AlarmReceiver;
import jco.goldenrule.R;
import jco.goldenrule.Utility.Constant;
import jco.goldenrule.Utility.PreferenceClass;


public class SplashActivity extends AppCompatActivity {
    Context mContext;
    private Button mRegBtn;
    private Button mLoginBtn;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private AVLoadingIndicatorView loadingSpinner;



    protected void setStatusBarTranslucent(boolean makeTranslucent){
        if(makeTranslucent){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void setupAlarm(){
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        /*
        calendar.set(Calendar.HOUR,12);
        calendar.set(Calendar.MINUTE,34);
        */
        calendar.add(Calendar.MINUTE,1);

        Intent newIntent = new Intent(this,AlarmReceiver.class);
        Intent intent=new Intent(this,AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,alarmIntent);
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+10*1000,alarmIntent);
        System.out.println("Alarm set in "+calendar.getTime());

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupAlarm();
        loadingSpinner =(AVLoadingIndicatorView)findViewById(R.id.loadingSpinner);
        loadingSpinner.smoothToShow();

        System.out.println("Current user " +FirebaseAuth.getInstance().getCurrentUser());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loadingSpinner.smoothToHide();
            }
        },3000);


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
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
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
                            //loadingSpinner.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            //loadingSpinner.setVisibility(View.INVISIBLE);
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
            }, 4000);
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
