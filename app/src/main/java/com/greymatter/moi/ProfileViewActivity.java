package com.greymatter.moi;

import static com.greymatter.moi.helper.Constant.SUCCESS;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.greymatter.moi.helper.ApiConfig;
import com.greymatter.moi.helper.Constant;
import com.greymatter.moi.helper.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ProfileViewActivity extends AppCompatActivity {

    EditText etPlace, etMobile, etName;
    Session session;
    Activity activity;
    ImageView backbtn;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        activity = ProfileViewActivity.this;
        session = new Session(activity);


        etName = findViewById(R.id.etName);
        etPlace = findViewById(R.id.etPlace);
        etMobile = findViewById(R.id.etMobile);
        etName.setText(session.getData(Constant.NAME));
        etPlace.setText(session.getData(Constant.LOCATION));
        etMobile.setText(session.getData(Constant.MOBILE));
        backbtn = findViewById(R.id.backbtn);
        btnUpdate = findViewById(R.id.btnUpdate);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty()) {
                    etName.setError("பெயர்");
                    etName.requestFocus();
                } else if (etPlace.getText().toString().isEmpty()) {
                    etPlace.setError("இடம்");
                    etPlace.requestFocus();
                } else {
                    updateUser();

                }
            }
        });


    }

    private void updateUser()
    {
        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.NAME,etName.getText().toString().trim());
        params.put(Constant.MOBILE,etMobile.getText().toString().trim());
        params.put(Constant.LOCATION,etPlace.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            //Log.d("DAS_RES",response);
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(SUCCESS)){
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.LOCATION,jsonArray.getJSONObject(0).getString(Constant.LOCATION));
                        Intent intent = new Intent(activity,MainActivity.class);
                        startActivity(intent);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },activity, Constant.UPDATEUSER_URL,params,false);



    }
}