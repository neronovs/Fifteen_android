package ru.narod.nod.fifteen;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;


class Ads {

    //vars
    private final Context context;
    private final String adBlock;

    //constructor
    public Ads (Context context) {
        adBlock = "ca-app-pub-8956716360419559~4528395824";
        this.context = context;
    }

    //methods
    public AdRequest getSpecialAdRequest() {
        return BuildConfig.DEBUG ? testAd() : realAd();
    }

    private AdRequest realAd () {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context, adBlock);
        return new AdRequest.Builder()
                .build();
    }

    private AdRequest testAd () {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(context.getApplicationContext(), adBlock);
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get the test device ID
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();
    }
}
