package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Pila;
import java.util.List;

public interface PilaService {
    Pila savePila(Pila pila);
    Pila getPilaById(Long id);
    List<Pila> getAllPilas();
    Pila updatePila(Pila pila);
    void deletePila(Long id);
}
