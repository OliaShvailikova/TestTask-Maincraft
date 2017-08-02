package com.example.home.testminecraft;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {
    private int number = 3;
    Maps[] mapsarray = new Maps[number];
    String[] names = new String[number];
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < number; i++) {
            mapsarray[i] = new Maps(i, getApplicationContext());
        }
        for (int i = 0; i < number; i++) {
            names[i] = mapsarray[i].getName_of_map();
        }
        mapsarray[1].setVideo(true);

        ListView gameList;

        gameList = (ListView) findViewById(R.id.listView);
        GameAdapter adapter = new GameAdapter(MainActivity.this, R.layout.list_item_template, names, mapsarray);
        gameList.setAdapter(adapter);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4599836135724302~6952371078");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);


    }


}
