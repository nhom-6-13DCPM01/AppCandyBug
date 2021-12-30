package com.example.appcandybug.model;

public class Account {
    private String UserName;
    private String PassWord;

    public Account( String UserName, String PassWord) {
        this.UserName = UserName;
        this.PassWord = PassWord;
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
}
