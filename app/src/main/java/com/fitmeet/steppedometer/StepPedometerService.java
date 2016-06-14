package com.fitmeet.steppedometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

public class StepPedometerService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    private Sensor mStepDetectorSensor;

    private OnStepPedometer mOnStepPedometer;

    public StepPedometerService() {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        float[] values = sensorEvent.values;
        int value = -1;

        if(values.length > 0) {
            value = (int) values[0];
        }

        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            //TODO Step Counter Detected
            if(mOnStepPedometer != null ) {
                mOnStepPedometer.stepCounterSensed();
            }
        } else if(sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            //// TODO: 16/6/12 Step Detector Detected
            if(mOnStepPedometer != null) {
                mOnStepPedometer.stepDetectorSened();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public class StepPedometerBinder extends Binder {
        StepPedometerService getService() {
            return StepPedometerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new StepPedometerBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        initSensor();
        registeSensor();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisteSensor();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_FLAG_REDELIVERY;
    }

    private void initSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    private void registeSensor() {
        mSensorManager
                .registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager
                .registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void unregisteSensor() {
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

    private int mValue = 0;

    public interface OnStepPedometer {
        void stepCounterSensed();
        void stepDetectorSened();
    }

    public void setOnStepPedometer(OnStepPedometer onStepPedometer) {
        this.mOnStepPedometer = onStepPedometer;
    }

}
