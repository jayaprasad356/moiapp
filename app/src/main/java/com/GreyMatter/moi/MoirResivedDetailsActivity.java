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

import com.GreyMatter.moi.Adapter.MoireciveddetailsAdapter;
import com.GreyMatter.moi.Adapter.MoisenddetailsAdapter;
import com.GreyMatter.moi.model.Moireciveddetails;
import com.GreyMatter.moi.model.Moisendeddetails;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MoirResivedDetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoireciveddetailsAdapter moireciveddetailsAdapter;
    private ImageView imgMice;
    private EditText Search;

    private final int REQUEST_CODE_SPEECH_INPUT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moir_resived_details);

        activity = MoirResivedDetailsActivity.this;

        imgMice = findViewById(R.id.imgMike);
        backbtn = findViewById(R.id.backbtn);
        recyclerview = findViewById(R.id.recyclerview);
        Search = findViewById(R.id.etSearch);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        imgMice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                try {
                    startActivityForResult(speech,REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e) {
                    Toast.makeText(MoirResivedDetailsActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });


        //ends here mic method
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