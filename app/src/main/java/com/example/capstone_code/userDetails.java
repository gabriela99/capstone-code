package com.example.capstone_code;

public class userDetails {
    private String userId;
    private String name;
//    private String role;
//    private String skills;

    public userDetails(String userId, String name) {
        this.userId = userId;
        this.name = name;
//        this.role = role;
//        this.skills = skills;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getSkills() {
//        return skills;
//    }
//
//    public void setSkills(String skills) {
//        this.skills = skills;
//    }
}
