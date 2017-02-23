package ru.narod.nod.fifteen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by otc on 14.11.2016.
 *      1. You need put this in the 'onCreate' of the calling class
 *      timeCounter.startTime = SystemClock.uptimeMillis();
 *      timeCounter.customHandler.postDelayed(timeCounter.updateTimerThread, 0);
 *      2. Use the protected void onPause() method to call:
 *      timeCounter.pauseCounter();
 *
 */

class TimeCounter {

    public boolean continTheRunnable;

    public TextView timerValue;

    public long startTime;

    public Handler customHandler;

    long timeInMilliseconds;
    long timeSwapBuff;
    long updatedTime;

    SharedPreferences prefs;
    SharedPreferences.Editor edit;


    public TimeCounter(Context context, Activity activity) {

        timerValue = (TextView) activity.findViewById(R.id.timerValue);
        continTheRunnable = true;
        startTime = 0L;

        customHandler = new Handler();

        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;

        prefs = activity.getSharedPreferences("storeField", Context.MODE_PRIVATE);
        edit = prefs.edit();

    }

    public void reset(Activity activity) {

        continTheRunnable = false;
        startTime = 0L;

        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
    }

    public void savePref() {
        //if (prefs.getLong("timeCounter", 0) < updatedTime)
            edit.putLong("timeCounter", updatedTime);
//        else {
//            long diff = SystemClock.uptimeMillis() - updatedTime;
//            edit.putLong("timeCounter", SystemClock.uptimeMillis() - diff);
//        }

        edit.apply();
}

    public void loadPref() {
        startTime = SystemClock.uptimeMillis() - prefs.getLong("timeCounter", 0);
        //startTime = prefs.getLong("timeCounter", 0);
    }

    public void pauseCounter() {
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    public Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            //int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) /*+ ":"
                            + String.format("%03d", milliseconds)*/);
            customHandler.postDelayed(this, 1);

        }
    };

}
