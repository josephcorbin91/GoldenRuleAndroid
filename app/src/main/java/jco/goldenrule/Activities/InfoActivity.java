package jco.goldenrule.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.facebook.CallbackManager;

import jco.goldenrule.R;


public class InfoActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 123;


    private VideoView videoView;
    private MediaController mediaControls;
    private CallbackManager callbackManager;

    private Context mContext;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

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

        setContentView(R.layout.activity_info);



        setStatusBarTranslucent(true);
        mediaControls = new MediaController(InfoActivity.this);
        videoView = (VideoView) findViewById(R.id.videoView);
        try{
            videoView.setMediaController(mediaControls);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.golden_rule_intro_video));


        }
        catch (Exception exception){
            Log.e("Error", exception.getMessage());
            exception.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                videoView.setMediaController(null);

                videoView.start();


            }
        });




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
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));


    }
}
