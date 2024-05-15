package com.ruta.rutaarch.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.rutaarch.entities.Carta;
import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.entities.user.User;
import com.ruta.rutaarch.repositories.JugadorRepository;
import com.ruta.rutaarch.repositories.MazoRepository;
import com.ruta.rutaarch.repositories.PartidaRepository;
import com.ruta.rutaarch.repositories.UsuarioRepository;

@Service
public class InsertarJugService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MazoRepository mazoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public Partida insertJugador(String email, int idPartida, int numEquipo){
        Jugador jugador = new Jugador();
        jugador.setNombre(getNombreUser(email));
        Optional<Partida> partidaOptional = partidaRepository.findById((long) idPartida);

        if (partidaOptional.isPresent()){
            Partida partida = partidaOptional.get();
            if (verificarEquipos(partida, numEquipo)) {
                partida.getEquipos().get(numEquipo - 1).getJugadores().add(jugador);
                jugador.setEquipo(partida.getEquipos().get(numEquipo - 1));
                jugadorRepository.save(jugador);
                setMano(jugador, partida);
                return partidaRepository.save(partida);
            }
        }
        return partidaOptional.get();
    }

    public String getNombreUser(String email){
        Optional<User> user = usuarioRepository.findByEmail(email);
        return user.get().getEmail();
    }

    public void setMano(Jugador jugador, Partida partida){
        List<Carta> manoInicial = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Carta carta = partida.getMazo().getCartas().remove(0);
            carta.setJugador(jugador);
            carta.setMazo(null);
            manoInicial.add(carta);
        }
        jugador.setMano(manoInicial);
    }


    public boolean verificarEquipos(Partida partida, int equipo){
        if (equipo == 1 && (partida.getEquipos().get(0).getJugadores().size()/2) < partida.getNumJugadores()){
            return true;
        }

        if (equipo == 2 && (partida.getEquipos().get(1).getJugadores().size()/2) < partida.getNumJugadores()){
            return true;
        }
        return false;
    }

}
