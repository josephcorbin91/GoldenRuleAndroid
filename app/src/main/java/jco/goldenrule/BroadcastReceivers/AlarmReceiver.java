package jco.goldenrule.BroadcastReceivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Toast;


/**
 * Created by jco on 12/18/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()!=null) {
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                Toast.makeText(context, "Boot completed", Toast.LENGTH_LONG).show();

            }
            if (intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")) {
                Toast.makeText(context, "Quickboot power on", Toast.LENGTH_LONG).show();

            }
        }



        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);


          Intent alarmIntent = new Intent(context,AlarmReceiver.class);
          PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,alarmIntent,0);
          AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
          manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime() + 60 * 1000, pendingIntent);
          //Toast.makeText(context,"Alarm Set in boot completed", Toast.LENGTH_SHORT).show();



    }
}
