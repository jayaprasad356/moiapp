package com.greymatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greymatter.moi.helper.Session;

import java.util.Calendar;

public class AddMoiActivity extends AppCompatActivity {

    private EditText etEventName;
    private TextView tvFunDate,tvMoiDetails;
    private EditText etPlace;
    private Button btnSubmit;
    Session session;
    Activity activity;
    String FunctionDate  = "";
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moi);

        etEventName = findViewById(R.id.etEventName);
        tvFunDate = findViewById(R.id.tvFunDate);
        etPlace = findViewById(R.id.etPlace);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvMoiDetails = findViewById(R.id.tvMoiDetails);
        backbtn = findViewById(R.id.backbtn);
        activity = AddMoiActivity.this;

        session = new Session(activity);

        tvMoiDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,MoisenddetailsActivity.class);
                startActivity(intent);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity,MoisenddetailsActivity.class);
                startActivity(intent);

//                if (etEventName.getText().toString().isEmpty()) {
//                    etEventName.setError("ஏதுமில்லை");
//                    etEventName.requestFocus();
//                } else if (etPlace.getText().toString().isEmpty()) {
//                    etPlace.setError("ஏதுமில்லை");
//                    etEventName.requestFocus();
//                } else if (FunctionDate.isEmpty()) {
//                    Toast.makeText(activity, "தேதியைத் தேர்ந்தெடுக்கவும்", Toast.LENGTH_SHORT).show();
//                } else {
////                    AddFunction();
//
//                    Intent intent = new Intent(activity,MoisenddetailsActivity.class);
//                    startActivity(intent);
//                }
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvFunDate.setOnClickListener(v -> {
            showDatePicker();
        });
    }


    private void showDatePicker()
    {
        int mYear,mMonth,mDay;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            monthOfYear = monthOfYear + 1;
            FunctionDate = year + "-"+convertTwodigit(monthOfYear)+"-"+ convertTwodigit(dayOfMonth);
            tvFunDate.setText(FunctionDate);

        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private String convertTwodigit(int s)
    {
        long val = (long) s;
        String format = "%1$02d";
        return (String.format(format,val));
    }
}