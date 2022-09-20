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

import com.GreyMatter.moi.Adapter.MoisenddetailsAdapter;
import com.GreyMatter.moi.model.Moisendeddetails;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MoisenddetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoisenddetailsAdapter moisenddetailsAdapter;
    private ImageView imgMike;
    private EditText etSearch;
    private static final int REQUEST_CODE_SPEECH_INPUT = 2;

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


        imgMike = findViewById(R.id.imgMike);
        etSearch = findViewById(R.id.etSearch);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);


        MoiSend();

        imgMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                try {
                    startActivityForResult(speech,REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e) {
                    Toast.makeText(MoisenddetailsActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
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
}