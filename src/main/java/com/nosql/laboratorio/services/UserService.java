package com.nosql.laboratorio.services;

import com.nosql.laboratorio.dao.UserRepository;
import com.nosql.laboratorio.exceptions.AlreadyExistsException;
import com.nosql.laboratorio.exceptions.InvalidPasswordException;
import com.nosql.laboratorio.exceptions.RoleNotFoundException;
import com.nosql.laboratorio.exceptions.UserNotFoundException;
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
            throw new AlreadyExistsException("Ya existe un usuario registrado con el email " + user.getEmail() + " en el sistema");
        }
        userRepository.save(user);
    }

    public void addRolesToUser (User user){
        Optional<User> userByEmail = getByEmail(user.getEmail());
        if(userByEmail.isEmpty()) {
            throw new UserNotFoundException("No existe ningún usuario registrado con el email " + user.getEmail() + " en el sistema");
        }else {
            User userAux = userByEmail.get();
            if(!user.getPassword().equals(userAux.getPassword())){
                throw new InvalidPasswordException("Contraseña incorrecta");
            }

            userAux.setRoles(getUpdatedRoles(userAux, user.getRoles(), true));
            update(userAux);
        }
    }

    public void removeRolesFromUser(User user){
        Optional<User> userByEmail = getByEmail(user.getEmail());
        if(userByEmail.isEmpty()) {
            throw new UserNotFoundException("No existe ningún usuario registrado con el email " + user.getEmail() + " en el sistema");
        }else {
            User userAux = userByEmail.get();
            if(!user.getPassword().equals(userAux.getPassword())){
                throw new InvalidPasswordException("Contraseña incorrecta");
            }
            if (user.getRoles()!= null) {
                user.getRoles().forEach(role -> {
                    if(userAux.getRoles()!=null) {
                        if (!userAux.getRoles().contains(role)) {
                            throw new RoleNotFoundException("El rol " + role + " no se encuentra asociado al usuario registrado con el mail " + userAux.getEmail());
                        }
                    }else{
                        throw new RoleNotFoundException("El rol " + role + " no se encuentra asociado al usuario registrado con el mail " + userAux.getEmail());
                    }
                });
            }
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
        } else if(add) return newRoles;
        else return null;
    }
}
