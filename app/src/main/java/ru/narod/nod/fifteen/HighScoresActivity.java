package ru.narod.nod.fifteen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

public class HighScoresActivity extends Activity {

    private final String APP_PREFERENCES = "HighScoreSettings";
    private final String APP_PREFERENCES_DATE = "Date";
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
        adView = findViewById(R.id.adViewHS);
        final Ads ads = new Ads(this);
        adView.loadAd(ads.getSpecialAdRequest());

        hsText = findViewById(R.id.hsText);
        hsDate = findViewById(R.id.hsDate);
    }

    public String loadHighScore() {

        final SharedPreferences prefs = getSharedPreferences("storeField", MODE_PRIVATE);
        final String savedText = prefs.getString(APP_PREFERENCES, "");
        final String savedDate = prefs.getString(APP_PREFERENCES_DATE, "");
        hsText.setText(savedText);
        hsDate.setText(savedDate);
        return savedText;
    }

    public String getAPP_PREFERENCES() {
        return APP_PREFERENCES;
    }

    public String getAPP_PREFERENCES_DATE() {
        return APP_PREFERENCES_DATE;
    }
}
