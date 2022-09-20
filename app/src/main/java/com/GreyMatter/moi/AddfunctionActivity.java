package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

import org.json.JSONObject;

import java.util.HashMap;

public class AddfunctionActivity extends AppCompatActivity {

    private EditText etEventName;
    private EditText etDate;
    private EditText etPlace;
    private Button btnSubmit;
    Session session;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfunction);

        etEventName = findViewById(R.id.etEventName);
        etDate = findViewById(R.id.etDate);
        etPlace = findViewById(R.id.etPlace);
        btnSubmit = findViewById(R.id.btnSubmit);
        activity = AddfunctionActivity.this;
        session = new Session(activity);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEventName.getText().toString().isEmpty()) {
                    etEventName.setError("");
                }else if(etPlace.getText().toString().isEmpty()) {
                    etPlace.setError("");
                }else if(etDate.getText().toString().isEmpty()) {
                    etDate.setError("");
                }else{
                    AddFunction();
                }
            }
        });
    }

    private void AddFunction() {
        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.FUNCTIONAME,etEventName.getText().toString());
        params.put(Constant.LOCATION,etPlace.getText().toString());
        params.put(Constant.DATE,etDate.getText().toString());

        ApiConfig.RequestToVolley((result,response)-> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(Constant.SUCCESS)){
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity, "Unable to add function", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
            }
        },activity,Constant.ADDFUNCTION,params,true);
    }

}