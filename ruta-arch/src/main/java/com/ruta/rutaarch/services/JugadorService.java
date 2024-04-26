package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Jugador;
import java.util.List;

public interface JugadorService {
    Jugador saveJugador(Jugador jugador);
    Jugador getJugadorById(Long id);
    List<Jugador> getAllJugadores();
    Jugador updateJugador(Jugador jugador);
    void deleteJugador(Long id);
}