package com.nikocastellana.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AttractionReceiver extends BroadcastReceiver {

    private static String ATTRACTION_INTENT = "com.nikocastellana.broadcastreceiver.attraction";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Attraction clicked!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context,AttractionActivity.class);
        context.startActivity(i);
    }


}