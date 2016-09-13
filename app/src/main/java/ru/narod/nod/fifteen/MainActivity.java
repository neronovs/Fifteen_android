package ru.narod.nod.fifteen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    Button btnStart, btnHS, btnRules, btnExit;
    final String TAG = "States";
    AdView adView;
    //***Идентификатор приложения: ca-app-pub-8956716360419559~6814211023
    //***Идентификатор рекламного блока: ca-app-pub-8956716360419559/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//deny to change orientation of a screen
        //Log.d(TAG, "MainActivity: onCreate()");

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-8956716360419559~4528395824");
        adView = (AdView) findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder()
                // Check the LogCat to get your test device ID
                //.addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();
        adView.loadAd(adRequest);

        //region Button impementation
        btnStart = (Button) findViewById(R.id.btnStart);
        btnHS = (Button) findViewById(R.id.btnHS);
        btnRules = (Button) findViewById(R.id.btnRules);
        btnExit = (Button) findViewById(R.id.btnExit);
        //endregion

        btnStart.setOnClickListener(this);
        btnHS.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
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
