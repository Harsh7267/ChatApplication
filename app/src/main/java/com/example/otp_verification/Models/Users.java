package com.example.otp_verification.Models;

public class Users {
    String profilePicture, phone, userName, userId, lastMessage;

    public Users(String profilePicture, String userName, String phone, String userId, String lastMessage) {
        this.profilePicture = profilePicture;
        this.userName = userName;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.phone=phone;
    }

    public Users(){}

    public Users(String phone, String userName) {
        this.phone = phone;
        this.userName = userName;
    }



    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

