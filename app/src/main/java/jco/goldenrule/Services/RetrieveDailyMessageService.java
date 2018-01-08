package jco.goldenrule.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import java.io.IOException;

import jco.goldenrule.API.GoldenRuleApi;


public class RetrieveDailyMessageService extends IntentService {
    private LocalBroadcastManager localBroadcastManager;
    public static final String RESULT_OK = "RESULT_OK";
    public static final String RESULT_FAIL = "RESULT_FAIL";
    public static final String SERVICE_UNAVAILABLE = "SERVICE_UNAVAILABLE";

    public RetrieveDailyMessageService() {
        super("RetrieveDailyMessageService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    private void sendResult(String message){
        Intent intent = new Intent(RESULT_OK);
        if(message != null){
            intent.putExtra(RESULT_OK,message);
        }
        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {


        Log.i("State", "Starting retrieval of daily message");

        GoldenRuleApi apiService = new GoldenRuleApi();
        try {
            apiService.getMessages("123");
            sendResult(RESULT_OK);
        }


        catch(IOException serviceUnavailableException){
            //if(serviceUnavailableException.getMessage().contains("failed to connect"))
               System.out.println("R!! "+serviceUnavailableException.getMessage()+serviceUnavailableException.getLocalizedMessage());
                sendResult(SERVICE_UNAVAILABLE);

        }


        }



}
