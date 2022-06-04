package com.ketlas.ebanking.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.util.AnimationManager;


public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 3000;
    private static final Handler HANDLER = new Handler();
    private ImageView logo;
    private TextView splash_tv;
    private ImageView splash_iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = findViewById(R.id.logo);
        splash_tv = findViewById(R.id.splash_tv);
        splash_iv = findViewById(R.id.splash_iv);
        AnimationManager.slideUP(this,splash_tv);
        AnimationManager.slideDOWN(this,logo);
        HANDLER.postDelayed(()->{
            splash_iv.setVisibility(View.VISIBLE);
            AnimationManager.RevealAnim(splash_iv,new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e(SplashScreen.class.getName(),"ended");
                    Intent i = new Intent(SplashScreen.this, LogInActivity.class);
                    startActivity(i);
                    overridePendingTransition(0,0);
                    finish();
                }
            });
        },SPLASH_SCREEN_DURATION);


    }

}
