package jco.goldenrule.Activities.LoginRegistrationActivities;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.Calendar;

import jco.goldenrule.Activities.HomeActivity;
import jco.goldenrule.BroadcastReceivers.AlarmReceiver;
import jco.goldenrule.R;
import jco.goldenrule.Utility.Constant;
import jco.goldenrule.Utility.PreferenceClass;


public class SplashActivity extends AppCompatActivity {
    Context mContext;
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


    private void setupMessageReceival(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = "channel Id";
            String channelName = "chanell name";
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d("TAAG", "Key: " + key + " Value: " + value);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupAlarm();
        setupMessageReceival();

        loadingSpinner =(AVLoadingIndicatorView)findViewById(R.id.loadingSpinner);
        loadingSpinner.smoothToShow();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loadingSpinner.smoothToHide();
            }
        },3000);


        mContext = this;
        setStatusBarTranslucent(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        },Constant.AppConstant.SPLASH_TIME_OUT);

        /*
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){


           }
*/


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
