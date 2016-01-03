package com.example.barbarossa.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "JOKE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(JOKE_EXTRA)) {
            if(intent.getStringExtra(JOKE_EXTRA) != null) {
                ((TextView) findViewById(R.id.jokeText)).setText(intent.getStringExtra(JOKE_EXTRA));
            }
        }
    }
}
