package com.example.rucha.loginact.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.rucha.loginact.R;
import com.example.rucha.loginact.Services.Activity.MusicServiceActivity;

/**
 * Created by rucha on 08-03-2018.
 */

public class MusicService extends IntentService{

    private static final int NOTIFICATION_ID = 1;
    private final String TAG = "MusicService";
    private MediaPlayer mediaPlayer;
    private int lclStartId;

    public MusicService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        Set up the Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.musicfile);

        if (null != mediaPlayer) {
            mediaPlayer.setLooping(false);

            // Stop Service when music has finished playing
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {

                    // stop Service if it was started with this ID
                    // Otherwise let other start commands proceed
                    stopSelf(lclStartId);
                }
            });
        }

        // Create a notification area notification so the user
        // can get back to the MusicServiceClient

        final Intent notificationIntent = new Intent(getApplicationContext(), MusicServiceActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        final Notification notification = new Notification.Builder(getApplicationContext())
                                             .setSmallIcon(android.R.drawable.ic_media_play)
                                             .setOngoing(true).setContentTitle("Music Playing")
                                             .setContentText("Click to access Music Player")
                                             .setContentIntent(pendingIntent).build();

        // Put this Service in a foreground state, so it won't
        // readily be killed by the system
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if(null != mediaPlayer){

//            Id for Start command
            lclStartId = startId;

            if (mediaPlayer.isPlaying()){

//            Rewind to beginning of song
                mediaPlayer.seekTo(0);
            }
            else {
//            Start playing song
                mediaPlayer.start();
            }
        }
        // Don't automatically restart this Service if it is killed
        return START_NOT_STICKY;
    }

//    Can't bind to this service

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
