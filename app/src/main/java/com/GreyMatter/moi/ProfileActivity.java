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

public class ProfileActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etLocation;
    private Button btnLogin;
    Activity activity;
    private String Mobile;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etUsername = findViewById(R.id.etUsername);
        etLocation = findViewById(R.id.etLocation);
        btnLogin = findViewById(R.id.btnLogin);
        activity = ProfileActivity.this;
        session = new Session(activity);
        Mobile = getIntent().getStringExtra(Constant.MOBILENUMBER);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("பெயர்");
                }else if(etLocation.getText().toString().isEmpty()) {
                    etLocation.setError("இடம்");
                }else if(etUsername.getText().toString().isEmpty() && etLocation.getText().toString().isEmpty()) {
                    etUsername.setError("பெயர்");
                    etLocation.setError("இடம்");
                }else{
                   RegisterUser();
                }
            }
        });

    }

    private void RegisterUser() {
        HashMap<String, String> params = new HashMap<>();
        params.put(Constant.NAME, etUsername.getText().toString());
        params.put(Constant.MOBILE, Mobile);
        params.put(Constant.LOCATION, etLocation.getText().toString());
        ApiConfig.RequestToVolley((Result, Response) -> {
            if (Result) {
                try {
                    JSONObject jsonObject = new JSONObject(Response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);

                        session.setBoolean("is_logged_in", true);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        startActivity(new Intent(activity, MainActivity.class));
                    } else {
                        Toast.makeText(activity, " unable to register user", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.REGISTERUSER, params, true);
    }
}