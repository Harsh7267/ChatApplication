package com.example.otp_verification.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.otp_verification.Fragments.Calls;
import com.example.otp_verification.Fragments.Camera;
import com.example.otp_verification.Fragments.Chats;
import com.example.otp_verification.Fragments.Status;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return  new Camera();
            case 1:
                return new Chats();
            case 2:
                return new Status();
            case 3:
                return new Calls();
            default:
                return new Chats();

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0)
        {
            title="CAMERA";
        }
        if(position==1)
        {
            title="CHATS";
        }
        if(position==2)
        {
            title="STATUS";
        }
        if(position==3)
        {
            title="CALLS";
        }
        return title;
    }
}
