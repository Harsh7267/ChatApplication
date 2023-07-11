package com.example.otp_verification.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otp_verification.Models.Users;
import com.example.otp_verification.Models.UsersAdapter;
import com.example.otp_verification.R;
import com.example.otp_verification.databinding.FragmentChatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Chats extends Fragment {

FragmentChatsBinding binding;
ArrayList<Users> list=new ArrayList<>();
FirebaseDatabase database;
    public Chats() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        database=FirebaseDatabase.getInstance();
        binding=FragmentChatsBinding.inflate(inflater,container,false);

        UsersAdapter adapter=new UsersAdapter(list, getContext());
        binding.rvChat.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.rvChat.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                 Users users=dataSnapshot.getValue(Users.class);
                 users.setUserId(dataSnapshot.getKey());
                 list.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();

    }
}