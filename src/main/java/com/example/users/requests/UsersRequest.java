package com.example.users.requests;

import com.example.users.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

public class UsersRequest {
    private List<User> users;

    public UsersRequest(List<User> users) {
        this.users = users;
    }

    public UsersRequest() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}