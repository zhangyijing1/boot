package com.by.model;

public class User {
    private Integer userId;

    private String userName;

    private String userPswd;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd == null ? null : userPswd.trim();
    }

    public User(String userName, String userPswd) {
        this.userName = userName;
        this.userPswd = userPswd;
    }

    public User() {

    }

    public User(Integer userId, String userName, String userPswd) {
        this.userId = userId;
        this.userName = userName;
        this.userPswd = userPswd;
    }
}