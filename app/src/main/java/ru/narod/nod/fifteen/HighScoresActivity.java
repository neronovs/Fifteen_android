package ru.narod.nod.fifteen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

public class HighScoresActivity extends AppCompatActivity {

    final String TAG = "States";
    private final String APP_PREFERENCES = "HighScoreSettings";
    private final String APP_PREFERENCES_DATE = "Date";
    private String highScores;
    ListView lvHS;
    TextView hsText, hsDate;
    AdView adView;

    public HighScoresActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//deny to change orientation of a screen

        // Initialize the Mobile Ads SDK.
        adView = (AdView) findViewById(R.id.adViewHS);
        Ads ads = new Ads(this, HighScoresActivity.this);
        adView.loadAd(ads.getSpecialAdRequest());

        hsText = (TextView) findViewById(R.id.hsText);
        hsDate = (TextView) findViewById(R.id.hsDate);
        highScores = loadHighScore();

        //translation for the activity due to the system language
        Translater translater = new Translater(this, HighScoresActivity.this);
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

        SharedPreferences prefs = getSharedPreferences("storeField", MODE_PRIVATE);
        String savedText = prefs.getString(APP_PREFERENCES, "");
        String savedDate = prefs.getString(APP_PREFERENCES_DATE, "");
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

    public String getAPP_PREFERENCES() {
        return APP_PREFERENCES;
    }

    public String getAPP_PREFERENCES_DATE() {
        return APP_PREFERENCES_DATE;
    }
}
