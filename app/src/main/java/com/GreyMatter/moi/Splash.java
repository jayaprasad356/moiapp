package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private TextView SplashText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashText = findViewById(R.id.SplashText);

        AlphaAnimation animation = new AlphaAnimation(0.0f,0.9f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        SplashText.setAnimation(animation);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 startActivity(new Intent(Splash.this, LoginActivity.class));
             }
         },3000);
    }
}