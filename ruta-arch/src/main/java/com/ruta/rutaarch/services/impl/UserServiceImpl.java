package com.ruta.rutaarch.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ruta.rutaarch.entities.user.User;
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
            //userRepository.deleteById(id);
            return true;
       } catch (Exception e) {
            return false;
       }
    }

    @Override
    public Optional<User> findUserbyEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName(); // Assuming email is used as username
            return userRepository.findByEmail(userEmail);
        } else {
            return Optional.empty();
        }
    }

    @Override
public Boolean updateUser(String id, User userUpdates) {
    try {
        Optional<User> userOptional = userRepository.findByEmail(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userUpdates.getName());
            user.setNick(userUpdates.getNick());
            user.setEmail(userUpdates.getEmail());
            userRepository.save(user);
            return true;
        } else {
            return false; // No se encontró el usuario con el email dado
        }
    } catch (Exception e) {
        return false; // Ocurrió un error al actualizar el usuario
    }
}

}
