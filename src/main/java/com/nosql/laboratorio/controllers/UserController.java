package com.nosql.laboratorio.controllers;

import com.nosql.laboratorio.models.User;
import com.nosql.laboratorio.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> fetchAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/auth")
    public Map<String, Boolean> authenticateUser(@RequestBody User user){
        return Collections.singletonMap("authentication",
                userService.userByEmailAndPasswordIsPresent(user.getEmail(), user.getPassword()));
    }
}
