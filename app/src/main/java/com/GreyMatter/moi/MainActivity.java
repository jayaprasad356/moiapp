package com.GreyMatter.moi;

import static com.GreyMatter.moi.helper.Constant.SUCCESS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.GreyMatter.moi.Adapter.FunctionAdapter;
import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Functions;
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
    TextView tvMoiSent,tvMoirecive;
    Session session;
    FunctionAdapter functionAdapter;
    ImageView imgMenu;

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
        recyclerview = findViewById(R.id.recyclerview);


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

        recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));



        functionList();

    }



    private void functionList() {

        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(SUCCESS)){
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.RECENT_FUNCTIONS);
                        Gson g = new Gson();
                        ArrayList<Functions> functions = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Functions group = g.fromJson(jsonObject1.toString(), Functions.class);
                                functions.add(group);
                            } else {
                                break;
                            }
                        }
                        functionAdapter = new FunctionAdapter(activity, functions,"moidetails");
                        recyclerview.setAdapter(functionAdapter);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },activity, Constant.DASHBOARD,params,true);

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