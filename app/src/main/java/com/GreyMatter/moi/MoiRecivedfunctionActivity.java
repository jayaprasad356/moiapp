package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.GreyMatter.moi.Adapter.MoirecivedAdapter;
import com.GreyMatter.moi.Adapter.MoisendAdapter;
import com.GreyMatter.moi.model.Moirecived;
import com.GreyMatter.moi.model.Moisend;

import java.util.ArrayList;

public class MoiRecivedfunctionActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoirecivedAdapter moirecivedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi_recivedfunction);
        activity = MoiRecivedfunctionActivity.this;

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

        moirecivedfuc();

    }

    private void moirecivedfuc() {
        ArrayList<Moirecived> moireciveds = new ArrayList<>();
        Moirecived moirecived1 = new Moirecived("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moirecived moirecived2 = new Moirecived("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");
        Moirecived moirecived3 = new Moirecived("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        moireciveds.add(moirecived1);
        moireciveds.add(moirecived2);
        moireciveds.add(moirecived3);



        moirecivedAdapter = new MoirecivedAdapter(activity, moireciveds);
        recyclerview.setAdapter(moirecivedAdapter);


    }
}