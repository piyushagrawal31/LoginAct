package com.example.rucha.loginact.Services.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.rucha.loginact.R;
import com.example.rucha.loginact.Services.MusicService;

public class MusicServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_service);

        // Intent used for starting the MusicService
        final Intent intentMusicService = new Intent(getApplicationContext(), MusicService.class);

        final Button lclbtnstart = (Button) findViewById(R.id.btnStart);
        lclbtnstart.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                // Start the MusicService using the Intent
                startService(intentMusicService);
            }
        });

        final Button lclbtnStop = (Button) findViewById(R.id.btnStop);
        lclbtnStop.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                // Stop the MusicService using the Intent
                stopService(intentMusicService);
            }
        });
    }
}
