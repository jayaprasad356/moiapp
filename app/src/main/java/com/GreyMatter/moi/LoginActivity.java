package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etPhoneNumber;
    private Button btnLogin;
    Activity activity;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhoneNumber = findViewById(R.id.etMobile);
        btnLogin = findViewById(R.id.btnLogin);
        activity = LoginActivity.this;
        session = new Session(activity);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(activity, "தொடர தொலைபேசி எண்ணை உள்ளிடவும்", Toast.LENGTH_LONG).show(); //Enter phone number to proceed
                }else if(etPhoneNumber.getText().toString().length()!=10) {
                    Toast.makeText(activity, "தவறான மொபைல் எண்", Toast.LENGTH_SHORT).show();
                } else{
                    //need to change this after using shared preferences
                  // Intent i = new Intent(activity,Otp.class);
                   // i.putExtra(Constant.MOBILENUMBER,etPhoneNumber.getText().toString());
                    //startActivity(i);
                    VerifyUser();
                }
            }
        });

    }

    private void VerifyUser() {
        Map<String,String> params = new HashMap<>();
        params.put(Constant.MOBILENUMBER,etPhoneNumber.getText().toString().trim());
        ApiConfig.RequestToVolley((result,response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(Constant.NEWUSER)) {
                       Intent i = new Intent(activity,ProfileActivity.class);
                       i.putExtra(Constant.MOBILENUMBER,etPhoneNumber.getText().toString());
                       startActivity(i);
                    }else{
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setBoolean("is_logged_in", true);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        startActivity(new Intent(activity,
                                MainActivity.class));
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