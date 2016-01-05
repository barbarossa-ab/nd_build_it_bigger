package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.barbarossa.jokedisplay.DisplayActivity;


public class MainActivity extends ActionBarActivity implements JokeDownloader.IListener{
    private ProgressBar spinner;
    private boolean jokeDownloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
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

    public void tellJoke(View view){
        if (!jokeDownloading) {
            jokeDownloading = true;
            JokeDownloader jd = new JokeDownloader(this);
            jd.download();

            spinner.setVisibility(View.VISIBLE);
        }
    }

    public void onDownloadCompleted(String result) {
        spinner.setVisibility(View.GONE);
        jokeDownloading = false;

        Intent jokeIntent = new Intent(this, DisplayActivity.class);
        jokeIntent.putExtra(DisplayActivity.JOKE_EXTRA, result);

        startActivity(jokeIntent);
    }
}
