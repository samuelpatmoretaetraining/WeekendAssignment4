package com.roundarch.codetest.part3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Samuel on 09/12/2017.
 */

public class Part3BroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "Part3BroadcastReceiver";
    private Part3Fragment target;

    public Part3BroadcastReceiver() {
        Log.i(TAG, "Part3BroadcastReceiver created");
    }

    public void setTarget(Part3Fragment target) {
        this.target = target;
        Log.i(TAG, "Target set to "+this.toString());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent received by Part3BroadcastReceiver.");
        Toast.makeText(context, "Intent received by Part3BroadcastReceiver.", Toast.LENGTH_SHORT).show();
        target.setDataReceived();
    }
}
