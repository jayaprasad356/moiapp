package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AmountrecivedActivity extends AppCompatActivity {

    Button newmemberbtn;

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
    }
}