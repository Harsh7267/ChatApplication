package com.example.otp_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.otp_verification.Adapters.FragmentsAdapter;
import com.example.otp_verification.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {
    ActivityDashBoardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.iNewGroup:
                Intent intent1=new Intent(this, GroupChatActivity.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(), "Group Chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iLinkedDevices:
                break;

            case R.id.iStarredMessages:
                break;

            case R.id.iSettings:
                Intent intent=new Intent(this, Settings.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}