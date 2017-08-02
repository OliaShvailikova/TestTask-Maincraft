package com.example.home.testminecraft;


import android.app.AlertDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class DetailActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_detail);
        ViewGroup.LayoutParams params =linearLayout.getLayoutParams();
        params.width=width/2;
        linearLayout.setLayoutParams(params);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(DetailActivity.this);
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });
        final Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int image = extras.getInt("image");
        String text = extras.getString("text");
        final int i = extras.getInt("zip");
        final boolean video = extras.getBoolean("video");
        TextView name_of_map = (TextView) findViewById(R.id.txt_name);
        name_of_map.setText(name);
        TextView information_abpot_map = (TextView) findViewById(R.id.txt_maps);
        information_abpot_map.setText(text);
        ImageView screen = (ImageView) findViewById(R.id.image);
        switch (image) {
            case 1:
                screen.setImageResource(R.drawable.screen1);
                break;
            case 2:
                screen.setImageResource(R.drawable.screen2);
                break;
            case 3:
                screen.setImageResource(R.drawable.screen3);
                break;
        }
        final Button btn_load = (Button) findViewById(R.id.butt_inst);
        if (video==true){
            btn_load.setBackgroundResource(R.drawable.button_pressed_style);
            btn_load.setText(R.string.title);
        }
        View.OnClickListener btnOn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video==true){
                    CustomDialog dialog = new CustomDialog();
                    dialog.show(getFragmentManager(),"");
                } else {
                    Toast.makeText(getApplicationContext(), R.string.load, Toast.LENGTH_LONG).show();
                    String unzipLocation = Environment.getExternalStorageDirectory() + "/unzipped/";
                    ZipManager.unzipFromAssets(getApplicationContext(), getIntent().getStringExtra("zip"), unzipLocation);
                }
            }
        };
        btn_load.setOnClickListener(btnOn);
        final Button btn_back = (Button) findViewById(R.id.butt_back);
        View.OnClickListener btnBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interstitial.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        displayInterstitial();
                    }
                });
                DetailActivity.super.onBackPressed();
            }
        };
        btn_back.setOnClickListener(btnBack);
    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


}


