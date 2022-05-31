package com.example.users.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.users.models.User;
import com.example.users.requests.UsersRequest;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class UserController {


    @PostMapping("extra")
    public String getNamesFromUsers(@RequestBody UsersRequest usersRequest) {

        return usersRequest.getUsers()
                .stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));

    }

}
