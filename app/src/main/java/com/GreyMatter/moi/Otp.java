package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.aabhasjindal.otptextview.OtpTextView;

public class Otp extends AppCompatActivity {

    private Button BtnLogin;
    private String Mnumber;
    private OtpTextView otpTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        BtnLogin = findViewById(R.id.btnLogin);
        otpTextView = findViewById(R.id.optView);
        Mnumber = getIntent().getStringExtra(Constant.MOBILENUMBER);
        BtnLogin.setOnClickListener(v -> {
            if(otpTextView.getOTP().isEmpty() || otpTextView.getOTP().length() != 6) {
                Toast.makeText(this, "தவறான otp", Toast.LENGTH_SHORT).show();
            }else{
                startActivity(new Intent(this,ProfileActivity.class));
            }
        });
    }

}