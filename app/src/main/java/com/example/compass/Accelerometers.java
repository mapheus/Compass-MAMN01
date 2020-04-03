package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Accelerometers extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private TextView x;
    private TextView y;
    private TextView z;
    private TextView fade;

    private int enterDuration;
    private int exitDuration;

    AnimationDrawable animationDrawable;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometers);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        enterDuration = 2500;
        exitDuration = 5000;

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);
        fade = (TextView) findViewById(R.id.fade);

        constraintLayout = (ConstraintLayout) findViewById(R.id.linear_layout);

        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        setFadeDuration(enterDuration, exitDuration);

        animationDrawable.start();
    }

    public void setFadeDuration(int enter, int exit) {
        animationDrawable.setEnterFadeDuration(enter);
        animationDrawable.setExitFadeDuration(exit);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x.setText("X: " + Float.toString(event.values[0]));
            y.setText("Y: " + Float.toString(event.values[1]));
            z.setText("Z: " + Float.toString(event.values[2]));

            if(event.values[0] > 3 ) {
                enterDuration = 300;
                exitDuration = 450;
                setFadeDuration(enterDuration, exitDuration);
                fade.setText("X > 3 so fast background transitions!!");
            } else {
                enterDuration = 1500;
                exitDuration = 2000;
                setFadeDuration(enterDuration, exitDuration);
                fade.setText("X < 3 so  background transitions!!");
            }
        }

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
