package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.aabhasjindal.otptextview.OtpTextView;

public class OtpActivity extends AppCompatActivity {

    private Button BtnLogin;
    private String Mnumber;
    private OtpTextView otpTextView;
    Activity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        activity =  OtpActivity.this;
        session = new Session(activity);

        BtnLogin = findViewById(R.id.btnLogin);
        otpTextView = findViewById(R.id.optView);

        Mnumber = getIntent().getStringExtra(Constant.MOBILENUMBER);
        BtnLogin.setOnClickListener(v -> {
            if(otpTextView.getOTP().isEmpty() || otpTextView.getOTP().length() != 6) {
                Toast.makeText(this, "தவறான otp", Toast.LENGTH_SHORT).show();
            }else{

                VerifyUser();
//                startActivity(new Intent(this,ProfileActivity.class));
            }
        });
    }


    private void VerifyUser() {
        Map<String,String> params = new HashMap<>();
        params.put(Constant.MOBILENUMBER,Mnumber);
        ApiConfig.RequestToVolley((result,response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(Constant.NEWUSER)) {
                        Intent i = new Intent(activity,ProfileActivity.class);
                        i.putExtra(Constant.MOBILENUMBER,Mnumber);
                        startActivity(i);
                    }else{
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setBoolean("is_logged_in", true);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        startActivity(new Intent(activity,
                                MainActivity.class));
                        finish();
                    }
                    finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(activity, "server unreachable", Toast.LENGTH_SHORT).show();
            }
        },activity,Constant.LOGIN_URL,params,true);
    }

}