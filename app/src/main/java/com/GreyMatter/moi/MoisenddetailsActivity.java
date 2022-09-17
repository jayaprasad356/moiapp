package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.GreyMatter.moi.Adapter.MoisenddetailsAdapter;
import com.GreyMatter.moi.model.Moisendeddetails;

import java.util.ArrayList;

public class MoisenddetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoisenddetailsAdapter moisenddetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisenddetails);
        activity = MoisenddetailsActivity.this;

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


        MoiSend();
    }

    private void MoiSend() {
        ArrayList<Moisendeddetails> moisendeddetails = new ArrayList<>();
        Moisendeddetails moisendeddetails1 = new Moisendeddetails("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moisendeddetails moisendeddetails2 = new Moisendeddetails("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moisendeddetails moisendeddetails3 = new Moisendeddetails("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        moisendeddetails.add(moisendeddetails1);
        moisendeddetails.add(moisendeddetails2);
        moisendeddetails.add(moisendeddetails3);


        moisenddetailsAdapter = new MoisenddetailsAdapter(activity, moisendeddetails);
        recyclerview.setAdapter(moisenddetailsAdapter);
    }
}