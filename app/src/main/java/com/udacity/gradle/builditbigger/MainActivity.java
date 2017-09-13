package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    // Make task static so it can be re-initialized in onResponse
    public static EndpointsAsyncTask mAsyncTask = new EndpointsAsyncTask();

    // Make static so visibility can be toggled from onResponse
    public static ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar) findViewById(R.id.spinner);

        // Progress bar should be visible only if the async task is running
        if(EndpointsAsyncTask.isRunning){
            mSpinner.setVisibility(View.VISIBLE);
        } else {
            mSpinner.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        mAsyncTask.execute(new Pair<Context, String>(this, "Manfred"));
        mSpinner.setVisibility(View.VISIBLE);
    }
}
