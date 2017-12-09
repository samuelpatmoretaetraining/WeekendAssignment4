package com.roundarch.codetest.part3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Samuel on 09/12/2017.
 */

public class Part3BroadcastReceiver extends BroadcastReceiver {
    public Part3BroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent received by Part3BroadcastReceiver.", Toast.LENGTH_SHORT).show();
    }
}
