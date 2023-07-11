package com.example.otp_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.otp_verification.Adapters.ChatAdapter;
import com.example.otp_verification.Models.MessageModel;
import com.example.otp_verification.databinding.ActivityChatDetailsBinding;
import com.example.otp_verification.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetails extends AppCompatActivity {
    ActivityChatDetailsBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChatDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

        final String senderId=auth.getUid();
        String reciverId=getIntent().getStringExtra("userId");
        String userName=getIntent().getStringExtra("userName");
        String profilePic=getIntent().getStringExtra("profilePic");

        binding.tvUserName.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.user).into(binding.profileImage);

        binding.ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChatDetails.this, DashBoard.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessageModel> messageModels=new ArrayList<>();
        final ChatAdapter chatAdapter=new ChatAdapter(messageModels, this);
        binding.rvChatView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.rvChatView.setLayoutManager(layoutManager);


        //Creating 2 node in chats in firebase (RealTime Database
        //there will 2 sepreat room for sender and receiver
        final String senderRoom=senderId+reciverId;
        final String receiverRoom=reciverId+senderId;

        database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    MessageModel model=snapshot1.getValue(MessageModel.class);
                    messageModels.add(model);
                }
                //To update the recycler view in run time notifydataset is used

                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        binding.ivSendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String message= binding.etTypeMessage.getText().toString();
               final MessageModel model=new MessageModel(senderId,message);
               model.setTimeStamp(new Date().getTime());
               binding.etTypeMessage.setText("");

               database.getReference().child("chats").child(senderRoom)
                       .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {
                               database.getReference().child("chats").child(receiverRoom)
                                       .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void unused) {

                                           }
                                       });
                           }
                       });
            }
        });

    }
}