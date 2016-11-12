package ru.narod.nod.fifteen;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by otc on 12.11.2016.
 */

class Ads {

    //vars
    private Context context;
    private String adBlock;
    private Activity activity;

    //constructor
    public Ads (Context context, Activity activity) {
        adBlock = "ca-app-pub-8956716360419559~4528395824";
        this.context = context;
        this.activity = activity;
    }

    //methods
    public AdRequest getSpecialAdRequest() {
        //return realAd(); //this method for working version - NOT TESTING
        return testAd(); //this method for testing - NOT WORKING
    }

    private AdRequest realAd () {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context, adBlock);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        return adRequest;
    }

    private AdRequest testAd () {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context.getApplicationContext(), "ca-app-pub-8956716360419559~4528395824");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get the test device ID
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();
        return adRequest;
    }
}
