package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

}
