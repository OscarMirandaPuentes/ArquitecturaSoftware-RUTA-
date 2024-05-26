package com.ruta.rutaarch.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.entities.Partida;
import com.ruta.rutaarch.entities.user.User;
import com.ruta.rutaarch.repositories.UsuarioRepository;

@Service
public class HistorialService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    public List<Partida> mostrarHistorial(String email){
        List<Partida> partidas = new ArrayList<>();
        Optional<User> userOptional = usuarioRepository.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            List<Jugador> players = user.getPlayers();
            for (Jugador jugador : players) {
                partidas.add(jugador.getEquipo().getPartida());
            }
        }
        return partidas;
    }

}
