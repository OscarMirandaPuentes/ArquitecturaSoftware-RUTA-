package com.ruta.rutaarch.services;

import com.ruta.rutaarch.entities.Partida;
import java.util.Date;
import java.util.List;

public interface PartidaService {
    Partida savePartida(Partida partida);
    Partida getPartidaById(Long id);
    List<Partida> getAllPartidas();
    Partida updatePartida(Partida partida);
    void deletePartida(Long id);
    List<Partida> getPartidasByEstado(String estado);
    void actualizarEstadoPartida(Long partidaId, String nuevoEstado);
    int getEquipoGanador(Long partidaId);
    void eliminarTodasLasPartidas();
    long obtenerNumeroTotalDePartidas();
}
