package ru.narod.nod.fifteen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class FieldActivity extends Activity implements View.OnClickListener {
    //View's declaration
    private final String TAG = "FieldActivity";
    private Engine engine;
    private HighScoresActivity highScoresActivity;
    private ImageView[][] arrBut;
    private Boolean contin, finished;
    private SharedPreferences prefs;
    private SharedPreferences.Editor edit;
    private ViewGroup.LayoutParams params;

    private TimeCounter timeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        prefs = getSharedPreferences("storeField", Context.MODE_PRIVATE);
        edit = prefs.edit();

        finished = false; //for checking if the game is done (finished)
        //Get the boolean data from Intent about was pressed the "continue" button or not
        Intent intent = getIntent();
        contin = intent.getBooleanExtra("contin", false);

        // Initialize the Mobile Ads SDK.
        //Chronometer chronometer;
        AdView adView = findViewById(R.id.adViewField);
        Ads ads = new Ads(this);
        adView.loadAd(ads.getSpecialAdRequest());

        //region Declaration of views
        arrBut = new ImageView[4][4];

        arrBut[0][0] = findViewById(R.id.btn1);
        arrBut[0][1] = findViewById(R.id.btn2);
        arrBut[0][2] = findViewById(R.id.btn3);
        arrBut[0][3] = findViewById(R.id.btn4);
        arrBut[1][0] = findViewById(R.id.btn5);
        arrBut[1][1] = findViewById(R.id.btn6);
        arrBut[1][2] = findViewById(R.id.btn7);
        arrBut[1][3] = findViewById(R.id.btn8);
        arrBut[2][0] = findViewById(R.id.btn9);
        arrBut[2][1] = findViewById(R.id.btn10);
        arrBut[2][2] = findViewById(R.id.btn11);
        arrBut[2][3] = findViewById(R.id.btn12);
        arrBut[3][0] = findViewById(R.id.btn13);
        arrBut[3][1] = findViewById(R.id.btn14);
        arrBut[3][2] = findViewById(R.id.btn15);
        arrBut[3][3] = findViewById(R.id.btn16);
        //endregion

        //region Assign setOnClickListener for buttons
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrBut[i][j].setOnClickListener(this);
            }
        }
        //endregion

        engine = new Engine();
        highScoresActivity = new HighScoresActivity();

        if (null == timeCounter)
            timeCounter = new TimeCounter(FieldActivity.this);

        //if was pressed "continue" then get the previous Field consistence
        if (contin) {
            getPreviousConsistence();
        }

        timeCounter.startTime = SystemClock.uptimeMillis() - timeCounter.startTime;
        if (contin)
            timeCounter.loadPref();
        timeCounter.customHandler.postDelayed(timeCounter.updateTimerThread, 0);

        //sets the squared size for cells
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mainTableLayoutWeight;
        if (dm.widthPixels < dm.heightPixels) //needs because of the device orientation
            mainTableLayoutWeight = dm.widthPixels - 24;
        else mainTableLayoutWeight = dm.heightPixels - 24;
        params = arrBut[0][0].getLayoutParams();
        params.height = params.width = mainTableLayoutWeight / 4;
        arrayToField();
    }

    private void putCurrentConsistence() {
        Log.d(TAG, "FieldActivity: putCurrentConsistence()");
        if (!finished) {
            for (int i = 0; i < arrBut.length; i++) {
                for (int j = 0; j < arrBut[0].length; j++) {
                    edit.putString("array_" + i + j, engine.getGameField()[i][j]);
                }
            }

            timeCounter.savePref(); //save current timer in the properties through the timer object's method

            edit.putBoolean("deactivate_contin", false);
            edit.apply();
        } else {
            edit.putBoolean("deactivate_contin", true);
            edit.apply();
        }
    }

    private void getPreviousConsistence() {
        Log.d(TAG, "FieldActivity: getPreviousConsistence()");
        // load tasks from preference
        for (int i = 0; i < arrBut.length; i++) {
            for (int j = 0; j < arrBut[0].length; j++) {
                engine.getGameField()[i][j] = prefs.getString("array_" + i + j, null);
            }
        }
        timeCounter.loadPref(); //load the saved timer from the properties through the timer object's method
    }

    public void arrayToField() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (engine.getGameField()[i][j].equals("1"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b01);
                else if (engine.getGameField()[i][j].equals("2"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b02);
                else if (engine.getGameField()[i][j].equals("3"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b03);
                else if (engine.getGameField()[i][j].equals("4"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b04);
                else if (engine.getGameField()[i][j].equals("5"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b05);
                else if (engine.getGameField()[i][j].equals("6"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b06);
                else if (engine.getGameField()[i][j].equals("7"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b07);
                else if (engine.getGameField()[i][j].equals("8"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b08);
                else if (engine.getGameField()[i][j].equals("9"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b09);
                else if (engine.getGameField()[i][j].equals("10"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b10);
                else if (engine.getGameField()[i][j].equals("11"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b11);
                else if (engine.getGameField()[i][j].equals("12"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b12);
                else if (engine.getGameField()[i][j].equals("13"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b13);
                else if (engine.getGameField()[i][j].equals("14"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b14);
                else if (engine.getGameField()[i][j].equals("15"))
                    arrBut[i][j].setBackgroundResource(R.drawable.b15);
                else if (engine.getGameField()[i][j].equals(""))
                    arrBut[i][j].setBackgroundResource(R.drawable.empty);

                //sets the squared size for cells
                arrBut[i][j].setLayoutParams(params);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        //region Switch button's construction
        switch (v.getId()) {
            case R.id.btn1 -> actionOnTheField(0, 0);
            case R.id.btn2 -> actionOnTheField(0, 1);
            case R.id.btn3 -> actionOnTheField(0, 2);
            case R.id.btn4 -> actionOnTheField(0, 3);
            case R.id.btn5 -> actionOnTheField(1, 0);
            case R.id.btn6 -> actionOnTheField(1, 1);
            case R.id.btn7 -> actionOnTheField(1, 2);
            case R.id.btn8 -> actionOnTheField(1, 3);
            case R.id.btn9 -> actionOnTheField(2, 0);
            case R.id.btn10 -> actionOnTheField(2, 1);
            case R.id.btn11 -> actionOnTheField(2, 2);
            case R.id.btn12 -> actionOnTheField(2, 3);
            case R.id.btn13 -> actionOnTheField(3, 0);
            case R.id.btn14 -> actionOnTheField(3, 1);
            case R.id.btn15 -> actionOnTheField(3, 2);
            case R.id.btn16 -> actionOnTheField(3, 3);
        }
        //endregion
    }

    public void actionOnTheField(int line, int column) {
        String tmp = engine.getGameField()[line][column];
        try {
            if (Objects.equals(engine.getGameField()[line][column + 1], "")) {
                engine.setGameField(engine.getGameField()[line][column + 1], line, column);
                engine.setGameField(tmp, line, column + 1);
            }
        } catch (Throwable e) {
            Log.e(TAG, "ERROR: " + e);
        }
        try {
            if (Objects.equals(engine.getGameField()[line][column - 1], "")) {
                engine.setGameField(engine.getGameField()[line][column - 1], line, column);
                engine.setGameField(tmp, line, column - 1);
            }
        } catch (Throwable e) {
            Log.e(TAG, "ERROR: " + e);
        }
        try {
            if (Objects.equals(engine.getGameField()[line + 1][column], "")) {
                engine.setGameField(engine.getGameField()[line + 1][column], line, column);
                engine.setGameField(tmp, line + 1, column);
            }
        } catch (Throwable e) {
            Log.e(TAG, "ERROR: " + e);
        }
        try {
            if (Objects.equals(engine.getGameField()[line - 1][column], "")) {
                engine.setGameField(engine.getGameField()[line - 1][column], line, column);
                engine.setGameField(tmp, line - 1, column);
            }
        } catch (Throwable e) {
            Log.e(TAG, "ERROR: " + e);
        }
        arrayToField();
        checkIsFinished();
    }

    private void checkIsFinished() {
        int checker = 0;
        int tmp; //here we put number of the current button to check whether it equals to the sequence
        finished = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (engine.getGameField()[i][j].equals(""))
                    tmp = 0; //if button is empty (without a num), put 0
                else tmp = Integer.parseInt(engine.getGameField()[i][j]);
                if (i == 3 && j == 3) break; //skip the last button #16
                if (tmp - checker++ != 1) { //if next button minus preview NOT equals 1 then it is NOT the sequence
                    finished = false;
                    break;
                } else finished = true;
            }
        }
        if (finished) {
            String scores = timeCounter.timerValue.getText().toString();
            Toast.makeText(FieldActivity.this, "Congrats, you won!", Toast.LENGTH_SHORT).show();

            //***High score creating

            //Load the saved high scores to compare to current ones
            String savedText = prefs.getString(highScoresActivity.getAPP_PREFERENCES(), "");

            if (savedText.compareTo(scores) > 0) {
                //If the current high scores better then the saved before ones than save the current scores
                SimpleDateFormat LocaleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                LocaleDateFormat.setTimeZone(TimeZone.getDefault());
                edit.putString(highScoresActivity.getAPP_PREFERENCES(), scores);
                edit.putString(highScoresActivity.getAPP_PREFERENCES_DATE(), LocaleDateFormat.format(new Date()));
                edit.apply();
            }
            Intent intent = new Intent(this, HighScoresActivity.class);
            startActivity(intent);

            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "FieldActivity: onResume()");
        Intent intent = getIntent();
        contin = intent.getBooleanExtra("contin", false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "FieldActivity: onPause()");
        putCurrentConsistence();

        timeCounter.pauseCounter();
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
        getPreviousConsistence();
        arrayToField();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        putCurrentConsistence();
    }
}
