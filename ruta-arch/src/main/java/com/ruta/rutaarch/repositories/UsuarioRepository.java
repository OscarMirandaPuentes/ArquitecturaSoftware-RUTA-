package com.ruta.rutaarch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruta.rutaarch.entities.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);

}
