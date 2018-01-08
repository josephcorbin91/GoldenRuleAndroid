package jco.goldenrule.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;



import java.util.Calendar;

import jco.goldenrule.Activities.ChatActivities.ChatActivity;
import jco.goldenrule.Activities.ChatActivities.MainActivity;
import jco.goldenrule.BroadcastReceivers.AlarmReceiver;
import jco.goldenrule.R;


public class HomeActivity extends AppCompatActivity {


    private Context mContext;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private FloatingActionButton mSettingButton, mMessagingButton, mLocationButton;

    private LinearLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.backgroundLayout= (LinearLayout)findViewById(R.id.main_screen);
        setContentView(R.layout.activity_home);
        mContext=this;
        backgroundLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });



       // initializeUIComponents();

        setupAlarm();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initializeUIComponents(){

        /*
        mSettingButton = (FloatingActionButton)findViewById(R.id.settingsButton);
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });
        mMessagingButton = (FloatingActionButton)findViewById(R.id.messageButton);
        mMessagingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), MessagingActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        mLocationButton = (FloatingActionButton)findViewById(R.id.locatorButton);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

*/



    }
private void setupAlarm(){
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,14);
        Intent intent=new Intent(this,AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this,0,intent,0);
    //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,alarmIntent);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+10*1000,alarmIntent);
    Toast.makeText(mContext, "Alarm set", Toast.LENGTH_SHORT).show();

}
}
