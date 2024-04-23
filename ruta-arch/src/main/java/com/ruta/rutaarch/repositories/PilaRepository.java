package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Pila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilaRepository extends JpaRepository<Pila, Long> {

}
