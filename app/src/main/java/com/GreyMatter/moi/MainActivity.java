package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.GreyMatter.moi.Adapter.AttendingfunctionAdapter;
import com.GreyMatter.moi.model.Attendingfunction;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MaterialCardView homecard1,homecard2;
    Activity activity;
    RecyclerView recyclerview;
    AttendingfunctionAdapter attendingfunctionAdapter;
    TextView tvMoiSent,tvMoirecive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = MainActivity.this;

        homecard1 = findViewById(R.id.homecard1);
        homecard2 = findViewById(R.id.homecard2);
        tvMoiSent = findViewById(R.id.tvMoiSent);
        tvMoirecive = findViewById(R.id.tvMoirecive);
        recyclerview = findViewById(R.id.recyclerview);

        homecard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MoisenddetailsActivity.class);
                startActivity(intent);
            }
        });
        homecard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MoiRecivedfunctionActivity.class);
                startActivity(intent);
            }
        });
        tvMoiSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MoisentfunctionActivity.class);
                startActivity(intent);
            }
        });
        tvMoirecive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MoirecivingfunctionActivity.class);
                startActivity(intent);
            }
        });

        //recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);

        functionlist();

    }

    private void functionlist() {

        ArrayList<Attendingfunction> attendingfunctions = new ArrayList<>();
        Attendingfunction attendingfunction1 = new Attendingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Attendingfunction attendingfunction2 = new Attendingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Attendingfunction attendingfunction3 = new Attendingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        attendingfunctions.add(attendingfunction1);
        attendingfunctions.add(attendingfunction2);
        attendingfunctions.add(attendingfunction3);

        attendingfunctionAdapter = new AttendingfunctionAdapter(activity, attendingfunctions);
        recyclerview.setAdapter(attendingfunctionAdapter);


    }
}