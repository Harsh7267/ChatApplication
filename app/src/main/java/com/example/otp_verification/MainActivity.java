package com.example.otp_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.otp_verification.Models.Users;
import com.example.otp_verification.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button btnGetOTP;//getting otp after clicking on button
    EditText etPhone,etUserName;//entering the phone number




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName=findViewById(R.id.etUserName);
        etPhone=findViewById(R.id.etPhone);
        btnGetOTP=findViewById(R.id.btnGetOTP);

        //ProgressBar shows the animation till otp gets
        ProgressBar pbVerify=findViewById(R.id.pbVerify);




        //On click listner used for getting otp it is used on button Get Otp
        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //getting phone in the String data type
                String phone = etPhone.getText().toString();
                //checking the data field of Phone is empty or not
                if (!phone.isEmpty()) {
                    pbVerify.setVisibility(View.VISIBLE);
                    btnGetOTP.setVisibility(View.GONE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + etPhone.getText().toString(), 30,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    pbVerify.setVisibility(View.GONE);
                                    btnGetOTP.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    pbVerify.setVisibility(View.GONE);
                                    btnGetOTP.setVisibility(View.VISIBLE);
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backEndOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    pbVerify.setVisibility(View.GONE);
                                    btnGetOTP.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), Otp_Verification.class);
                                    intent.putExtra("phone", etPhone.getText().toString());
                                    intent.putExtra("userName", etUserName.getText().toString());
                                    intent.putExtra("backEndOtp", backEndOtp);
                                    super.onCodeSent(backEndOtp,forceResendingToken);
                                    startActivity(intent);
                                }
                            }
                    );
                } else {


                }
            }
        });

    }




}