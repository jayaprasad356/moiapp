package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.GreyMatter.moi.Adapter.MoisendingfunctionAdapter;
import com.GreyMatter.moi.model.Moisendingfunction;

import java.util.ArrayList;

public class MoisentfunctionActivity extends AppCompatActivity {

    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoisendingfunctionAdapter moisendingfunctionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisentfunction);

        activity = MoisentfunctionActivity.this;

        backbtn = findViewById(R.id.backbtn);
        recyclerview = findViewById(R.id.recyclerview);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);


        moisentfunction();



    }

    private void moisentfunction() {

        ArrayList<Moisendingfunction> moisendingfunctions = new ArrayList<>();
        Moisendingfunction moisendingfunction1 = new Moisendingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        moisendingfunctions.add(moisendingfunction1);




        moisendingfunctionAdapter = new MoisendingfunctionAdapter(activity, moisendingfunctions);
        recyclerview.setAdapter(moisendingfunctionAdapter);

    }
}