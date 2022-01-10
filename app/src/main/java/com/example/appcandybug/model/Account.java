package com.example.appcandybug.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Account implements Parcelable {
    private String UserName;
    private String PassWord;
    private String DisplayName;
    private String Email;
    private int SDT;

    public Account( String UserName, String PassWord) {
        this.UserName = UserName;
        this.PassWord = PassWord;
    }

    public Account(String userName, String passWord, String displayName, String email, int SDT) {
        UserName = userName;
        PassWord = passWord;
        DisplayName = displayName;
        Email = email;
        this.SDT = SDT;

    }

    protected Account(Parcel in) {
        UserName = in.readString();
        PassWord = in.readString();
        DisplayName = in.readString();
        Email = in.readString();
        SDT = in.readInt();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UserName);
        dest.writeString(PassWord);
        dest.writeString(DisplayName);
        dest.writeString(Email);
        dest.writeInt(SDT);
    }
}
