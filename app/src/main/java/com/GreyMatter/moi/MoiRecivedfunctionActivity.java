package com.GreyMatter.moi;

import static com.GreyMatter.moi.helper.Constant.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.GreyMatter.moi.Adapter.MoirecivedAdapter;
import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Moirecived;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class MoiRecivedfunctionActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoirecivedAdapter moirecivedAdapter;
    private ImageView imgMike;
    private EditText etSearch;
    private final int  REQUEST_CODE_SPEECH_INPUT= 3;
    Session session;

    ArrayList<Moirecived> moireciveds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi_recivedfunction);
        activity = MoiRecivedfunctionActivity.this;

        backbtn = findViewById(R.id.backbtn);

        recyclerview = findViewById(R.id.recyclerview);

        imgMike = findViewById(R.id.imgMike);
        etSearch = findViewById(R.id.etSearch);
        session = new Session(activity);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        recyclerview.setLayoutManager(gridLayoutManager);

        moirecivedfuc();

        imgMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                try {
                    startActivityForResult(speech,REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e) {
                    Toast.makeText(MoiRecivedfunctionActivity.this, " " + e.getMessage(),
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


    private void moirecivedfuc() {
        HashMap<String,String> params = new HashMap<>();
        params.put(ID,session.getData(ID));
        ApiConfig.RequestToVolley((result,response) -> {


            if(result) {
                try {
                    JSONObject object = new JSONObject(response);
                    if(object.getBoolean(SUCCESS)){
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Moirecived> moireciveds = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Moirecived group = g.fromJson(jsonObject1.toString(), Moirecived.class);
                                moireciveds.add(group);
                            } else {
                                break;
                            }
                        }
                        moirecivedAdapter = new MoirecivedAdapter(activity, moireciveds);
                        recyclerview.setAdapter(moirecivedAdapter);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(activity, "Server unreachable", Toast.LENGTH_SHORT).show();
            }
        },activity, FUNCTIONLIST,params,true);
    }

}