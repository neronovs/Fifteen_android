package ru.narod.nod.fifteen

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdView
import ru.narod.nod.fifteen.FieldActivity.CONTINUE_KEY
import ru.narod.nod.fifteen.HighScoresActivity.HIGH_SCORE_SETTINGS_KEY
import ru.narod.nod.fifteen.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : Activity(), View.OnClickListener {
    private lateinit var adView: AdView
    private var isContinue: Boolean? = null
    private lateinit var prefs: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    private lateinit var binding: ActivityMainBinding

    //***Идентификатор приложения: ca-app-pub-8956716360419559~6814211023
    //***Идентификатор рекламного блока: ca-app-pub-8956716360419559/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isContinue = false

        // Initialize the Mobile Ads SDK.
        adView = findViewById(R.id.adViewMain)
        val ads = Ads(this)
        adView.loadAd(ads.specialAdRequest)

        //make the "Continue" button active or deactive
        prefs = getSharedPreferences("storeField", MODE_PRIVATE)
        edit = prefs.edit()

        //endregion
        binding.run {
            btnContinue.setOnClickListener(this@MainActivity)
            btnStart.setOnClickListener(this@MainActivity)
            btnHS.setOnClickListener(this@MainActivity)
            btnRules.setOnClickListener(this@MainActivity)
            btnExit.setOnClickListener(this@MainActivity)
        }

        theFirstStart()
    }

    override fun onResume() {
        super.onResume()
        binding.run {
            //make the "Continue" and "High scores" buttons active or inactive
            btnContinue.isEnabled = !prefs.getBoolean(DEACTIVATE_CONTINUE_KEY, true)
            btnHS.isEnabled = prefs.getString(HIGH_SCORE_SETTINGS_KEY, "")?.isNotEmpty() == true
        }
    }


    //Check the app for the first start
    private fun theFirstStart() {
        //make the first HighScores record
        val highScoresActivity = HighScoresActivity()
        val savedText = prefs.getString(highScoresActivity.apP_PREFERENCES, "")
        if (savedText == "") {
            edit.run {
                putString(highScoresActivity.apP_PREFERENCES, "--:--")
                putString(highScoresActivity.apP_PREFERENCES_DATE, "--.--.----")
                putBoolean(DEACTIVATE_CONTINUE_KEY, true)
                apply()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnContinue -> {
                isContinue = true
                val intent = Intent(this, FieldActivity::class.java)
                intent.putExtra(CONTINUE_KEY, isContinue)
                startActivity(intent)
            }

            R.id.btnStart -> {
                val intent = Intent(this, FieldActivity::class.java)
                startActivity(intent)
            }

            R.id.btnHS -> {
                val intent = Intent(this, HighScoresActivity::class.java)
                startActivity(intent)
            }

            R.id.btnRules -> {
                val intent = Intent(this, RulesActivity::class.java)
                startActivity(intent)
            }

            R.id.btnExit -> exitProcess(1)
        }
    }

    companion object {
        const val DEACTIVATE_CONTINUE_KEY = "deactivate_contin"
    }
}
