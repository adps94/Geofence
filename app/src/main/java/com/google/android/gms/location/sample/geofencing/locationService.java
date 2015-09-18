package com.google.android.gms.location.sample.geofencing;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class locationService extends Service {

    private double lat;
    private double lng;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        final Context self= this;
        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        Log.e("test","");
        scheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {

                        lat = MainActivity.gps.getLatitude();
                        lng = MainActivity.gps.getLongitude();
                        Log.e("" + lat, "" + lng);
                      //   new PostLocationAlert().execute(lat + "," + lng);
                    }
                }, 0,22, TimeUnit.SECONDS);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        Log.e("SERVICE", "service started");
        return START_STICKY;
    }




}


