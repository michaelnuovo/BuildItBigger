package com.example.androidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidLibActivity extends AppCompatActivity {

    public static InterstitialAd mInterstitialAd;

    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_lib);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mJoke = getIntent().getStringExtra(Keys.JOKE_STRING);


        // Don't show the add again on screen orientation changes
        if(null == savedInstanceState){
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());

            // The listener will show the add when the add is loaded, then display the mJoke
            mInterstitialAd.setAdListener(new MyAdListener());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyAdListener extends AdListener {

        @Override
        public void onAdLoaded() {
            // Code to be executed when an ad finishes loading.
            Log.i("Ads", "onAdLoaded");
            mInterstitialAd.show();
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            // Code to be executed when an ad request fails.
            Log.i("Ads", "onAdFailedToLoad");
        }

        @Override
        public void onAdOpened() {
            // Code to be executed when the ad is displayed.
            Log.i("Ads", "onAdOpened");
        }

        @Override
        public void onAdLeftApplication() {
            // Code to be executed when the user has left the app.
            Log.i("Ads", "onAdLeftApplication");
        }

        @Override
        public void onAdClosed() {
            // Code to be executed when when the interstitial ad is closed.
            Log.i("Ads", "onAdClosed");


            Toast.makeText(AndroidLibActivity.this, mJoke, Toast.LENGTH_LONG).show();
        }
    }
}


