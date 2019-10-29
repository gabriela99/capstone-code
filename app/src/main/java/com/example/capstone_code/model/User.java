package com.example.capstone_code.model;

public class User {
    private String name;
    private String role;
    private String skills;

    public User() {
    }

    public User(String name, String role, String skills) {
        this.name = name;
        this.role = role;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
