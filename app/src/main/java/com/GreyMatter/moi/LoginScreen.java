package com.GreyMatter.moi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private EditText etPhoneNumber;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        etPhoneNumber = findViewById(R.id.etMobile);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPhoneNumber.toString().isEmpty()) {
                    Toast.makeText(LoginScreen.this, "தொடர தொலைபேசி எண்ணை உள்ளிடவும்", Toast.LENGTH_LONG).show(); //Enter phone number to proceed
                }else{
                    //need to change this after using shared preferences
                    startActivity(new Intent(LoginScreen.this,Otp.class));
                    finish();
                }
            }
        });

    }
}