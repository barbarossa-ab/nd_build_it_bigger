package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.barbarossa.jokedisplay.DisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends ActionBarActivity implements JokeDownloader.IListener{
    InterstitialAd mInterstitialAd;

    private boolean adDisplayed;
    private boolean jokeDownloading;
    private String mJoke;

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        adDisplayed = false;
        jokeDownloading = false;

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                adDisplayed = false;

                if(!jokeDownloading){
                    displayJoke();
                }
            }
        });

        requestNewInterstitial();
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
        if (!jokeDownloading) {
            jokeDownloading = true;
            JokeDownloader jd = new JokeDownloader(this);
            jd.download();
            spinner.setVisibility(View.VISIBLE);
        }

        if (mInterstitialAd.isLoaded()) {
            adDisplayed = true;
            mInterstitialAd.show();
        }
    }

    public void onDownloadCompleted(String result) {
        mJoke = result;
        jokeDownloading = false;
        spinner.setVisibility(View.GONE);

        if(!adDisplayed) {
            displayJoke();
        }
    }

    private void displayJoke() {
        Intent jokeIntent = new Intent(this, DisplayActivity.class);
        jokeIntent.putExtra(DisplayActivity.JOKE_EXTRA, mJoke);

        startActivity(jokeIntent);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("84F675E42FCB7E70B88F66E647456EC5")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
