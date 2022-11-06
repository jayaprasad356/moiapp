package com.greymatter.moi;

import static com.greymatter.moi.helper.Constant.SUCCESS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.greymatter.moi.Adapter.MoiSentFunctionAdapter;
import com.greymatter.moi.helper.ApiConfig;
import com.greymatter.moi.helper.Constant;
import com.greymatter.moi.helper.Session;
import com.greymatter.moi.model.MoiSentFunctions;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    MaterialCardView homecard1,homecard2;
    Activity activity;
    RecyclerView recyclerview;
    TextView tvMoiSent,tvMoirecive,tvMoicompare;
    Session session;
    MoiSentFunctionAdapter moiSentFunctionAdapter;
    ImageView imgMenu;
    TextView tvDebit,tvCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = MainActivity.this;
        session = new Session(activity);

        homecard1 = findViewById(R.id.homecard1);
        homecard2 = findViewById(R.id.homecard2);
        imgMenu = findViewById(R.id.imgMenu);
        tvMoiSent = findViewById(R.id.tvMoiSent);
        tvMoirecive = findViewById(R.id.tvMoirecive);
        tvMoicompare = findViewById(R.id.tvMoicompare);
        recyclerview = findViewById(R.id.recyclerview);
        tvDebit = findViewById(R.id.tvDebit);
        tvCredit = findViewById(R.id.tvCredit);


        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopup(view);
            }
        });

        homecard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changing this for testing
                Intent intent = new Intent(MainActivity.this,MoisenddetailsActivity.class);
                startActivity(intent);
            }
        });
        homecard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changing this for testing
                Intent intent = new Intent(MainActivity.this, FunctionListActivity.class);
                intent.putExtra(Constant.MOI,"moidetails");
                startActivity(intent);
            }
        });
        tvMoiSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddMoiActivity.class);
//                intent.putExtra(Constant.MOI,"addmoi");
                startActivity(intent);
            }
        });
        tvMoirecive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FunctionListActivity.class);
                intent.putExtra(Constant.MOI,"addmoi");
                startActivity(intent);
            }
        });
        tvMoicompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompareMoiActivity.class);
//                intent.putExtra(Constant.MOI,"addmoi");
                startActivity(intent);
            }
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        functionList();
        dashboardList();

    }

    private void dashboardList()
    {
        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("DAS_RES",response);
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(SUCCESS)){
                        tvDebit.setText("₹ "+jsonObject.getString(Constant.DEBITMOI));
                        tvCredit.setText("₹ "+jsonObject.getString(Constant.CREDITMOI));
                        session.setData(Constant.NAME,jsonObject.getString(Constant.NAME));
                        session.setData(Constant.MOBILE,jsonObject.getString(Constant.MOBILE));
                        session.setData(Constant.LOCATION,jsonObject.getString(Constant.LOCATION));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },activity, Constant.DASHBOARD,params,false);


    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }



    private void functionList() {

        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(SUCCESS)){
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<MoiSentFunctions> moiSentFunctions = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                MoiSentFunctions group = g.fromJson(jsonObject1.toString(), MoiSentFunctions.class);
                                moiSentFunctions.add(group);
                            } else {
                                break;
                            }
                        }
                        moiSentFunctionAdapter = new MoiSentFunctionAdapter(activity, moiSentFunctions);
                        recyclerview.setAdapter(moiSentFunctionAdapter);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },activity, Constant.MOILIST,params,true);

    }

    public void showpopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId() == R.id.logoutitem){
            session.logoutUser(activity);
        }
        else if (item.getItemId() == R.id.profileitem){

            Intent intent = new Intent(MainActivity .this,ProfileViewActivity.class);
            startActivity(intent);

        }
        return false;
    }
}