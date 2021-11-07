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

    @GetMapping
    public List<User> fetchAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public /*TODO: Optional<Error>*/ void createUser(@RequestBody User request){
        Optional<User> userByEmail = userService.getByEmail(request.getEmail());
        if(userByEmail.isPresent()) {
            // TODO - Error 101
            return;
        }
        userService.create(request);
    }

    @PutMapping
    public /*TODO: Optional<Error>*/ void addRolesToUser(@RequestBody User request){
        Optional<User> userByEmail = userService.getByEmail(request.getEmail());
        if(userByEmail.isEmpty()) {
            // TODO - Error 102
            return;
        }else {
            User user = userByEmail.get();
            if(!request.getPassword().equals(user.getPassword())){
                // TODO - Error 104
                return;
            }

            user.setRoles(userService.getUpdatedRoles(user, request.getRoles()));
            userService.update(user);
        }
    }

    @GetMapping("/auth")
    public Map<String, Boolean> authenticateUser(@RequestBody User request){
        return Collections.singletonMap("authentication",
                userService.withCredentialsIsPresent(request.getEmail(), request.getPassword()));
    }
}
