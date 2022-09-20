package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewmemeberaddActivity extends AppCompatActivity {
    ImageView backbtn;
    private EditText etName;
    private EditText etMobile;
    private EditText etTown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmemeberadd);

        backbtn = findViewById(R.id.backbtn);
        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etTown = findViewById(R.id.etTown);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v-> {
            if(etName.getText().toString().isEmpty()){
                etName.setError("பெயரஂ");
            }else if(etMobile.getText().toString().length()!=10) {
                etMobile.setError("தவறான எண்");
            }else if(etTown.getText().toString().isEmpty()) {
                etTown.setError("செல்லாத ஊர்");
            }else{
                //intent
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}