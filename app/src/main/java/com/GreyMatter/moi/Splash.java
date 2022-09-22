package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.GreyMatter.moi.helper.Session;

public class Splash extends AppCompatActivity {

    private TextView SplashText;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashText = findViewById(R.id.SplashText);
        handler = new Handler();

        GotoActivity();

        AlphaAnimation animation = new AlphaAnimation(0.0f,0.9f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        SplashText.setAnimation(animation);;
    }
    private void GotoActivity()
    {
        Session session = new Session(Splash.this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.getBoolean("is_logged_in")){
                    Intent intent=new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent=new Intent(Splash.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }



            }
        },2000);
    }
}