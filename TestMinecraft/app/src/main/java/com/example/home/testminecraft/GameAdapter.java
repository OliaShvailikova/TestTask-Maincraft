package com.example.home.testminecraft;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;

public class GameAdapter extends ArrayAdapter<String> {

    private Context con;
    private int lastPosition = -1;
    ImageView iv_gameimages;
    TextView tv_gamenames;
    TextView tv_nameoffile;
    Button bt_more;


    private int number = 3;
    Maps[] mapsarray;
    String[] names;
    String[] map;
    int images_arr[] = {

            R.drawable.screen1,
            R.drawable.screen2,
            R.drawable.screen3

    };
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;

    public GameAdapter(Context context, int resource, String[] objects, Maps[] array) {
        super(context, resource, objects);
        this.con = context;
        this.mapsarray = array;
        this.names = objects;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(con);
        interstitial.setAdUnitId(con.getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest);
        final LayoutInflater inflater = (LayoutInflater) con.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_item_template, parent, false);
        iv_gameimages = (ImageView) row.findViewById(R.id.game_image);
        iv_gameimages.setImageResource(images_arr[position]);
        tv_gamenames = (TextView) row.findViewById(R.id.txt_game_name);
        tv_gamenames.setText(names[position]);
        tv_nameoffile = (TextView) row.findViewById(R.id.txt_nameofzip);
        tv_nameoffile.setText(mapsarray[position].getMap());
        bt_more = (Button) row.findViewById(R.id.butt_install);
        final int image_id = position+1;
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(con, DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image", image_id);
                intent.putExtra("name", names[position]);
                intent.putExtra("text", mapsarray[position].getText_about_map());
                intent.putExtra("zip", mapsarray[position].getMap());
                intent.putExtra("video",mapsarray[position].isVideo());
                con.startActivity(intent);
            }
        };
        bt_more.setOnClickListener(oclBtn);

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        row.startAnimation(animation);
        lastPosition = position;
        return row;
    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}