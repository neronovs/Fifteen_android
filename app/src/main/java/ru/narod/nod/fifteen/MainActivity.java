package ru.narod.nod.fifteen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    Button btnContinue, btnStart, btnHS, btnRules, btnExit;
    final String TAG = "States";
    AdView adView;
    Boolean contin;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    //***Идентификатор приложения: ca-app-pub-8956716360419559~6814211023
    //***Идентификатор рекламного блока: ca-app-pub-8956716360419559/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//deny to change orientation of a screen
        //Log.d(TAG, "MainActivity: onCreate()");
        contin = false;

        // Initialize the Mobile Ads SDK.
        adView = (AdView) findViewById(R.id.adViewMain);
//        MobileAds.initialize(this, "ca-app-pub-8956716360419559~4528395824");
//        AdRequest adRequest1 = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                // Check the LogCat to get your test device ID
//                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
//                .build();
//        adView.loadAd(adRequest1);

        Ads ads = new Ads(this, MainActivity.this);
        adView.loadAd(ads.getSpecialAdRequest());

        //region Button implementation
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnHS = (Button) findViewById(R.id.btnHS);
        btnRules = (Button) findViewById(R.id.btnRules);
        btnExit = (Button) findViewById(R.id.btnExit);

        //make the "Continue" button active or deactive
        prefs = getSharedPreferences("storeField", Context.MODE_PRIVATE);
        edit = prefs.edit();
        if (prefs.getBoolean("deactivate_contin", false)) {
            btnContinue.setClickable(false);
            btnContinue.setTextColor(Color.GRAY);
        } else {
            btnContinue.setClickable(true);
            btnContinue.setTextColor(btnStart.getTextColors());
        }
        //endregion

        btnContinue.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnHS.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        theFirstStart();
    }

    //Check the app for the first start
    public void theFirstStart() {
        //make the first HighScores record
        HighScoresActivity highScoresActivity = new HighScoresActivity();
        //SharedPreferences sharedPreferences = getSharedPreferences(highScoresActivity.getAPP_PREFERENCES(), MODE_PRIVATE);
        String savedText = prefs.getString(highScoresActivity.getAPP_PREFERENCES(), "");
        if (savedText.equals("")) {
            //edit = sharedPreferences.edit();
            edit.putString(highScoresActivity.getAPP_PREFERENCES(), "99:99");
            edit.putString(highScoresActivity.getAPP_PREFERENCES_DATE(), "01.09.2016");
            //deactivate the "Continue" button
            edit.putBoolean("deactivate_contin", true);

            edit.apply();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnContinue:
                contin = true;
                intent = new Intent(this, FieldActivity.class);
                intent.putExtra("contin", contin);
                startActivity(intent);
                break;
            case R.id.btnStart:
                intent = new Intent(this, FieldActivity.class);
                startActivity(intent);
                break;
            case R.id.btnHS:
                intent = new Intent(this, HighScoresActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRules:
                intent = new Intent(this, RulesActivity.class);
                startActivity(intent);
                break;
            case R.id.btnExit:
                System.exit(1);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "MainActivity: onResume()");

        //make the "Continue" button active or deactive
        if (prefs.getBoolean("deactivate_contin", false)) {
            btnContinue.setClickable(false);
            btnContinue.setTextColor(Color.GRAY);
        } else {
            btnContinue.setClickable(true);
            btnContinue.setTextColor(btnStart.getTextColors());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(TAG, "MainActivity: onDestroy()");
    }

}
