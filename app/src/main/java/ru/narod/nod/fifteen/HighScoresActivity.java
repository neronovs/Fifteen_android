package ru.narod.nod.fifteen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

public class HighScoresActivity extends Activity {

    final static String HIGH_SCORE_SETTINGS_KEY = "HighScoreSettings";
    private final static String HIGH_SCORE_DATE_KEY = "Date";
    private TextView hsText, hsDate;

    public HighScoresActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        // Initialize the Mobile Ads SDK.
        AdView adView = findViewById(R.id.adViewHS);
        final Ads ads = new Ads(this);
        adView.loadAd(ads.getSpecialAdRequest());

        hsText = findViewById(R.id.hsText);
        hsDate = findViewById(R.id.hsDate);

        loadHighScore();
    }

    private void loadHighScore() {

        final SharedPreferences prefs = getSharedPreferences("storeField", MODE_PRIVATE);
        final String savedText = prefs.getString(HIGH_SCORE_SETTINGS_KEY, "");
        final String savedDate = prefs.getString(HIGH_SCORE_DATE_KEY, "");
        hsText.setText(savedText);
        hsDate.setText(savedDate);
    }

    public String getAPP_PREFERENCES() {
        return HIGH_SCORE_SETTINGS_KEY;
    }

    public String getAPP_PREFERENCES_DATE() {
        return HIGH_SCORE_DATE_KEY;
    }
}
