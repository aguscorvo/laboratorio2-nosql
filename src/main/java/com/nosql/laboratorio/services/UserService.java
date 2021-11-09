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

    public void create(User user){
        Optional<User> userByEmail = getByEmail(user.getEmail());
        if(userByEmail.isPresent()) {
            // TODO - Error 101
            return;
        }
        userRepository.save(user);
    }

    public void addRolesToUser (User user){
        Optional<User> userByEmail = getByEmail(user.getEmail());
        if(userByEmail.isEmpty()) {
            // TODO - Error 102
            return;
        }else {
            User userAux = userByEmail.get();
            if(!user.getPassword().equals(userAux.getPassword())){
                // TODO - Error 104
                return;
            }

            userAux.setRoles(getUpdatedRoles(userAux, user.getRoles(), true));
            update(userAux);
        }
    }

    public void removeRolesFromUser(User user){
        Optional<User> userByEmail = getByEmail(user.getEmail());
        if(userByEmail.isEmpty()) {
            // TODO - Error 102
            return;
        }else {
            User userAux = userByEmail.get();
            if(!user.getPassword().equals(user.getPassword())){
                // TODO - Error 104
                return;
            }
            user.getRoles().forEach(role -> {
                if (!user.getRoles().contains(role)){
                    // TODO - Error 103
                    return;
                }
            });
            userAux.setRoles(getUpdatedRoles(userAux, user.getRoles(), false));
            update(userAux);
        }
    }

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
