package com.nosql.laboratorio.controllers;

import com.nosql.laboratorio.models.User;
import com.nosql.laboratorio.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{email}")
    public Optional<User> fetchUser(@PathVariable String email){
        return userService.getByEmail(email);
    }

    @GetMapping
    public List<User> fetchAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User request){
        userService.create(request);
    }

    @PutMapping("/addRoles")
    public void addRolesToUser(@RequestBody User request){
        userService.addRolesToUser(request);
    }

    @PutMapping("/removeRoles")
    public void removeRolesFromUser(@RequestBody User request){
        userService.removeRolesFromUser(request);
    }

    @GetMapping("/auth")
    public Map<String, Boolean> authenticateUser(@RequestBody User request){
        return Collections.singletonMap("authentication",
                userService.withCredentialsIsPresent(request.getEmail(), request.getPassword()));
    }
}
