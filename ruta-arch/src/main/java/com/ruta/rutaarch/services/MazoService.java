package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Mazo;
import java.util.List;

public interface MazoService {
    Mazo saveMazo(Mazo mazo);
    Mazo getMazoById(Long id);
    List<Mazo> getAllMazos();
    Mazo updateMazo(Mazo mazo);
    void deleteMazo(Long id);
}