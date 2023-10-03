package com.nikocastellana.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button attractions;
    private Button restaurants;

    private static String ATTRACTION_INTENT = "com.nikocastellana.broadcastreceiver.attraction";
    private static String RESTAURANT_INTENT = "com.nikocastellana.broadcastreceiver.restaurant";
    private static String APP_TITLE = "Cool Chicago Locations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(APP_TITLE);

        attractions = (Button) findViewById(R.id.attraction) ;

        attractions.setOnClickListener((v)->{
            Intent attractionIntent = new Intent();
            attractionIntent.setAction(ATTRACTION_INTENT);
            sendBroadcast(attractionIntent);
        });

        restaurants = (Button) findViewById(R.id.restaurant);

        restaurants.setOnClickListener((v)->{
            Intent attractionIntent = new Intent();
            attractionIntent.setAction(RESTAURANT_INTENT);
            sendBroadcast(attractionIntent);
        });
    }
}