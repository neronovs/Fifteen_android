package ru.narod.nod.fifteen

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.ads.AdView
import kotlin.system.exitProcess

class MainActivity : Activity(), View.OnClickListener {
    //Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    private lateinit var btnContinue: Button
    private lateinit var btnStart: Button
    private lateinit var btnHS: Button
    private lateinit var btnRules: Button
    private lateinit var btnExit: Button
    private lateinit var adView: AdView
    private var contin: Boolean? = null
    private lateinit var prefs: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    //***Идентификатор приложения: ca-app-pub-8956716360419559~6814211023
    //***Идентификатор рекламного блока: ca-app-pub-8956716360419559/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contin = false

        // Initialize the Mobile Ads SDK.
        adView = findViewById(R.id.adViewMain)
        val ads = Ads(this)
        adView.loadAd(ads.specialAdRequest)

        //region Button implementation
        btnContinue = findViewById(R.id.btnContinue)
        btnStart = findViewById(R.id.btnStart)
        btnHS = findViewById(R.id.btnHS)
        btnRules = findViewById(R.id.btnRules)
        btnExit = findViewById(R.id.btnExit)

        //make the "Continue" button active or deactive
        prefs = getSharedPreferences("storeField", MODE_PRIVATE)
        edit = prefs.edit()
        if (prefs.getBoolean("deactivate_contin", false)) {
            btnContinue.isClickable = false
            btnContinue.setTextColor(Color.GRAY)
        } else {
            btnContinue.isClickable = true
            btnContinue.setTextColor(btnStart.textColors)
        }
        //endregion
        btnContinue.setOnClickListener(this)
        btnStart.setOnClickListener(this)
        btnHS.setOnClickListener(this)
        btnRules.setOnClickListener(this)
        btnExit.setOnClickListener(this)
        theFirstStart()
    }

    //Check the app for the first start
    private fun theFirstStart() {
        //make the first HighScores record
        val highScoresActivity = HighScoresActivity()
        val savedText = prefs.getString(highScoresActivity.apP_PREFERENCES, "")
        if (savedText == "") {
            edit.putString(highScoresActivity.apP_PREFERENCES, "99:99")
            edit.putString(highScoresActivity.apP_PREFERENCES_DATE, "01.09.2016")
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
        //make the "Continue" button active or deactive
        if (prefs.getBoolean("deactivate_contin", false)) {
            btnContinue.isClickable = false
            btnContinue.setTextColor(Color.GRAY)
        } else {
            btnContinue.isClickable = true
            btnContinue.setTextColor(btnStart.textColors)
        }
    }
}
