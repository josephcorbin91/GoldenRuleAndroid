package jco.goldenrule.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import jco.goldenrule.Activities.ChatActivities.ChatActivity;
import jco.goldenrule.Activities.ChatActivities.MainActivityChat;
import jco.goldenrule.R;


public class HomeActivity extends AppCompatActivity {


    private Context mContext;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private ImageButton mSettingButton, mMessagingButton, mLocationButton,mInfoButton;

    private LinearLayout backgroundLayout;


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

        setContentView(R.layout.activity_home);


        System.out.println("Current user in home activity"+ FirebaseAuth.getInstance().getCurrentUser());


        this.mSettingButton = (ImageButton)findViewById(R.id.settingButton);
        this.mMessagingButton = (ImageButton)findViewById(R.id.messageButton);
        this.mLocationButton = (ImageButton)findViewById(R.id.locatorButton);
        this.mInfoButton = (ImageButton)findViewById(R.id.infoButton);
        this.mContext=getApplicationContext();

        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Settings clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mMessagingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Messaging clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MainActivityChat.class));

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Info clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Location clicked", Toast.LENGTH_SHORT).show();
            }
        });



        setStatusBarTranslucent(true);



       // initializeUIComponents();

        //setupAlarm();

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }
}
