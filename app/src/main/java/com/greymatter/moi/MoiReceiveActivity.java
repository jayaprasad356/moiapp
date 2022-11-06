package com.greymatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.greymatter.moi.helper.ApiConfig;
import com.greymatter.moi.helper.Constant;
import com.greymatter.moi.helper.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MoiReceiveActivity extends AppCompatActivity {

    Button newmemberbtn;
    private AutoCompleteTextView etMobile;
    private EditText etAmount;
    private Button btnSubmit;
    TextView tvFunName;
    String FunctionName,FunctionID;
    Activity activity;
    AlertDialog alertDialog;
    Session session;
    ImageView backbtn;

    private String[] TextViewData = {"john","somex","somey","somez"};
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi_receive);
        activity = MoiReceiveActivity.this;
        session = new Session(activity);
        newmemberbtn = findViewById(R.id.newmemberbtn);
        btnSubmit = findViewById(R.id.btnSubmit);
        etMobile = findViewById(R.id.etMobile);
        etAmount = findViewById(R.id.etAmount);
        backbtn = findViewById(R.id.backbtn);

        tvFunName = findViewById(R.id.tvFunName);
        FunctionName = getIntent().getStringExtra(Constant.FUNCTIONAME);
        FunctionID = getIntent().getStringExtra(Constant.FUNCTIONID);
//        tvFunName.setText(FunctionName);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,TextViewData);
        etMobile.setAdapter(arrayAdapter);
        etMobile.setThreshold(2);
        newmemberbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.new_user_layout, viewGroup, false);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etMobile = dialogView.findViewById(R.id.etMobile);
                EditText etPlace = dialogView.findViewById(R.id.etPlace);
                Button btnSubmit = dialogView.findViewById(R.id.btnSubmit);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NewMember(etName.getText().toString().trim(),etMobile.getText().toString().trim(),etPlace.getText().toString().trim());
                    }
                });
                builder.setView(dialogView);
                alertDialog = builder.create();

                alertDialog.show();
            }
        });

        btnSubmit.setOnClickListener(v -> {
            if(etMobile.getText().toString().isEmpty()) {
                etMobile.setError("கைபேசி எண்");
                etMobile.requestFocus();
            }else if(etAmount.getText().toString().isEmpty()) {
                etAmount.setError("தொகை");
                etAmount.requestFocus();
            }else if(etMobile.getText().toString().length() != 10) {
                etMobile.setError("தவறான எண்");
                etMobile.requestFocus();
            }else{

                addmoi();
                // need to edit this for next updates
            }
        });
    }

    private void addmoi() {


        Map<String, String> params = new HashMap<>();
        params.put(Constant.ORGANIZER_ID,session.getData(Constant.ID));
        params.put(Constant.FUNCTIONID,FunctionID);
        params.put(Constant.MOBILE,etMobile.getText().toString().trim());
        params.put(Constant.AMOUNT,etAmount.getText().toString().trim());





        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("SIGNUP_RES",response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(activity, MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(this, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }

            //pass url
        }, activity, Constant.ADDMOI, params,true);






    }

    private void NewMember(String name, String mobile, String place) {
        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.NAME,name);
        params.put(Constant.MOBILE,mobile);
        params.put(Constant.LOCATION,place);
        ApiConfig.RequestToVolley((result, response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },activity,Constant.REGISTERUSER,params,true);
    }
}