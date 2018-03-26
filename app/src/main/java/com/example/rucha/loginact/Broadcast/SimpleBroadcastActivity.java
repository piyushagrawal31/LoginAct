package com.example.rucha.loginact.Broadcast;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.rucha.loginact.R;

public class SimpleBroadcastActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT =
            "course.examples.BroadcastReceiver.show_toast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_broadcast);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBroadcast(new Intent(CUSTOM_INTENT), Manifest.permission.VIBRATE);
            }
        });


    }
}
