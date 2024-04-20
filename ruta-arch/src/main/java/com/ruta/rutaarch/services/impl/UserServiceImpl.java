package com.ruta.rutaarch.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.rutaarch.entities.User;
import com.ruta.rutaarch.repositories.UsuarioRepository;
import com.ruta.rutaarch.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public Boolean saveUser(User user) {
        try {
            userRepository.save(user);
            return true;
       } catch (Exception e) {
            return false;
       }
    }

    @Override
    public Boolean deleteUser(String id) {
        try {
            userRepository.deleteById(id);
            return true;
       } catch (Exception e) {
            return false;
       }
    }

    @Override
    public User findUserbyEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean updateUser(String id, User userUpdates) {
        try {
            User user = userRepository.findByEmail(id);
            if (user != null) {
                user.setName(userUpdates.getName());
                user.setUsername(userUpdates.getUsername());
                user.setEmail(userUpdates.getEmail());
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
