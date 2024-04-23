package com.ruta.rutaarch.services;

import java.util.Optional;

import com.ruta.rutaarch.entities.user.User;

public interface UserService {
    public Boolean saveUser(User user);
    public Boolean deleteUser(String id);
    public Optional<User> findUserbyEmail(String email);
    public Boolean updateUser(String email, User userUpdates);
}