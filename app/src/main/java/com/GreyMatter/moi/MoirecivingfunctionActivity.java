package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.GreyMatter.moi.Adapter.MoirecivingfunctionAdapter;
import com.GreyMatter.moi.Adapter.MoisendingfunctionAdapter;
import com.GreyMatter.moi.model.Moirecivingfunction;
import com.GreyMatter.moi.model.Moisendingfunction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MoirecivingfunctionActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoirecivingfunctionAdapter moirecivingfunctionAdapter;
    FloatingActionButton fabbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moirecivingfunction);

        activity = MoirecivingfunctionActivity.this;

        backbtn = findViewById(R.id.backbtn);
        fabbtn = findViewById(R.id.fabbtn);
        recyclerview = findViewById(R.id.recyclerview);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,AddfunctionActivity.class);
                startActivity(intent);
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);


        moirecivefunction();
    }

    private void moirecivefunction() {
        ArrayList<Moirecivingfunction> moirecivingfunctions = new ArrayList<>();
        Moirecivingfunction moirecivingfunction1 = new Moirecivingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moirecivingfunction moirecivingfunction2 = new Moirecivingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        moirecivingfunctions.add(moirecivingfunction1);
        moirecivingfunctions.add(moirecivingfunction2);




        moirecivingfunctionAdapter = new MoirecivingfunctionAdapter(activity, moirecivingfunctions);
        recyclerview.setAdapter(moirecivingfunctionAdapter);
    }
}