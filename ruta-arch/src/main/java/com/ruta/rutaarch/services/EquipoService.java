package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Equipo;

import java.util.List;

public interface EquipoService {
    Equipo createEquipo(Equipo equipo);
    Equipo getEquipoById(Long id);
    List<Equipo> getAllEquipos();
    Equipo updateEquipo(Equipo equipo);
    void deleteEquipo(Long id);
}
