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

import jco.goldenrule.Activities.ChatActivities.MainActivityChat;
import jco.goldenrule.R;


public class LocatorActivity extends AppCompatActivity {


    private Context mContext;

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

        setContentView(R.layout.activity_locator);


        System.out.println("Current user in home activity"+ FirebaseAuth.getInstance().getCurrentUser());



        setStatusBarTranslucent(true);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));


    }
}
