package com.example.otp_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.otp_verification.Adapters.ChatAdapter;
import com.example.otp_verification.Models.MessageModel;
import com.example.otp_verification.databinding.ActivityGroupChatBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GroupChatActivity.this, DashBoard.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final ArrayList<MessageModel> messageModels=new ArrayList<>();

        final ChatAdapter chatAdapter=new ChatAdapter(messageModels,this);
        binding.rvGroupChatView.setAdapter(chatAdapter);
    }
}