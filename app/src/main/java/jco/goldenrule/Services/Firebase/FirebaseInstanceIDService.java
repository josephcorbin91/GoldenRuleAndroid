package jco.goldenrule.Services.Firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.RemoteMessage;

import jco.goldenrule.R;

/**
 * Created by AkshayeJH on 13/07/17.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FirebaseInstanceID","Refreshed token "+refreshedToken);
        FirebaseInstanceId.sendRegistrationToServer(refreshedToken);
    }
}
