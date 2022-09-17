package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.GreyMatter.moi.Adapter.AttendingfunctionAdapter;
import com.GreyMatter.moi.Adapter.MoisendAdapter;
import com.GreyMatter.moi.model.Attendingfunction;
import com.GreyMatter.moi.model.Moisend;

import java.util.ArrayList;

public class MoisenddetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoisendAdapter moisendAdapter;

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
        ArrayList<Moisend> moisends = new ArrayList<>();
        Moisend moisend1 = new Moisend("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moisend moisend2 = new Moisend("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moisend moisend3 = new Moisend("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        moisends.add(moisend1);
        moisends.add(moisend2);
        moisends.add(moisend3);


        moisendAdapter = new MoisendAdapter(activity, moisends);
        recyclerview.setAdapter(moisendAdapter);
    }
}