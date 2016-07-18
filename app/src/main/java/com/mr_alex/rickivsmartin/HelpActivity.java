package com.mr_alex.rickivsmartin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Activity {

    private Sound sound;
    private int setButtonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_help);

        Button bMenu = (Button) findViewById(R.id.bMenu);
        bMenu.setTypeface(Typeface.createFromAsset(this.getAssets(), "font.ttf"));
        bMenu.setOnClickListener(onClickListener);

        TextView tAbout = (TextView) findViewById(R.id.tAbout);
        tAbout.setTypeface(Typeface.createFromAsset(this.getAssets(), "comic.ttf"));

        AssetManager am = getAssets();
        sound = new Sound(am, 1);
        setButtonSound = sound.loadSound("Schelchok1.mp3");

        try {
            sound.setCheckSound(getIntent().getExtras().getBoolean("checkSound"));
        } catch(Exception e) {
            sound.setCheckSound(true);
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sound.playSound(setButtonSound);
            Intent intent = new Intent(HelpActivity.this, MenuActivity.class);
            intent.putExtra("checkSound", sound.getCheckSound());
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }
    };
}