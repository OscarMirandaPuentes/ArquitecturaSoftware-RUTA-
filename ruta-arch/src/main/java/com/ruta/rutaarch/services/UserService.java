package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.User;

public interface UserService {
    public Boolean saveUser(User user);
    public Boolean deleteUser(String id);
    public User findUserbyEmail(String email);
    public Boolean updateUser(String email, User userUpdates);
}