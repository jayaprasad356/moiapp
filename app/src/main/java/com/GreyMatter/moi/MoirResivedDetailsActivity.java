package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.GreyMatter.moi.Adapter.MoireciveddetailsAdapter;
import com.GreyMatter.moi.Adapter.MoisenddetailsAdapter;
import com.GreyMatter.moi.model.Moireciveddetails;
import com.GreyMatter.moi.model.Moisendeddetails;

import java.util.ArrayList;

public class MoirResivedDetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoireciveddetailsAdapter moireciveddetailsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moir_resived_details);

        activity = MoirResivedDetailsActivity.this;

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


        Moiresciveddetails();
    }

    private void Moiresciveddetails() {


        ArrayList<Moireciveddetails> moireciveddetails = new ArrayList<>();
        Moireciveddetails moireciveddetails1 = new Moireciveddetails("1","1","விக்னேஷ்","9876543210","சென்னை","₹ 200");

        moireciveddetails.add(moireciveddetails1);



        moireciveddetailsAdapter = new MoireciveddetailsAdapter(activity, moireciveddetails);
        recyclerview.setAdapter(moireciveddetailsAdapter);

    }
}