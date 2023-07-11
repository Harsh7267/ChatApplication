package com.example.otp_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.otp_verification.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {
    ActivitySettingsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.accountActivity.setVisibility(View.GONE);
        binding.notificationActivity.setVisibility(View.GONE);

        getSupportActionBar().hide();

        binding.account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.settingActivity.setVisibility(View.GONE);
                binding.accountActivity.setVisibility(View.VISIBLE);

            }
        });

        binding.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.settingActivity.setVisibility(View.GONE);
                binding.notificationActivity.setVisibility(View.VISIBLE);
            }
        });
    }
}