package com.ruta.rutaarch.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruta.rutaarch.entities.user.User;

public interface UsuarioRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
