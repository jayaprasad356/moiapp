package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;

import org.json.JSONObject;

import java.util.HashMap;

public class NewmemeberaddActivity extends AppCompatActivity {
    ImageView backbtn;
    private EditText etName;
    private EditText etMobile;
    private EditText etTown;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmemeberadd);

        backbtn = findViewById(R.id.backbtn);
        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etTown = findViewById(R.id.etTown);
        activity = NewmemeberaddActivity.this;
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v-> {
            if(etName.getText().toString().isEmpty()){
                etName.setError("பெயரஂ");
            }else if(etMobile.getText().toString().length()!=10) {
                etMobile.setError("தவறான எண்");
            }else if(etTown.getText().toString().isEmpty()) {
                etTown.setError("செல்லாத ஊர்");
            }else{
                NewMember();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void NewMember() {
        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.NAME,etName.getText().toString());
        params.put(Constant.MOBILE,etMobile.getText().toString());
        params.put(Constant.LOCATION,etTown.getText().toString());

        ApiConfig.RequestToVolley((result,response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        startActivity(new Intent(activity, MainActivity.class));
                    } else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(activity, "Server error", Toast.LENGTH_SHORT).show();
            }
        },activity,Constant.REGISTERUSER,params,true);
    }
}