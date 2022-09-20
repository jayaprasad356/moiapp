package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AmountrecivedActivity extends AppCompatActivity {

    Button newmemberbtn;
    private EditText etMobile;
    private EditText etAmount;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amountrecived);

        newmemberbtn = findViewById(R.id.newmemberbtn);
        newmemberbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmountrecivedActivity.this,NewmemeberaddActivity.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(v -> {
            if(etMobile.getText().toString().isEmpty()) {
                etMobile.setError("கைபேசி எண்");
            }else if(etAmount.getText().toString().isEmpty()) {
                etAmount.setError("தொகை");
            }else if(etMobile.getText().toString().length() != 10) {
                etMobile.setError("தவறான எண்");
            }else{
                // need to edit this for next updates
            }
        });
    }
}