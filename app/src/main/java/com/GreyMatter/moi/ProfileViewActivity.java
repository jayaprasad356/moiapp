package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

public class ProfileViewActivity extends AppCompatActivity {

    EditText tvplace,tvmobilenumber,tvusername;
    Session session;
    Activity activity;
    ImageView backbtn;
    Button btnverify;

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
        btnverify = findViewById(R.id.btnverify);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvmobilenumber.getText().toString().isEmpty()) {
                    Toast.makeText(activity, "தொடர தொலைபேசி எண்ணை உள்ளிடவும்", Toast.LENGTH_LONG).show(); //Enter phone number to proceed
                }else if(tvmobilenumber.getText().toString().length()!=10) {
                    Toast.makeText(activity, "தவறான மொபைல் எண்", Toast.LENGTH_SHORT).show();
                }
               else if(tvusername.getText().toString().isEmpty()) {
                    tvusername.setError("பெயர்");
                }
               else if(tvplace.getText().toString().isEmpty()) {
                    tvplace.setError("இடம்");
                }
               else if(tvusername.getText().toString().isEmpty() && tvplace.getText().toString().isEmpty()) {
                    tvusername.setError("பெயர்");
                    tvplace.setError("இடம்");
                }



                else{

                    Toast.makeText(activity, "verified", Toast.LENGTH_SHORT).show();
                    //need to change this after using shared preferences
//                    Intent i = new Intent(activity,OtpActivity.class);
//                    //i.putExtra(Constant.MOBILENUMBER,tvmobilenumber.getText().toString());
//                    startActivity(i);

                }
            }
        });










    }
}