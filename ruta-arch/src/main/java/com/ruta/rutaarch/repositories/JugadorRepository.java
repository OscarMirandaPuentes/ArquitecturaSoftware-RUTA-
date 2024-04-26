package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    // You can define custom queries here if needed, for example:
    List<Jugador> findByNombreContaining(String nombre);
}