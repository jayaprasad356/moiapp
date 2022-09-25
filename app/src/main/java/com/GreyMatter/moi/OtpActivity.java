package com.GreyMatter.moi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.GreyMatter.moi.helper.ApiConfig;
import com.GreyMatter.moi.helper.Constant;
import com.GreyMatter.moi.helper.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class OtpActivity extends AppCompatActivity {

    private Button BtnLogin;
    private String Mnumber;
    private OtpTextView otpTextView;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId = "";
    private static final String TAG = "OTP Activity";
    Activity activity;
    Session session;
    TextView tvMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        activity =  OtpActivity.this;
        session = new Session(activity);

        mAuth = FirebaseAuth.getInstance();

        BtnLogin = findViewById(R.id.btnLogin);
        otpTextView = findViewById(R.id.optView);
        tvMobile = findViewById(R.id.tvMobile);
        BtnLogin.setOnClickListener(v -> {
            if(otpTextView.getOTP().isEmpty() || otpTextView.getOTP().length() != 6) {
                Toast.makeText(this, "தவறான otp", Toast.LENGTH_SHORT).show();
            }else{

                VerifyUser();
//                startActivity(new Intent(this,ProfileActivity.class));
            }
        });




        Mnumber = getIntent().getStringExtra(Constant.MOBILE);

        tvMobile.setText("அனுப்பப்பட்ட otp ஐ உள்ளிடவும் (+91"+Mnumber+")");
        try {
            sendOTP(Mnumber);

        }catch (Exception e){


        }
        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {




                    try {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
                        signInWithPhoneAuthCredential(credential);

                    }
                    catch (Exception e){

                    }



            }
        });


    }

    private void sendOTP(String mobilenumber) {
        Log.d("OTP_RES",mobilenumber);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };


        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + mobilenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            VerifyUser();

                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    private void VerifyUser() {
        Map<String,String> params = new HashMap<>();
        params.put(Constant.MOBILENUMBER,Mnumber);
        ApiConfig.RequestToVolley((result,response) -> {
            if(result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean(Constant.NEWUSER)) {
                        Intent i = new Intent(activity,ProfileActivity.class);
                        i.putExtra(Constant.MOBILENUMBER,Mnumber);
                        startActivity(i);
                    }else{
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setBoolean("is_logged_in", true);
                        session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                        startActivity(new Intent(activity,
                                MainActivity.class));
                        finish();
                    }
                    finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(activity, "server unreachable", Toast.LENGTH_SHORT).show();
            }
        },activity,Constant.LOGIN_URL,params,true);
    }

}