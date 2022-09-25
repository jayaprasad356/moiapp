package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

public class ProfileViewActivity extends AppCompatActivity {

    EditText tvplace,tvmobilenumber,tvusername;
    Session session;
    Activity activity;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        activity = ProfileViewActivity.this;
        session = new Session(activity);


        tvusername = findViewById(R.id.etUsername);
        tvplace = findViewById(R.id.etLocation);
        tvmobilenumber = findViewById(R.id.etMobile);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });









    }
}