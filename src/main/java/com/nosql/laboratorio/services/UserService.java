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

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public boolean withCredentialsIsPresent(String email, String password){
        Optional<User> userByEmailAndPassword = userRepository.getUserByEmailAndPassword(email, password);
        return userByEmailAndPassword.isPresent();
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void create(User user){ userRepository.save(user); }

    // AUX

    public List<String> getUpdatedRoles (User user, List<String> newRoles, boolean add ){
        List<String> roles = user.getRoles();
        if (roles!= null){
            newRoles.forEach(role -> {
                if(add){
                    if (!roles.contains(role)){
                        roles.add(role);
                    }
                }else{
                    roles.remove(role);
                }
            });
            return roles;
        }
        return newRoles;
    }
}
