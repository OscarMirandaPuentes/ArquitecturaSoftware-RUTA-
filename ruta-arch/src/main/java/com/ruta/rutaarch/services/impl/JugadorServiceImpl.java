package com.ruta.rutaarch.services.impl;

import com.ruta.rutaarch.entities.Jugador;
import com.ruta.rutaarch.repositories.JugadorRepository;
import com.ruta.rutaarch.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;

    @Autowired
    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    @Transactional
    public Jugador saveJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    public Jugador getJugadorById(Long id) {
        return jugadorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    @Transactional
    public Jugador updateJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    @Transactional
    public void deleteJugador(Long id) {
        jugadorRepository.deleteById(id);
    }
}