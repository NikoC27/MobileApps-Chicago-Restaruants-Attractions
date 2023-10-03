package com.nikocastellana.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RestaurantReceiver extends BroadcastReceiver {

    private static final String RESTAURANT_INTENT = "com.nikocastellana.broadcastreceiver.restaurant";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Restaurant clicked!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, RestaurantActivity.class);
        context.startActivity(i);
    }
}