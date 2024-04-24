package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    // Finds all Partida entries by estado
    List<Partida> findAllByEstado(String estado);

    // Finds all Partida entries within a date range
    List<Partida> findAllByFechaInicioBetween(Date fechaInicio, Date fechaFin);

    // Example of a custom JPQL query using @Query annotation
    @Query("SELECT p FROM Partida p WHERE p.fechaInicio >= :fechaInicio AND p.fechaFin <= :fechaFin")
    List<Partida> findByFechaRange(Date fechaInicio, Date fechaFin);
}
