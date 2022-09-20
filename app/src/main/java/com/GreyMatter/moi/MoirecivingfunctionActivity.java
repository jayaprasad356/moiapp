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

import com.GreyMatter.moi.Adapter.MoirecivingfunctionAdapter;
import com.GreyMatter.moi.Adapter.MoisendingfunctionAdapter;
import com.GreyMatter.moi.model.Moirecivingfunction;
import com.GreyMatter.moi.model.Moisendingfunction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MoirecivingfunctionActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoirecivingfunctionAdapter moirecivingfunctionAdapter;
    FloatingActionButton fabbtn;

    private ImageView imgMice;
    private EditText Search;

    private final int REQUEST_CODE_SPEECH_INPUT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moirecivingfunction);

        activity = MoirecivingfunctionActivity.this;

        backbtn = findViewById(R.id.backbtn);
        fabbtn = findViewById(R.id.fabbtn);
        recyclerview = findViewById(R.id.recyclerview);
        Search = findViewById(R.id.etSearch);
        imgMice = findViewById(R.id.imgMike);


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

        imgMice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                try {
                    startActivityForResult(speech,REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e) {
                    Toast.makeText(MoirecivingfunctionActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                Search.setText(
                        Objects.requireNonNull(result).get(0));
            }
        }
    }
}