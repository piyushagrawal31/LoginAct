package com.example.rucha.loginact.Broadcast.Receiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.rucha.loginact.R;

/**
 * Created by rucha on 16-03-2018.
 */

public class ReceiverActivity extends AppCompatActivity {

//    Dynamic Registration starts
    private static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.show_toast";
    private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);

    private final BCastWifiReceiver receiver = new BCastWifiReceiver();
    private LocalBroadcastManager mBroadcastMgr;

//    Dynamic Registration nds

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Dynamic registration Starts
        mBroadcastMgr = LocalBroadcastManager.getInstance(getApplicationContext());

        mBroadcastMgr.registerReceiver(receiver, intentFilter);
    }
}


