package ru.narod.nod.fifteen

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdView
import ru.narod.nod.fifteen.HighScoresActivity.APP_PREFERENCES
import ru.narod.nod.fifteen.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : Activity(), View.OnClickListener {
    //Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    private lateinit var adView: AdView
    private var contin: Boolean? = null
    private lateinit var prefs: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    private lateinit var binding: ActivityMainBinding

    //***Идентификатор приложения: ca-app-pub-8956716360419559~6814211023
    //***Идентификатор рекламного блока: ca-app-pub-8956716360419559/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contin = false

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

    //Check the app for the first start
    private fun theFirstStart() {
        //make the first HighScores record
        val highScoresActivity = HighScoresActivity()
        val savedText = prefs.getString(highScoresActivity.apP_PREFERENCES, "")
        if (savedText == "") {
            edit.putString(highScoresActivity.apP_PREFERENCES, "--:--")
            edit.putString(highScoresActivity.apP_PREFERENCES_DATE, "--.--.----")
            edit.putBoolean("deactivate_contin", true)
            edit.apply()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnContinue -> {
                contin = true
                val intent = Intent(this, FieldActivity::class.java)
                intent.putExtra("contin", contin)
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

    override fun onResume() {
        super.onResume()
        binding.run {
            //make the "Continue" and "High scores" buttons active or inactive
            btnContinue.isEnabled = prefs.getBoolean("deactivate_contin", false)
            btnHS.isEnabled = prefs.getString(APP_PREFERENCES, "")?.isNotEmpty() == true
        }
    }
}
