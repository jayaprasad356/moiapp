package com.GreyMatter.moi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.GreyMatter.moi.Adapter.MoisendingfunctionAdapter;
import com.GreyMatter.moi.model.Moisendingfunction;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MoisentfunctionActivity extends AppCompatActivity {

    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoisendingfunctionAdapter moisendingfunctionAdapter;
    private ImageView imgMike;
    private EditText etSearch;
    private static final int REQUEST_CODE_SPEECH_INPUT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisentfunction);

        activity = MoisentfunctionActivity.this;

        backbtn = findViewById(R.id.backbtn);
        recyclerview = findViewById(R.id.recyclerview);
        imgMike = findViewById(R.id.imgMike);
        etSearch = findViewById(R.id.etSearch);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);


        moisentfunction();



        imgMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                try {
                    startActivityForResult(speech,REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e) {
                    Toast.makeText(MoisentfunctionActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                etSearch.setText(
                        Objects.requireNonNull(result).get(0));
            }
        }
    }

    private void moisentfunction() {

        ArrayList<Moisendingfunction> moisendingfunctions = new ArrayList<>();
        Moisendingfunction moisendingfunction1 = new Moisendingfunction("1","","நயனஂ - விக்னேஷ் திருமண விழா","இடம் - சென்னை","தேதி - 01-11-2022");

        moisendingfunctions.add(moisendingfunction1);




        moisendingfunctionAdapter = new MoisendingfunctionAdapter(activity, moisendingfunctions);
        recyclerview.setAdapter(moisendingfunctionAdapter);

    }
}