package com.example.users.models;

public class User {
    private String type;
    private int id;
    private String name;
    private String email;

    public User(String type, int id, String name, String email) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

}
