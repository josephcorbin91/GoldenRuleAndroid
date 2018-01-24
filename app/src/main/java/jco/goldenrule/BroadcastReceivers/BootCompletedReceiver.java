package jco.goldenrule.BroadcastReceivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Calendar;


/**
 * Created by jco on 12/18/2017.
 */

public class BootCompletedReceiver extends BroadcastReceiver {
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent originalIntent) {
        if(originalIntent.getAction()!=null) {
            String action = originalIntent.getAction();
            if(action.equals("android.intent.action.BOOT_COMPLETED")||action.equals("android.intent.action.QUICKBOOT_POWERON")){

            Toast.makeText(context, "Home boot power on", Toast.LENGTH_LONG).show();

                Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                System.out.println("intent received "+action);

                alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(System.currentTimeMillis());
        /*
        calendar.set(Calendar.HOUR,12);
        calendar.set(Calendar.MINUTE,34);
        */
                calendar.add(Calendar.MINUTE,1);

                Intent intent=new Intent(context,AlarmReceiver.class);
                alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,alarmIntent);
                //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+10*1000,alarmIntent);
                System.out.println("Alarm set in "+calendar.getTime());


            }
        }






    }
}
