package com.ruta.rutaarch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ruta.rutaarch.entities.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {
}
