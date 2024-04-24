package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartaRepository extends JpaRepository<Carta, Long> {

    List<Carta> findByNombre(String nombre);
}
