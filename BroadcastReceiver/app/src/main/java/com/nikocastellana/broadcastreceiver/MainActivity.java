package com.nikocastellana.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity {

    private AttractionReceiver attractionRec;
    private RestaurantReceiver restaurantRec;
    private IntentFilter attractionFilter;
    private IntentFilter restaurantFilter;

    private static final String ATTRACTION_INTENT = "com.nikocastellana.broadcastreceiver.attraction";
    private static final String RESTAURANT_INTENT = "com.nikocastellana.broadcastreceiver.restaurant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attractionRec = new AttractionReceiver() ;
        restaurantRec = new RestaurantReceiver() ;

        attractionFilter = new IntentFilter(ATTRACTION_INTENT) ;
        restaurantFilter = new IntentFilter(RESTAURANT_INTENT) ;


        registerReceiver(attractionRec, attractionFilter) ;
        registerReceiver(restaurantRec, restaurantFilter) ;

        Intent i = new Intent(MainActivity.this,AttractionActivity.class);
        startActivity(i);
    }

}