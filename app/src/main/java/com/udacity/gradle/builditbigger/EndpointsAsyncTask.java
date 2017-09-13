package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import com.example.androidlib.AndroidLibActivity;
import com.example.androidlib.Keys;
import com.example.michael.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    // Testing
    public CountingIdlingResource mIdlingCounter = new CountingIdlingResource("DATA_LOADER");
    public String mJoke = null;

    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();

    private static MyApi myApiService = null;
    private Context context;

    public static boolean isRunning = false;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

        isRunning = true;

        // Increment here; decrement in onPostExecute
        mIdlingCounter.increment();

        Log.v(TAG,"doInBackground");

        if(myApiService == null) {  // Only do this once


//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });


            String projectId = "udacity-178800";
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://"+projectId+".appspot.com/_ah/api/");


            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {

        Intent intent = new Intent(context,AndroidLibActivity.class).putExtra(Keys.JOKE_STRING,joke);
        context.startActivity(intent);

        mJoke = joke;
        mIdlingCounter.decrement();

        // Create a new task; a task cannot be run more than once.
        MainActivity.mAsyncTask = new EndpointsAsyncTask();

        MainActivity.mSpinner.setVisibility(View.GONE);
        isRunning = false;
    }
}