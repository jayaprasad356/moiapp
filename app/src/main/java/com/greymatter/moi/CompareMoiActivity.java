package com.greymatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.greymatter.moi.Adapter.MoiCompareAdapter;
import com.greymatter.moi.Adapter.MoiReceiveddetailsAdapter;
import com.greymatter.moi.helper.ApiConfig;
import com.greymatter.moi.helper.Constant;
import com.greymatter.moi.model.MoiCompareFunctions;
import com.greymatter.moi.model.MoiReceiveddata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompareMoiActivity extends AppCompatActivity {

    RecyclerView recyclerview,recivedrecyclerview;
    ImageView backbtn;
    Activity activity;
    TextView tvTitle;
    MoiCompareAdapter moiCompareAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_moi);

        activity = CompareMoiActivity.this;



        recivedrecyclerview = findViewById(R.id.recivedrecyclerview);
        recyclerview = findViewById(R.id.recyclerview);
        backbtn = findViewById(R.id.backbtn);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recivedrecyclerview.setLayoutManager(linearLayoutManager);



        Moiresciveddetails();
        MoiSendetails();


    }

    private void MoiSendetails() {

        ArrayList<MoiCompareFunctions> moiCompareFunctions = new ArrayList<>();



        MoiCompareFunctions moiCompareFunctions1 = new MoiCompareFunctions("1","1","நயனஂ - விக்னேஷ் திருமண விழா","","சென்னை","01-11-2022","100");


        moiCompareFunctions.add(moiCompareFunctions1);





        moiCompareAdapter = new MoiCompareAdapter(activity, moiCompareFunctions);
        recyclerview.setAdapter(moiCompareAdapter);
    }


    private void Moiresciveddetails() {


        ArrayList<MoiCompareFunctions> moiCompareFunctions = new ArrayList<>();



        MoiCompareFunctions moiCompareFunctions1 = new MoiCompareFunctions("1","1","நயனஂ - விக்னேஷ் திருமண விழா","","சென்னை","01-11-2022","100");

        moiCompareFunctions.add(moiCompareFunctions1);





        moiCompareAdapter = new MoiCompareAdapter(activity, moiCompareFunctions);
        recivedrecyclerview.setAdapter(moiCompareAdapter);


    }
}