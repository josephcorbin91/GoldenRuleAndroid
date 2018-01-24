package jco.goldenrule.BroadcastReceivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Toast;

import jco.goldenrule.Activities.HomeActivity;


/**
 * Created by jco on 12/18/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {







        System.out.println("Alarm for Home activity");
        Intent startHomeActivityIntent = new Intent(context, HomeActivity.class);
        startHomeActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startHomeActivityIntent);





    }
}
