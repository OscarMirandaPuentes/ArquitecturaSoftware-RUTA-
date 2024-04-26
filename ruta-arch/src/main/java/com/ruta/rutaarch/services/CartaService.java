package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Carta;
import java.util.List;

public interface CartaService {
    Carta createCarta(Carta carta);
    Carta getCartaById(Long id);
    List<Carta> getAllCartas();
    Carta updateCarta(Carta carta);
    void deleteCarta(Long id);
}