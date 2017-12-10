package com.roundarch.codetest.part3;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by Samuel on 09/12/2017.
 */

public class Part3IntentService extends IntentService{

    private static final String TAG = "Part3IntentService";

    // Zero argument constructor required for instantiation on Intent
    public Part3IntentService() {
        // Name thread onHandleIntent will be executed on.
        super(Part3IntentService.class.getName());
        // Restarts IS, with duplicate Intent if killed before onHandleIntent completes.
        setIntentRedelivery(true);
    }

    public Part3IntentService(String name) {
        // Name thread onHandleIntent will be executed on.
        super(Part3IntentService.class.getName());
        // Restarts IS, with duplicate Intent if killed before onHandleIntent completes.
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "Intent received by Part3IntentService");

        // Send Intent to BroadcastReceiver
        Intent i = new Intent(Part3Fragment.DATA_RECEIVED);
        LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(i);

    }
}
