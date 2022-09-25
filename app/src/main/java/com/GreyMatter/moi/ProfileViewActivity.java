package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

public class ProfileViewActivity extends AppCompatActivity {

    TextView tvplace,tvmobilenumber,tvusername;
    Session session;
    Activity activity;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        activity = ProfileViewActivity.this;
        session = new Session(activity);


        tvusername = findViewById(R.id.tvusername);
        tvplace = findViewById(R.id.tvplace);
        tvmobilenumber = findViewById(R.id.tvmobilenumber);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





        tvusername.setText(session.getData(Constant.NAME));
        tvplace.setText(session.getData(Constant.LOCATION));
        tvmobilenumber.setText(session.getData(Constant.MOBILE));






    }
}