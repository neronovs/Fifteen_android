package ru.narod.nod.fifteen;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class HighScoresActivity extends AppCompatActivity {

    final String TAG = "States";
    public static final String APP_PREFERENCES = "HighScoreSettings";
    public static final String APP_PREFERENCES_DATE = "Date";
    private String highScores;
    ListView lvHS;
    TextView hsText, hsDate;
    SharedPreferences sharedPreferences;
    AdView adView;

    public HighScoresActivity() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//deny to change orientation of a screen

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-8956716360419559~4528395824");
        adView = (AdView) findViewById(R.id.adViewHS);
        AdRequest adRequest = new AdRequest.Builder()
                // Check the LogCat to get your test device ID
                //.addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();
        adView.loadAd(adRequest);

        hsText = (TextView) findViewById(R.id.hsText);
        hsDate = (TextView) findViewById(R.id.hsDate);
        highScores = loadHighScore();
    }

    public void saveHighScore(String newScore) {

        //int oldScore = prefs.getInt("key", 0);
        //if(newScore > oldScore ){.
            /*sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(APP_PREFERENCES_DATE, "Hi guys");//String.valueOf(newScore));
            edit.apply();*/
        //}
    }

    public String loadHighScore() {

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        String savedText = sharedPreferences.getString(APP_PREFERENCES, "");
        String savedDate = sharedPreferences.getString(APP_PREFERENCES_DATE, "");
        hsText.setText(savedText);
        hsDate.setText(savedDate);
        return savedText;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "HighScore: onResume()");

    }

    //Getters and Setters
    public String getHighScores() {
        return highScores;
    }
}
