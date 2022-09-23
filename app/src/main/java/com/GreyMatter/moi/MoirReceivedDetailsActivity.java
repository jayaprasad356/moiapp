package com.GreyMatter.moi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import com.GreyMatter.moi.Adapter.MoiReceiveddetailsAdapter;
import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.model.MoiReceiveddata;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MoirReceivedDetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Activity activity;
    RecyclerView recyclerview;
    private ImageView imgMice;
    String FunctionID;
    private EditText Search;
    MoiReceiveddetailsAdapter moiReceiveddetailsAdapter;

    private final int REQUEST_CODE_SPEECH_INPUT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi_received_details);

        activity = MoirReceivedDetailsActivity.this;

        FunctionID = getIntent().getStringExtra(Constant.FUNCTIONID);

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
                    Toast.makeText(MoirReceivedDetailsActivity.this, " " + e.getMessage(),
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


        Map<String, String> params = new HashMap<>();
        params.put(Constant.FUNCTIONID,FunctionID);

        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("SIGNUP_RES",FunctionID);

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("SIGNUP_RES",response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<MoiReceiveddata> moiReceiveddata = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                MoiReceiveddata group = g.fromJson(jsonObject1.toString(), MoiReceiveddata.class);
                                moiReceiveddata.add(group);
                            } else {
                                break;
                            }
                        }

                        moiReceiveddetailsAdapter = new MoiReceiveddetailsAdapter(activity, moiReceiveddata,R.layout.moi_recived_details, moiReceiveddata.size());
                        recyclerview.setAdapter(moiReceiveddetailsAdapter);



                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.MOI_LIST_BY_FIDS, params, true);






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