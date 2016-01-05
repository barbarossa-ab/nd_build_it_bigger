package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.barbarossa.jokedisplay.DisplayActivity;
import com.example.barbarossa.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by barbarossa on 05/01/16.
 */
public class JokeDownloader {
    private IListener listener;

    public JokeDownloader( IListener downloadListener ) {
        this.listener = downloadListener;
    }

    public void download() {
        new EndpointsAsyncTask().execute();
    }

    public interface IListener {
        void onDownloadCompleted(String result);
    }

    public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://baikal-1139.appspot.com/_ah/api/");

                // end options for devappserver
                myApiService = builder.build();
            }

            try {
                String data =  myApiService.getJoke().execute().getData();

                return data;

            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            listener.onDownloadCompleted(result);
        }
    }
}
