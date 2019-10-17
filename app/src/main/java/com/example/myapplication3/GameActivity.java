package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mediaPlayer = MediaPlayer.create(this, R.raw.scream);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);
//        mediaPlayer.prepareAsync();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {


            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 50) {
                lastUpdate = curTime;
                double rootSquare = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
                if (rootSquare < 2.0) {

                    if (!mediaPlayer.isPlaying()) {
                        Log.i("tag", "playing sound...");
                        this.mediaPlayer.start();
                    }

                }
                else {
                    if (mediaPlayer.isPlaying()) {
                        Log.i("tag", "stopping sound...");
                        this.mediaPlayer.pause();
                        this.mediaPlayer.seekTo(0);
                    }
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
