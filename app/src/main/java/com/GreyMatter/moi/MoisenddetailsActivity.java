package com.GreyMatter.moi;

import static com.GreyMatter.moi.helper.Constant.SUCCESS;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.GreyMatter.moi.Adapter.FunctionAdapter;
import com.GreyMatter.moi.Adapter.MoiSentFunctionAdapter;
import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Functions;
import com.GreyMatter.moi.model.MoiSentFunctions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class MoisenddetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    MoiSentFunctionAdapter moiSentFunctionAdapter;
    Session session;
    private ImageView imgMike;
    private EditText etSearch;
    private static final int REQUEST_CODE_SPEECH_INPUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moisenddetails);
        activity = MoisenddetailsActivity.this;
        session = new Session(activity);

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

        recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));


        functionList();
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

    private void functionList() {

        HashMap<String,String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(SUCCESS)){
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<MoiSentFunctions> moiSentFunctions = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                MoiSentFunctions group = g.fromJson(jsonObject1.toString(), MoiSentFunctions.class);
                                moiSentFunctions.add(group);
                            } else {
                                break;
                            }
                        }
                        moiSentFunctionAdapter = new MoiSentFunctionAdapter(activity, moiSentFunctions);
                        recyclerview.setAdapter(moiSentFunctionAdapter);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },activity, Constant.MOILIST,params,true);

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