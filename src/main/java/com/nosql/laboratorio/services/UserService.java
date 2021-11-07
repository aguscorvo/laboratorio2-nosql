package com.nosql.laboratorio.services;

import com.nosql.laboratorio.dao.UserRepository;
import com.nosql.laboratorio.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public boolean userByEmailAndPasswordIsPresent(String email, String password){
        Optional<User> userByEmailAndPassword = userRepository.getUserByEmailAndPassword(email, password);
        return userByEmailAndPassword.isPresent();
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void createUser(User user){ userRepository.save(user); }

    // AUX

    public List<String> getUpdatedRoles (User user, List<String> newRoles ){
        List<String> roles = user.getRoles();
        if (roles!= null){
            newRoles.forEach(role -> {
                if (!roles.contains(role)){
                    roles.add(role);
                }
            });
            return roles;
        }
        return newRoles;
    }
}
