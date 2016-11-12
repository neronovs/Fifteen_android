package ru.narod.nod.fifteen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FieldActivity extends AppCompatActivity implements View.OnClickListener {
    //View's declaration
    final String TAG = "FieldActivity";
    Engine engine;
    HighScoresActivity highScoresActivity;
    ImageView arrBut[][];
    Chronometer chronometer;
    AdView adView;
    Boolean contin, finished, orientationChanged;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//deny to change orientation of a screen

        prefs = getSharedPreferences("storeField", Context.MODE_PRIVATE);
        edit = prefs.edit();

        finished = false; //for checking if the game is done (finished)
        //Get the boolean data from Intent about was pressed the "continue" button or not
        Intent intent = getIntent();
        contin = intent.getBooleanExtra("contin", false);

        //the var for the saving of the current field when the orientation is changing
        //if (null == orientationChanged) orientationChanged = false;

        // Initialize the Mobile Ads SDK.
        adView = (AdView) findViewById(R.id.adViewField);
        Ads ads = new Ads(this, FieldActivity.this);
        adView.loadAd(ads.getSpecialAdRequest());

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.start();

        //region Declaration of views
        arrBut = new ImageView[4][4];

        //Log.d(TAG, "FieldActivity: onCreate()");

        arrBut[0][0] = (ImageView) findViewById(R.id.btn1);
        arrBut[0][1] = (ImageView) findViewById(R.id.btn2);
        arrBut[0][2] = (ImageView) findViewById(R.id.btn3);
        arrBut[0][3] = (ImageView) findViewById(R.id.btn4);
        arrBut[1][0] = (ImageView) findViewById(R.id.btn5);
        arrBut[1][1] = (ImageView) findViewById(R.id.btn6);
        arrBut[1][2] = (ImageView) findViewById(R.id.btn7);
        arrBut[1][3] = (ImageView) findViewById(R.id.btn8);
        arrBut[2][0] = (ImageView) findViewById(R.id.btn9);
        arrBut[2][1] = (ImageView) findViewById(R.id.btn10);
        arrBut[2][2] = (ImageView) findViewById(R.id.btn11);
        arrBut[2][3] = (ImageView) findViewById(R.id.btn12);
        arrBut[3][0] = (ImageView) findViewById(R.id.btn13);
        arrBut[3][1] = (ImageView) findViewById(R.id.btn14);
        arrBut[3][2] = (ImageView) findViewById(R.id.btn15);
        arrBut[3][3] = (ImageView) findViewById(R.id.btn16);
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

        //if was pressed "continue" then get the previous Field consistence
        if (contin) {
            getPreviousConsistence();
            arrayToField();
        } else {
            arrayToField();
        }
    }

    private void putCurrentConsistence() {
        Log.d(TAG, "FieldActivity: putCurrentConsistence()");
        if (!finished) {
            //String array[]
            //SharedPreferences.Editor edit = prefs.edit();
            //edit.putInt("array_size_1", arrBut.length);
            //edit.putInt("array_size_2", arrBut[0].length);
            for (int i = 0; i < arrBut.length; i++) {
                for (int j = 0; j < arrBut[0].length; j++) {
                    edit.putString("array_" + i + j, engine.getGameField()[i][j]);
                }
            }

            //store current chronometer time
            edit.putString("chronometer", String.valueOf(chronometer.getBase()));
            chronometer.stop();
            edit.putBoolean("deactivate_contin", false);
            edit.apply();
        } else {
            //SharedPreferences.Editor edit = prefs.edit();
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
        /*String time = prefs.getString("chronometer", "");
        int temp = Integer.valueOf(time.substring(0, 1));
        temp *= 60;
        temp += Integer.valueOf(time.substring(3));*/
        Long timeWhenStopped = Long.parseLong(prefs.getString("chronometer", ""));
        chronometer.setBase(timeWhenStopped);
        chronometer.start();
    }

    public void arrayToField() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (engine.getGameField()[i][j].equals("1")) arrBut[i][j].setBackgroundResource(R.drawable.b01);
                else if (engine.getGameField()[i][j].equals("2")) arrBut[i][j].setBackgroundResource(R.drawable.b02);
                else if (engine.getGameField()[i][j].equals("3")) arrBut[i][j].setBackgroundResource(R.drawable.b03);
                else if (engine.getGameField()[i][j].equals("4")) arrBut[i][j].setBackgroundResource(R.drawable.b04);
                else if (engine.getGameField()[i][j].equals("5")) arrBut[i][j].setBackgroundResource(R.drawable.b05);
                else if (engine.getGameField()[i][j].equals("6")) arrBut[i][j].setBackgroundResource(R.drawable.b06);
                else if (engine.getGameField()[i][j].equals("7")) arrBut[i][j].setBackgroundResource(R.drawable.b07);
                else if (engine.getGameField()[i][j].equals("8")) arrBut[i][j].setBackgroundResource(R.drawable.b08);
                else if (engine.getGameField()[i][j].equals("9")) arrBut[i][j].setBackgroundResource(R.drawable.b09);
                else if (engine.getGameField()[i][j].equals("10")) arrBut[i][j].setBackgroundResource(R.drawable.b10);
                else if (engine.getGameField()[i][j].equals("11")) arrBut[i][j].setBackgroundResource(R.drawable.b11);
                else if (engine.getGameField()[i][j].equals("12")) arrBut[i][j].setBackgroundResource(R.drawable.b12);
                else if (engine.getGameField()[i][j].equals("13")) arrBut[i][j].setBackgroundResource(R.drawable.b13);
                else if (engine.getGameField()[i][j].equals("14")) arrBut[i][j].setBackgroundResource(R.drawable.b14);
                else if (engine.getGameField()[i][j].equals("15")) arrBut[i][j].setBackgroundResource(R.drawable.b15);
                else if (engine.getGameField()[i][j].equals("")) arrBut[i][j].setBackgroundResource(R.drawable.empty);
            }
        }
    }

    @Override
    public void onClick(View v) {
        //region Switch button's construction
        switch (v.getId()) {
            case R.id.btn1:
                actionOnTheField(arrBut[0][0], 0, 0);
                break;
            case R.id.btn2:
                actionOnTheField(arrBut[0][1], 0, 1);
                break;
            case R.id.btn3:
                actionOnTheField(arrBut[0][2], 0, 2);
                break;
            case R.id.btn4:
                actionOnTheField(arrBut[0][3], 0, 3);
                break;
            case R.id.btn5:
                actionOnTheField(arrBut[1][0], 1, 0);
                break;
            case R.id.btn6:
                actionOnTheField(arrBut[1][1], 1, 1);
                break;
            case R.id.btn7:
                actionOnTheField(arrBut[1][2], 1, 2);
                break;
            case R.id.btn8:
                actionOnTheField(arrBut[1][3], 1, 3);
                break;
            case R.id.btn9:
                actionOnTheField(arrBut[2][0], 2, 0);
                break;
            case R.id.btn10:
                actionOnTheField(arrBut[2][1], 2, 1);
                break;
            case R.id.btn11:
                actionOnTheField(arrBut[2][2], 2, 2);
                break;
            case R.id.btn12:
                actionOnTheField(arrBut[2][3], 2, 3);
                break;
            case R.id.btn13:
                actionOnTheField(arrBut[3][0], 3, 0);
                break;
            case R.id.btn14:
                actionOnTheField(arrBut[3][1], 3, 1);
                break;
            case R.id.btn15:
                actionOnTheField(arrBut[3][2], 3, 2);
                break;
            case R.id.btn16:
                actionOnTheField(arrBut[3][3], 3, 3);
                break;
        }
        //endregion
    }

    public void actionOnTheField(ImageView btn, int line, int column) {
        //Log.d(TAG, "btn " + btn + " was pressed");
        String tmp = engine.getGameField()[line][column];;
        try {
            if (engine.getGameField()[line][column + 1] == "") {
                engine.setGameField(engine.getGameField()[line][column + 1], line, column);
                engine.setGameField(tmp, line, column + 1);
            }
        } catch (Throwable e) {}
        try {
            if (engine.getGameField()[line][column - 1] == "") {
                engine.setGameField(engine.getGameField()[line][column - 1], line, column);
                engine.setGameField(tmp, line, column - 1);
            }
        } catch (Throwable e) {}
        try {
            if (engine.getGameField()[line + 1][column] == "") {
                engine.setGameField(engine.getGameField()[line + 1][column], line, column);
                engine.setGameField(tmp, line + 1, column);
            }
        } catch (Throwable e) {}
        try {
            if (engine.getGameField()[line - 1][column] == "") {
                engine.setGameField(engine.getGameField()[line - 1][column], line, column);
                engine.setGameField(tmp, line - 1, column);
            }
        } catch (Throwable e) {}
        arrayToField();
        checkIsFinished();
    }

    private void checkIsFinished() {
        int checker = 0;
        int tmp = -1; //here we put number of the current button to check whether it equals to the sequence
        finished = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (engine.getGameField()[i][j].equals("")) tmp = 0; //if button is empty (without a num), put 0
                else tmp = Integer.valueOf(engine.getGameField()[i][j]);
                //System.out.println(Integer.valueOf(engine.getGameField()[i][j]));
                if (i == 3 && j == 3) break; //skip the last button #16
                if (tmp - checker++ != 1) { //if next button minus preview NOT equals 1 then it is NOT the sequence
                    finished = false;
                    break;
                }
                else finished = true;
            }
        }
        if (finished) {
            String scores = chronometer.getText().toString();
            chronometer.stop();
            Toast.makeText(FieldActivity.this, "Congrats, you won!", Toast.LENGTH_SHORT).show();

            //***High score creating

            //Load the saved high scores to compare to current ones
            //prefs = getSharedPreferences("storeField", MODE_PRIVATE);
            String savedText = prefs.getString(highScoresActivity.getAPP_PREFERENCES(), "");

            if (savedText.compareTo(scores) > 0) {
                //If the current high scores better then the saved before ones than save the current scores
                SimpleDateFormat LocaleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                LocaleDateFormat.setTimeZone(TimeZone.getDefault());
                //prefs = getSharedPreferences(highScoresActivity.getAPP_PREFERENCES(), MODE_PRIVATE);
                //SharedPreferences.Editor edit = prefs.edit();
                edit.putString(highScoresActivity.getAPP_PREFERENCES(), scores);
                edit.putString(highScoresActivity.getAPP_PREFERENCES_DATE(), String.valueOf(LocaleDateFormat.format(new Date())));
                edit.apply();
            }
            Intent intent = new Intent(this, HighScoresActivity.class);
            startActivity(intent);

            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "FieldActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "FieldActivity: onResume()");
        Intent intent = getIntent();
        contin = intent.getBooleanExtra("contin", false);
        //if (contin == false) contin = true;
        /*if (contin) {
            getPreviousConsistence();
            arrayToField();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "FieldActivity: onPause()");
        putCurrentConsistence();
        orientationChanged = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "FieldActivity: onStop()");
        //putCurrentConsistence();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "FieldActivity: onDestroy()");
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
