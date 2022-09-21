package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;

import org.json.JSONObject;

import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;

public class AddfunctionActivity extends AppCompatActivity {

    private EditText etEventName;
    private EditText etDate;
    private EditText etPlace;
    private Button btnSubmit;
    Session session;
    Activity activity;
    private LinearLayout lyDate;

    //attributes for initial dates
    private static int Date;
    private static int month;
    private static int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfunction);

        etEventName = findViewById(R.id.etEventName);
        etDate = findViewById(R.id.etDate);
        etPlace = findViewById(R.id.etPlace);
        btnSubmit = findViewById(R.id.btnSubmit);
        lyDate = findViewById(R.id.lyDate);
        activity = AddfunctionActivity.this;

        Calendar calendar = Calendar.getInstance();
        Date = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
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
                lyDate.setOnClickListener(v-> {
                    new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            etDate.setText(year + "-" + +month + "-" + dayOfMonth);
                        }
                    },year,month,Date).show();
                });
    }

    private void AddFunction() {
        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,"1");
        params.put(Constant.FUNCTIONAME,etEventName.getText().toString());
        params.put(Constant.PLACE,etPlace.getText().toString());
        params.put(Constant.DATE,etDate.getText().toString());

        ApiConfig.RequestToVolley((result,response)-> {
            Log.d("ADD_FUNC",response);
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