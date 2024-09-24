package com.example.musicplayer;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent serviceintent = new Intent(getApplicationContext(), MyCustomService.class);
            startService(serviceintent);
        });

        Button btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(v -> {
            Intent serviceintent = new Intent(getApplicationContext(), MyCustomService.class);
            stopService(serviceintent);
        });




        //API level 26 - or higher - can no longer recieve broadcasts
        // have to be registered in the mainactivity but if level 26 lower
        //need to declare in manifest

        IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        AirplaneModeReceiver airplaneModeReceiver = new AirplaneModeReceiver();
        registerReceiver(airplaneModeReceiver, intentFilter);

    }


    public void AndroidComponents(){
        //Activity - represents a single screen with a user interface

        //Services - runs in the background to perform long running operations
        //music/ servers/ APIS

        //Types of Services
        /*
        1 Foreground - music player that plays music in the background
                       -allows the user to interact with the player in the notif
        2 Background - tasks that can run independently
                     - background services / servers
        3 Bound services - allow components to communicate with each other

        Unbounded Service - service is started (startService()) -> onCreate() -> onStartCommand()
        -> Service is running --> Service is stopped by itself or client - > the service is stopped no callback
        -> onDestroy() - > service is shut down

            a. can be created without being binded by components


        Bounded Service service is started (startService()) -> onCreate() -> onBind() ->
        -> Clients interacts with the service--> All clients unbind by calling unbindService() - > t
        -> unBind() - > onDestroy() - > service is shut down
               |
            can be bind again onRebind()

            a. will be created using the components that is bound to it



         */







        //broadcast receivers - listen for system wide announcements
        //battery level, network notification airplane mode etc.


        //content providers - manages access to a structured set of data
        //,media store; content://media / external/ images / media
        //calendar provider- provides acces to device's calendar
        //Call log provider - Provides access to the call history on the device.
        //and many more


    }
}