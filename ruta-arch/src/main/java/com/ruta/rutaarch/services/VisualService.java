package com.ruta.rutaarch.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.entities.Equipo;
import com.ruta.rutaarch.repositories.JugadorRepository;
import com.ruta.rutaarch.repositories.PartidaRepository;

@Service
public class VisualService {
    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public Map<String, String> show(long idPartida, long idPlayer) {
        Optional<Partida> partidaOptional = partidaRepository.findById(idPartida);
        Optional<Jugador> jugadorOptional = jugadorRepository.findById(idPlayer);
    
        Map<String, String> envio = new HashMap<>();
    
        if (partidaOptional.isPresent() && jugadorOptional.isPresent()) {
            Partida partida = partidaOptional.get();
            Jugador jugador = jugadorOptional.get();
    
            List<Equipo> equipos = partida.getEquipos();
    
                Equipo equipo1 = equipos.get(0);
                envio.put("PBatallaE1", equipo1.getPillaBatalla());
                envio.put("PDistanciaE1", equipo1.getPillaDistancia());
                envio.put("PVelocidadE1", equipo1.getPillaVelocidad());
    
                Equipo equipo2 = equipos.get(1);
                envio.put("PBatallaE2", equipo2.getPillaBatalla());
                envio.put("PDistanciaE2", equipo2.getPillaDistancia());
                envio.put("PVelocidadE2", equipo2.getPillaVelocidad());
    
            int i = 1;
            for (Carta carta : jugador.getMano()) {
                envio.put("Carta " + i, carta.getNombre());
                i++;
            }
        } else {
            // Manejo de errores en caso de que la partida o el jugador no estén presentes
            if (!partidaOptional.isPresent()) {
                envio.put("Error", "Partida no encontrada");
            }
            if (!jugadorOptional.isPresent()) {
                envio.put("Error", "Jugador no encontrado");
            }
        }
    
        return envio;
    }
    
}
