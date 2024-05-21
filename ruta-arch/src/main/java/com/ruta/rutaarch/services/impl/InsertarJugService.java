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
import com.ruta.rutaarch.repositories.PartidaRepository;
import com.ruta.rutaarch.repositories.UsuarioRepository;

@Service
public class InsertarJugService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public String insertJugador(String email, int idPartida, int numEquipo){
        User user = getNombreUser(email);
        Optional<Partida> partidaOptional = partidaRepository.findById((long) idPartida);

        if(!verficarEntrada(user, idPartida)){
            Jugador jugador = new Jugador();
            jugador.setNombre(user.getName());
            
            if (partidaOptional.isPresent()){
                user.getPlayers().add(jugador);
                jugador.setUser(user);
                usuarioRepository.save(user);
                
                Partida partida = partidaOptional.get();
                if (verificarEquipos(partida, numEquipo)) {
                    partida.getEquipos().get(numEquipo - 1).getJugadores().add(jugador);
                    jugador.setEquipo(partida.getEquipos().get(numEquipo - 1));
                    jugadorRepository.save(jugador);
                    setMano(jugador, partida);
                    if (partida.getEquipos().get(0).getJugadores().size() == 1 && (numEquipo - 1) == 0) {
                        List<Carta> manoInicial = jugador.getMano();
                        Carta carta = partida.getMazo().getCartas().remove(0);
                        carta.setJugador(jugador);
                        carta.setMazo(null);
                        manoInicial.add(carta);
                        jugador.setMano(manoInicial);
                        partida.setJugadorTurno(jugador.getId());
                    }
                    partidaRepository.save(partida);
                    return "Ingresado " + jugador.getId().toString();
                }
            }
        }
        else if(verficarEntrada(user, idPartida)){
            return "Existente " + darId(user, idPartida);
        }
        return "Error NO";
    }

    public User getNombreUser(String email){
        Optional<User> user = usuarioRepository.findByEmail(email);
        return user.get();
    }

    public void setMano(Jugador jugador, Partida partida){
        List<Carta> manoInicial = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Carta carta = partida.getMazo().getCartas().remove(0);
            carta.setJugador(jugador);
            carta.setMazo(null);
            manoInicial.add(carta);
        }
        jugador.setMano(manoInicial);
    }


    public boolean verificarEquipos(Partida partida, int equipo){

        if((partida.getEquipos().get(0).getJugadores().size() * partida.getEquipos().get(1).getJugadores().size()) == partida.getNumJugadores()){
            partida.setEstado("En progreso");
            return false;
        }

        if (equipo == 1 && (partida.getEquipos().get(0).getJugadores().size()/2) < partida.getNumJugadores()){
            return true;
        }

        if (equipo == 2 && (partida.getEquipos().get(1).getJugadores().size()/2) < partida.getNumJugadores()){
            return true;
        }

        return false;
    }

    public boolean verficarEntrada(User user, int idPartida){
        List<Jugador> players = user.getPlayers();
        for (Jugador jugador : players) {
           if (jugador.getEquipo().getPartida().getId() == idPartida){
            return true;
           }
        }
        return false;
    }

    public String verficarEntrada(String email, int idPartida){
        User user = getNombreUser(email);
        List<Jugador> players = user.getPlayers();
        for (Jugador jugador : players) {
           if (jugador.getEquipo().getPartida().getId() == idPartida){
            return "Existente " + jugador.getId();
           }
        }
        return null;
    }

    public long darId(User user, int idPartida){
        List<Jugador> players = user.getPlayers();
        for (Jugador jugador : players) {
           if (jugador.getEquipo().getPartida().getId() == idPartida){
            return jugador.getId();
           }
        }
        return 0;
    }

}
