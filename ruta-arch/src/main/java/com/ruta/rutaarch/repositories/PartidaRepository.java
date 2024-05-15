package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    // Finds all Partida entries by estado
    List<Partida> findAllByEstado(String estado);
}
