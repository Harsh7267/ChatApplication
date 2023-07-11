package com.example.otp_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.otp_verification.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class Otp_Verification extends AppCompatActivity {

    EditText etInput1, etInput2, etInput3, etInput4, etInput5, etInput6;
    Button btnConfirm;
    String backEndOtp;
    FirebaseAuth auth;
    FirebaseDatabase database;
    String userName;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification);

        btnConfirm=findViewById(R.id.btnConfirm);

        etInput1=findViewById(R.id.etInput1);
        etInput2=findViewById(R.id.etInput2);
        etInput3=findViewById(R.id.etInput3);
        etInput4=findViewById(R.id.etInput4);
        etInput5=findViewById(R.id.etInput5);
        etInput6=findViewById(R.id.etInput6);


        database=FirebaseDatabase.getInstance();

        auth=FirebaseAuth.getInstance();

        backEndOtp=getIntent().getStringExtra("backEndOtp");
        userName=getIntent().getStringExtra("userName");
        phone=getIntent().getStringExtra("phone");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!etInput1.getText().toString().isEmpty() && !etInput2.getText().toString().isEmpty() && !etInput3.getText().toString().isEmpty() && !etInput4.getText().toString().isEmpty() && !etInput5.getText().toString().isEmpty() &&!etInput6.getText().toString().isEmpty()){

                   String otp=etInput1.getText().toString()+
                           etInput2.getText().toString()+
                           etInput3.getText().toString()+
                           etInput4.getText().toString()+
                           etInput5.getText().toString()+
                           etInput6.getText().toString();
                   if(backEndOtp!=null)
                   {
                       PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                               backEndOtp,otp
                       );
                       auth.signInWithCredential(phoneAuthCredential)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if(task.isSuccessful()){
                                           Users user=new Users(phone,userName);
                                           String id=task.getResult().getUser().getUid();
                                           database.getReference().child("Users").child(id).setValue(user);
                                           Intent intent=new Intent(getApplicationContext(),DashBoard.class);
                                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           Toast.makeText(getApplicationContext(), "OTP Verified", Toast.LENGTH_LONG).show();
                                           startActivity(intent);
                                       }
                                       else
                                       {
                                           Toast.makeText(getApplicationContext(), "Enter Correct OTP", Toast.LENGTH_LONG).show();
                                       }
                                   }
                               });
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(), "Please check Network Connect", Toast.LENGTH_LONG).show();
                   }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "OTP Invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
        numberotpmove();
    }

    private void numberotpmove() {

        etInput1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty())
                {
                    etInput2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etInput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty())
                {
                    etInput3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etInput3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty())
                {
                    etInput4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etInput4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty())
                {
                    etInput5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etInput5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty())
                {
                    etInput6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}