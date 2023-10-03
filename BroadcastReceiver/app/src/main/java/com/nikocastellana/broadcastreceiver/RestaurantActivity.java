package com.nikocastellana.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class RestaurantActivity extends AppCompatActivity{

    public static String[] titleArray;
    public static String[] webArray;

    private final MyWebView2 myWebView = new MyWebView2();

    FragmentManager mFragmentManager;

    private FrameLayout titleFrameLayout, webFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TITLE = "Restaurants";

    private ListViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(TITLE);

        setContentView(R.layout.activity_restaurant);
        // Get the string arrays with the titles and urls
        titleArray = getResources().getStringArray(R.array.RestaurantTitles);
        webArray = getResources().getStringArray(R.array.RestaurantUrls);


        // Get references to the TitleFragment and to the QuotesFragment
        titleFrameLayout = (FrameLayout) findViewById(R.id.rest_fragment_container);
        webFrameLayout = (FrameLayout) findViewById(R.id.restUrl_fragment_container);


        // Get a reference to the SupportFragmentManager instead of original FragmentManager
        mFragmentManager = getSupportFragmentManager();

        // Start a new FragmentTransaction
        final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.replace(
                R.id.rest_fragment_container,
                new MyListView2());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

        // set up model observer to add and remove quotes fragment
        // Note: the fragment object never gets deleted, it just gets removed when the user
        // presses the "back" button
        model = new ViewModelProvider(this).get(ListViewModel.class) ;
        model.getSelectedItem().observe(this, item -> {
            if (!myWebView.isAdded()) {
                FragmentTransaction fragmentTransaction2 = mFragmentManager.beginTransaction() ;

                // add quote fragment to display
                fragmentTransaction2.replace(R.id.restUrl_fragment_container,
                        myWebView);

                // Add this FragmentTransaction to the backstack
                fragmentTransaction2.addToBackStack(null);

                // Commit the FragmentTransaction
                fragmentTransaction2.commit();

                // Force Android to execute the committed FragmentTransaction
                mFragmentManager.executePendingTransactions();
            }
        });
        setLayout() ;
    }

    private void setLayout() {

        // Determine whether the QuoteFragment has been added
        if (!myWebView.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            titleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            webFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {

            // Make the TitleLayout take 1/3 of the layout's width
            titleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            // Make the QuoteLayout take 2/3's of the layout's width
            webFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.option_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.attractions:
                Intent attraction = new Intent(RestaurantActivity.this, AttractionActivity.class);
                startActivity(attraction);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}