package com.ruta.rutaarch.repositories;

import com.ruta.rutaarch.entities.Mazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazoRepository extends JpaRepository<Mazo, Long> {

}