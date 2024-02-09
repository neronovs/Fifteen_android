package ru.narod.nod.fifteen;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.List;


class Ads {

    //vars
    private final Context context;

    //constructor
    public Ads(Context context) {
        this.context = context;
    }

    //methods
    public AdRequest getSpecialAdRequest() {
        MobileAds.initialize(context);

        if (BuildConfig.DEBUG) {
            addTestDevices();
        }

        return new AdRequest.Builder()
                .build();
    }

    private void addTestDevices() {
        // Check the LogCat to get the test device ID
        List<String> testDeviceIds = List.of("33BE2250B43518CCDA7DE426D04EE231");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);
    }
}
