package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyCustomService extends Service {

    private MediaPlayer mediaPlayer; // to play music / videos from different sources

    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer = MediaPlayer.create(
                this,
                Settings.System.DEFAULT_RINGTONE_URI
        );


        //loops music
        mediaPlayer.setLooping(true);

        mediaPlayer.start();


        //return Start_not sticky services should not be restarted unless the client explicitly started it
        return START_STICKY; // this indicates that the service should be restarted if it is killed by the system
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }


    //Binds the component to the service
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
