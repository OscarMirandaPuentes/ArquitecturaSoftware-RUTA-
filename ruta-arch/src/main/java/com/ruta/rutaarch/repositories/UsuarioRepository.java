package com.ruta.rutaarch.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruta.rutaarch.entities.user.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
