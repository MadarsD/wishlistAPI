package com.example.wishlist.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExtraTaskController {

    List<User> users = new ArrayList<>(List.of(
            new User("user", 150709, "johnsmith", "jsmith@example.com"),
            new User("user", 150710, "angelinasmith", "asmith@example.com"),
            new User("user", 150910, "adamivanov", "aivanov@another.org")
    ));


    @PostMapping("/extra")
    public List<String> getNamesFromUsers(@RequestBody List<User> userList) {

        this.users = userList;

        List<String> names = new ArrayList<>();

        for (User user : users) {
            names.add(user.getName());
        }

        return names;

    }

    public static class User {
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
}
