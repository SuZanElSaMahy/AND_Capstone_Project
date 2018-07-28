package com.suzanelsamahy.vidviewer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.suzanelsamahy.vidviewer.util.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash)
    ImageView imgView;

    private int SPLASH_TIME = 5 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.ic_logo_play)
                .into(imgView);

        NavigateToMainActivity();

    }


    private void NavigateToMainActivity() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        String prefId = SharedPreferencesManager.getInstance(getApplicationContext()).getStringPref(SharedPreferencesManager.CHANNEL_ID);
                        if(prefId!=null&& !prefId.isEmpty()){
                             intent = new Intent(SplashActivity.this,MainActivity.class);
                        } else {
                             intent = new Intent(SplashActivity.this,EnterChannelIdActivity.class);
                        }
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, SPLASH_TIME);
    }

}


